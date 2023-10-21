package com.turtleteam.impl.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.HomeNavigator

class HomeViewModel(private val navigator: HomeNavigator) : ViewModel() {

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }

    fun navigateToAssistant() {
        navigator.navigateToAssistant()
    }

    fun navigateToPayment() {
        navigator.navigateToPayment()
    }

    fun navigateToDetailCard(cardId: String) {
        navigator.navigateToDetailCard(cardId)
    }
}