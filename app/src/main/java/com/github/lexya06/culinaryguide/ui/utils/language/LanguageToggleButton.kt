package com.github.lexya06.culinaryguide.ui.utils.language

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.github.lexya06.culinaryguide.language.LanguageViewModel
import androidx.compose.runtime.getValue
import java.util.Locale

@Composable
fun LanguageToggleButton(languageViewModel: LanguageViewModel) {

    val language by languageViewModel.currentLanguage.collectAsState()

    val currentLang = language ?: Locale.getDefault().language

    val nextLanguage = if (currentLang == "ru") "en" else "ru"

    val buttonText = if (currentLang == "ru") {
        "Switch to English"
    } else {
        "Переключить на русский"
    }

    Button(
        onClick = {
            languageViewModel.changeLanguage(nextLanguage)
        }
    ) {
        Text(buttonText)
    }
}