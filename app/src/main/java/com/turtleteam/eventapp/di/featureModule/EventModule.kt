package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.impl.navigation.EventNavigationImpl
import com.turtleteam.impl.navigation.EventNavigator
import com.turtleteam.impl.presentation.event.viewModel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    single<EventNavigation> { EventNavigationImpl() }
    factory { navController ->
        EventNavigator(get(), navController.get())
    }
    viewModel { navigator ->
        EventViewModel(navigator.get())
    }
}