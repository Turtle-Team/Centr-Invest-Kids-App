package com.turtleteam.impl.presentation.register.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    errorService: ErrorService = get()
) {

    val state = viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var checkedState by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            LargeTopAppBar(
                title = { Text(text = "Регистрация") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3EDF7)
                ),//TODO fix hardcode
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackButtonClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }

        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                OutlinedTextField(
                    value = state.value.loginText,
                    singleLine = true,
                    onValueChange = { viewModel.onLoginTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = isError,
                    placeholder = { Text("Введите логин") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                OutlinedTextField(
                    value = state.value.firstNameText,
                    singleLine = true,
                    onValueChange = { viewModel.onFirstNameTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = isError,
                    placeholder = { Text("Введите имя") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                OutlinedTextField(
                    value = state.value.lastNameText,
                    singleLine = true,
                    onValueChange = { viewModel.onLastNameTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = isError,
                    placeholder = { Text("Введите фамилию") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                OutlinedTextField(
                    value = state.value.emailText,
                    singleLine = true,
                    onValueChange = { viewModel.onEmailTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = isError,
                    placeholder = { Text("Введите почту") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                OutlinedTextField(
                    value = state.value.passwordText,
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val iconPainter =
                                painterResource(id = if (passwordHidden) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                            Icon(painter = iconPainter, contentDescription = null)
                        }
                    },
                    singleLine = true,
                    onValueChange = { viewModel.onPasswordTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isError = isError,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    placeholder = { Text("Введите пароль") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = { checkedState = it }
                        )
                        Text(
                            text = "Я согласен с Политикой конфиденциальности",
                            fontSize = 8.sp,
                            color = Color(0xFF6750A4), //TODO fix hardcode
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                Log.e("ajshd", "click")
                            }
                        )
                    }

                    Button(
                        modifier = Modifier.width(114.dp),
                        onClick = {
                            focusManager.clearFocus()
                            if (
                                state.value.loginText == "" ||
                                state.value.firstNameText == "" ||
                                state.value.lastNameText == "" ||
                                state.value.emailText == "" ||
                                state.value.passwordText == ""
                            ) {
                                isError = true
                                scope.launch { errorService.showError("Зполните все поля") }
                            } else {
                                viewModel.onRegisterClick(
                                    UserDTOReceive(
                                        login = state.value.loginText,
                                        password = state.value.passwordText
                                    )
                                )
                            }
                        }) {
                        if (state.value.registerLoadingState == LoadingState.Loading) {
                            CircularProgressIndicator(Modifier.size(24.dp), color = Color.White)
                        } else {
                            Text(text = "Далее")
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 24.dp))
            }
        }
    }
}