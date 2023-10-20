package com.turtleteam.eventapp.di.featureModule

import androidx.navigation.Navigator
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.impl.navigation.AssistantNavigationImpl
import com.turtleteam.impl.navigation.AssistantNavigator
import com.turtleteam.impl.presentation.viewModel.AssistantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val assistantModule = module {
    single<AssistantNavigation> { AssistantNavigationImpl() }

    factory { navController ->
        AssistantNavigator(navController.get())
    }

    viewModel { params ->
        AssistantViewModel(params.get())
    }
}