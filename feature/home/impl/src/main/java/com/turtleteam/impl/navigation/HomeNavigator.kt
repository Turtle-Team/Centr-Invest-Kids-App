package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.api.navigation.HomeNavigation

class HomeNavigator (
    homeNavigation: HomeNavigation,
    accountNavigation: AccountNavigation,
    assistantNavigation: AssistantNavigation,
    private val navController: NavController
) {
    private val baseRoute = homeNavigation.baseRoute
    private val accountRoute = accountNavigation.baseRoute
    private val assistantRoute = assistantNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(accountRoute)
    }

    fun navigateToAssistant() {
       navController.navigate(assistantRoute)
    }
}