package com.turtleteam.impl.presentation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.turtleteam.impl.presentation.viewModel.AssistantViewModel

@Composable
fun AssistantScreen(viewModel: AssistantViewModel) {

    Text("Assistant screen")
    Button(onClick = { viewModel.onBackButtonClick() }) {
        Text("Назад")
    }
}