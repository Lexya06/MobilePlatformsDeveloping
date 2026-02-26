package com.github.lexya06.culinaryguide.ui.utils

import android.content.Context
import android.content.res.Configuration
import org.intellij.lang.annotations.Language
import java.util.Locale

object LocaleHelper {

    fun updateLocale(context: Context, language: String?): Context {

        val locale = if (language != null) {
            Locale.forLanguageTag(language)
        } else {
            context.resources.configuration.locales[0]
        }

        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }



}