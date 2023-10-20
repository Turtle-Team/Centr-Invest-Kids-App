package com.turtleteam.impl.presentation.payment.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.PaymentNavigator

class PaymentViewModel(
    private val navigator: PaymentNavigator
): ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}