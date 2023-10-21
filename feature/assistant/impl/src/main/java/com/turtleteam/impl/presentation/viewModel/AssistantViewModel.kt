package com.turtleteam.impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.impl.navigation.AssistantNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val navigator: AssistantNavigator
) : ViewModel() {

    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                chat = listOf(
                    Message(
                        "asdasdasdasd",
                        Sender.ASSISTANT
                    ),
                    Message(
                        "asda;lsmdlasmd;alsdas;dasld;asdasdasd",
                        Sender.ASSISTANT
                    ),
                    Message(
                        "asdasdasdasd",
                        Sender.USER
                    ),
                    Message(
                        "asdasdasasldjalsdkjasdas;ld,a;lsd,;alsd,ma;sldmdasd",
                        Sender.ASSISTANT
                    ),
                    Message(
                        "asdasas;dlmas;dlma;sdasldasdasd",
                        Sender.USER
                    ),
                )
            )
        }
        viewModelScope.launch {
            while (true) {
                _state.update {
                    it.copy(
                        it.chat + Message(
                            "asdasas;dlmas;dlma;sdasldasdasd",
                            Sender.USER
                        )
                    )
                }
                delay(2000)
            }
        }
        viewModelScope.launch {
            while (true) {
                _state.update {
                    it.copy(
                        it.chat + Message(
                            "asdasas;dlmas;dlma;sdasldasdasd",
                            Sender.ASSISTANT
                        )
                    )
                }
                delay(1000)
            }
        }
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onMessageSend(text: String) {

    }
}