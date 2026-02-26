package com.github.lexya06.culinaryguide.ui.edituser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.lexya06.culinaryguide.data.model.User
import com.github.lexya06.culinaryguide.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import kotlin.time.ExperimentalTime

class EditUserViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository = UserRepository(application)

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser(userId: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getById(userId)
            }
            _user.value = result
        }
    }

    @OptIn(ExperimentalTime::class)
    fun updateUser(
        login: String,
        email: String,
        password: String,
        age: Byte,
        imagePath: String,
        onSuccess: () -> Unit
    ) {
        val currentUser = _user.value ?: return

        viewModelScope.launch {

            val updatedUser = currentUser.copy(
                userLogin = login,
                userEmail = email,
                userPasswHash = if (password.isNotBlank())
                    hash(password)
                else
                    currentUser.userPasswHash,
                userAge = age,
                userImagePath = imagePath
            )

            withContext(Dispatchers.IO) {
                repository.update(updatedUser)
            }

            onSuccess()
        }
    }

    private fun hash(input: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}