package com.github.lexya06.culinaryguide.ui.adduser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.lexya06.culinaryguide.data.model.User
import com.github.lexya06.culinaryguide.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class AddUserViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository = UserRepository(application)

    @OptIn(ExperimentalTime::class)
    fun insertUser(
        login: String,
        email: String,
        password: String,
        age: Byte,
        imagePath: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            val user = User(
                userId = 0L,
                userLogin = login,
                userEmail = email,
                userPasswHash = hash(password),
                userAge = age,
                userDateReg = Clock.System.now(),
                userImagePath = imagePath
            )

            withContext(Dispatchers.IO) {
                repository.insert(user)
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