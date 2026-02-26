package com.github.lexya06.culinaryguide.ui.main


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import com.github.lexya06.culinaryguide.R

import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.lexya06.culinaryguide.language.LanguageViewModel
import com.github.lexya06.culinaryguide.ui.theme.ThemeViewModel
import com.github.lexya06.culinaryguide.ui.component.UserItem
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAddClick: () -> Unit,
    onEditClick: (Long) -> Unit,
    viewModel: MainViewModel = viewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.loadUsers()
    }
    val users by viewModel.users.collectAsState()

    val themeViewModel : ThemeViewModel = viewModel()
    val isDark by themeViewModel.isDarkTheme.collectAsState()

    val languageViewModel : LanguageViewModel = viewModel()
    val language by languageViewModel.currentLanguage.collectAsState()

    val currentLanguage = language ?: Locale.getDefault().language

    val nextLanguage = if (currentLanguage == "ru") "en" else "ru"


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource( R.string.users)) },
                actions = {
                        IconButton(
                            onClick = { themeViewModel.toggleTheme() }
                        ) {
                            Icon(
                                imageVector = if (isDark)
                                    Icons.Default.FavoriteBorder
                                else
                                    Icons.Default.Favorite,
                                contentDescription = stringResource(R.string.dark_theme)
                            )
                        }

                       Button(
                           onClick = {languageViewModel.changeLanguage(nextLanguage)}
                       ) {
                           Text(currentLanguage)
                       }


                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_user)
                )
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            items(users, key = { it.userId }) { user ->
                UserItem(
                    user = user,
                    onClick = {
                        onEditClick(user.userId)
                    },
                    onDeleteClick = {
                        viewModel.deleteUser(user.userId)
                    }
                )
            }
        }
    }
}