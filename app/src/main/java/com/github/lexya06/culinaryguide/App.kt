package com.github.lexya06.culinaryguide

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.lexya06.culinaryguide.language.LanguageViewModel
import com.github.lexya06.culinaryguide.ui.navigation.AppNavGraph
import com.github.lexya06.culinaryguide.ui.theme.CulinaryGuideTheme
import com.github.lexya06.culinaryguide.ui.theme.ThemeViewModel
import com.github.lexya06.culinaryguide.ui.utils.LocaleHelper

@Composable
fun App() {

    val themeViewModel: ThemeViewModel = viewModel()
    val isDark by themeViewModel.isDarkTheme.collectAsState()

    val languageViewModel: LanguageViewModel = viewModel()
    val language by languageViewModel.currentLanguage.collectAsState()

    val baseContext = LocalContext.current

    val localizedContext = remember(language) {
        LocaleHelper.updateLocale(baseContext, language)
    }

    CompositionLocalProvider(
        LocalContext provides localizedContext
    ) {
        CulinaryGuideTheme(darkTheme = isDark) {
            AppNavGraph()
        }
    }

}