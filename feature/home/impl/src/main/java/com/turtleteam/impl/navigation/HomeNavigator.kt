package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.HomeNavigation

class HomeNavigator (
    homeNavigation: HomeNavigation,
    accountNavigation: AccountNavigation,
    private val navController: NavController
) {
    private val baseRoute = homeNavigation.baseRoute
    private val accountRoute = accountNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(accountRoute)
    }
}