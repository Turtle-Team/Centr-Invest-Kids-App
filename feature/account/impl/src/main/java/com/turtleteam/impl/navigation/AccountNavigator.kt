package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.core_navigation.BaseNavigator

class AccountNavigator(
    private val accountNavigation: AccountNavigation,
    private val navController: NavController
) : BaseNavigator(navController) {

    private val baseRoute = accountNavigation.baseRoute

    fun navigateToAuth() {
        navController.navigate(authRoute) {
            launchSingleTop = true
        }
    }

    fun navigateToRegister() {
        navController.navigate(registerRoute) {
            launchSingleTop = true
        }
    }
}