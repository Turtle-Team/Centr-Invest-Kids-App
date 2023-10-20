package com.turtleteam.impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.AssistantNavigator

class AssistantViewModel(
    private val navigator: AssistantNavigator
): ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}