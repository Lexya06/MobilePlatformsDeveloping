package com.github.lexya06.culinaryguide.ui.main


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

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository = UserRepository(application)

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                repository.getAll()
            }
            _users.value = list
        }
    }

    fun deleteUser(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(userId)
            loadUsers()
        }
    }
}