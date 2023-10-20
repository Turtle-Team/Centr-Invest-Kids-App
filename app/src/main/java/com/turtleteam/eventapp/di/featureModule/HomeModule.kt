package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.impl.navigation.HomeNavigationImpl
import com.turtleteam.impl.navigation.HomeNavigator
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<HomeNavigation> { HomeNavigationImpl() }
    factory { navController ->
        HomeNavigator(get(), get(), navController.get())
    }
    viewModel { navigator ->
        HomeViewModel(navigator.get())
    }
}