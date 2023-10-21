package com.turtleteam.eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.Settings
import com.turtleteam.core_view.theme.EventAppTheme
import com.turtleteam.eventapp.navigation.MainNavigationScreen
import com.turtleteam.impl.SpeakerService
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    private val speakerService by inject<SpeakerService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        speakerService.init(this)
        setContent {
            val navController = rememberNavController()
            val settings = koinInject<Settings>()

            val isDark = settings.theme.collectAsState(initial = false)
            EventAppTheme(isDark.value) {
                MainNavigationScreen(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        speakerService.destroy()
        super.onDestroy()
    }
}
