package com.turtleteam.impl.presentation.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.AccountNavigator
import com.turtleteam.impl.presentation.register.state.RegisterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val navigator: AccountNavigator,
    private val accountService: AccountService,
    private val errorService: ErrorService
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onLoginTextChanged(login: String) {
        _state.update { it.copy(loginText = login) }
    }

    fun onFirstNameTextChanged(firstName: String) {
        _state.update { it.copy(firstNameText = firstName) }
    }

    fun onLastNameTextChanged(lastName: String) {
        _state.update { it.copy(lastNameText = lastName) }
    }

    fun onEmailTextChanged(email: String) {
        _state.update { it.copy(emailText = email) }
    }

    fun onPasswordTextChanged(password: String) {
        _state.update { it.copy(passwordText = password) }
    }

    fun onRegisterClick(user: UserDTOReceive) {
        viewModelScope.launch(Dispatchers.IO) {
           exceptionHandleable(
               executionBlock = {
                   _state.update { it.copy(registerLoadingState = LoadingState.Loading) }
                   accountService.registerUser(user)
               },
               failureBlock = { throwable ->
                   _state.update { it.copy(registerLoadingState = LoadingState.Error(throwable.message.toString())) }
                   errorService.showError(throwable.message.toString())
               },
               conflictBlock = {
                   errorService.showError("Пользователь с таким логином или почтой уже существует. Авторизуйтесь")
               },
               completionBlock = {
                   _state.update { it.copy(registerLoadingState = LoadingState.Success) }
               }
           )
        }
    }
}
