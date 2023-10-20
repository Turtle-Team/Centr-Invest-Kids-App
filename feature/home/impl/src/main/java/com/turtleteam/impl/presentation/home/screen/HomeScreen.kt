package com.turtleteam.impl.presentation.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    Text("Home Screen")

    TextButton(
        modifier = Modifier.fillMaxSize().then(modifier),
        onClick = { viewModel.navigateToWelcome() }) {
        Text(text = "Авторизоваться")
    }
}