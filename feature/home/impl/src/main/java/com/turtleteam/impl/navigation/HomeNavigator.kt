package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.PaymentNavigation
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.api.navigation.HomeNavigation

class HomeNavigator (
    homeNavigation: HomeNavigation,
    accountNavigation: AccountNavigation,
    assistantNavigation: AssistantNavigation,
    paymentNavigation: PaymentNavigation,
    private val navController: NavController
) {
    private val baseRoute = homeNavigation.baseRoute
    private val accountRoute = accountNavigation.baseRoute
    private val assistantRoute = assistantNavigation.baseRoute
    private val paymentRoute = paymentNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(accountRoute)
    }

    fun navigateToAssistant() {
       navController.navigate(assistantRoute)
    }

    fun navigateToPayment() {
        navController.navigate(paymentRoute)
    }
}