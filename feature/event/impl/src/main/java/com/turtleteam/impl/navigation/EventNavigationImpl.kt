package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.impl.presentation.event.screen.EventScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class EventNavigationImpl : EventNavigation {

    override val baseRoute: String = "event"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val navigator =
                koinInject<EventNavigator>(parameters = { parametersOf(navController) })
            EventScreen(
                modifier,
                viewModel = koinViewModel(parameters = { parametersOf(navigator) })
            )
        }
    }
}