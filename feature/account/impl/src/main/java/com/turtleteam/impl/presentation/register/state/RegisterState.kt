package com.turtleteam.impl.presentation.register.state

import com.turtleteam.core_navigation.state.LoadingState

data class RegisterState(
    val loginText: String = "",
    val firstNameText: String = "",
    val lastNameText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val registerLoadingState: LoadingState = LoadingState.Success
)