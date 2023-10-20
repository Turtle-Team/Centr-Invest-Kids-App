package com.turtleteam.eventapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.api.navigation.ProfileNavigation
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.BottomNavigationBar
import com.turtleteam.core_view.NavigationItem
import com.turtleteam.core_view.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.compose.koinInject


@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    errorService: ErrorService = get()
) {

    val homeFeature: HomeNavigation = koinInject()
    val eventFeature: EventNavigation = koinInject()
    val profileFeature: ProfileNavigation = koinInject()
    val accountFeature: AccountNavigation = koinInject()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavigationItems = listOf(
        NavigationItem(
            route = homeFeature.baseRoute,
            label = R.string.bottom_navigation_view_home,
            icon = R.drawable.ic_home
        ),
        NavigationItem(
            route = eventFeature.baseRoute,
            label = R.string.bottom_navigation_view_event,
            icon = R.drawable.ic_ticket
        ),
        NavigationItem(
            route = profileFeature.baseRoute,
            label = R.string.bottom_navigation_view_profile,
            icon = R.drawable.ic_profile
        )
    )

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it, actionLabel = "Закрыть")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                routes = bottomNavigationItems,
                currentRoute = currentRoute,
                onClick = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        snackbarHost = { snackbarHostStatet ->
            SnackbarHost(snackbarHostStatet, Modifier.zIndex(2f)) {
                Snackbar(modifier = Modifier.zIndex(2f), snackbarData = it)
            }
        }
    ) { paddingValues ->
        val bottomNavigationViewModifier =
            Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            navController = navController,
            startDestination = homeFeature.baseRoute
        ) {
            register(homeFeature, navController, bottomNavigationViewModifier)
            register(eventFeature, navController, bottomNavigationViewModifier)
            register(profileFeature, navController, bottomNavigationViewModifier)
            register(accountFeature, navController)
        }
    }
}