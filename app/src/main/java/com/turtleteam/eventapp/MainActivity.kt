package com.turtleteam.eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.Settings
import com.turtleteam.core_view.theme.EventAppTheme
import com.turtleteam.eventapp.navigation.MainNavigationScreen
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val settings = koinInject<Settings>()
            val isDark = settings.theme.collectAsState(initial = false)
            EventAppTheme(isDark.value) {
                MainNavigationScreen(navController = navController)
            }
        }
    }
}
