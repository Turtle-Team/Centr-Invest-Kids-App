package com.turtleteam.impl.presentation.pincode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.Speaker
import com.turtleteam.impl.navigation.AccountNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal const val PINCODE_LENGTH = 4

class PincodeViewModel(
    private val settings: Settings,
    private val speaker: Speaker,
    private val navigator: AccountNavigator
) : ViewModel() {
    private val _state = MutableStateFlow(PincodeState())
    val state = _state.asStateFlow()

    private var pincode: String? = null
    private var inputEnabled = true

    init {
        viewModelScope.launch(Dispatchers.IO) {
            pincode = settings.getPincode()
        }
    }

    fun onPincodeTextChanged(char: String) {
        if (inputEnabled)
            _state.update {
                if (it.pincode.length < PINCODE_LENGTH) {
                    val newStr = _state.value.pincode + char
                    it.copy(
                        pincode = newStr,
                        saveBtnEnabled = (newStr.length >= PINCODE_LENGTH && pincode == null)
                    )
                } else {
                    if (pincode == it.pincode)
                        navigator.navigateToHome()
                    it
                }
            }
    }

    fun onBackspaceClick() {
        if (inputEnabled)
            _state.update {
                it.copy(pincode = _state.value.pincode.dropLast(1), saveBtnEnabled = false)
            }
    }

    fun onDoneClick() {
        inputEnabled = false
        viewModelScope.launch(Dispatchers.IO) {
            settings.setPincode(_state.value.pincode)
            withContext(Dispatchers.Main) {
                navigator.navigateToHome()
            }
        }
    }
    fun onSpeakerClick() {
        speaker.speak("что что что что")
    }
}