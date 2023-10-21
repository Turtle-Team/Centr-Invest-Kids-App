package com.turtleteam.impl.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.api.model.PaymentType
import com.turtleteam.impl.navigation.HomeNavigator

class HomeViewModel(private val navigator: HomeNavigator) : ViewModel() {

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }

    fun navigateToAssistant() {
        navigator.navigateToAssistant()
    }

    fun navigateToPayment(paymentType: PaymentType) {
        navigator.navigateToPayment(paymentType)
    }

    fun navigateToDetailCard(cardId: String) {
        navigator.navigateToDetailCard(cardId)
    }
}