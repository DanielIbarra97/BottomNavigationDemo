package com.example.bottomnavigationdemo

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationdemo.ui.pages.LoginPage
import com.example.bottomnavigationdemo.ui.pages.main.MainScreen
import com.example.bottomnavigationdemo.ui.theme.BottomNavigationDemoTheme
import androidx.compose.ui.graphics.Color

@Composable
fun AppEntry() {
    val systemDarkTheme = isSystemInDarkTheme()
    var isAuthenticated by remember { mutableStateOf(false) }
    var isDarkTheme by remember { mutableStateOf(systemDarkTheme) }

    val navController = rememberNavController()

    BottomNavigationDemoTheme(darkTheme = isDarkTheme) {
        Surface(color = MaterialTheme.colorScheme.background) {
            if (isAuthenticated) {
                MainScreen(
                    navController = navController, // <-- Esta línea faltaba
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = it },
                    onLogout = { isAuthenticated = false }
                )
            } else {
                BottomNavigationDemoTheme(darkTheme = false) {
                    LoginPage(onLoginSuccess = { isAuthenticated = true })
                }
            }
        }
    }
}