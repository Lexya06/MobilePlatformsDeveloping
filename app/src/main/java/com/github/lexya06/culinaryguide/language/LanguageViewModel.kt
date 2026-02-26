package com.github.lexya06.culinaryguide.language

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.lexya06.culinaryguide.data.datastore.LanguagePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LanguageViewModel(application: Application) :
    AndroidViewModel(application) {

    private val preferences = LanguagePreferences(application)

    val currentLanguage = preferences.appLanguage
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun changeLanguage(language: String?) {
        viewModelScope.launch {
            preferences.setLanguage(language)
        }
    }



}