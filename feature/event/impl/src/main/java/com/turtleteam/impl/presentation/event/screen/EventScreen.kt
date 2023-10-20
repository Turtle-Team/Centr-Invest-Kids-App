package com.turtleteam.impl.presentation.event.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.turtleteam.impl.presentation.event.viewModel.EventViewModel

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    viewModel: EventViewModel) {

    Text(
        modifier = Modifier.fillMaxSize().then(modifier),
        text = "Event screen"
    )
}