package com.turtleteam.impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Speaker
import com.turtleteam.api.data.api.repository.AssistantRepository
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.AssistantNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val navigator: AssistantNavigator,
    private val rep: AssistantRepository,
    private val speaker: Speaker
) : ViewModel() {

    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                chat = listOf(
                    Message(
                        "Я помню чудное мгновенье:\n" +
                                "Передо мной явилась ты,\n" +
                                "Как мимолетное виденье,\n" +
                                "Как гений чистой красоты.\n" +
                                "\n" +
                                "В томленьях грусти безнадежной,\n" +
                                "В тревогах шумной суеты,\n" +
                                "Звучал мне долго голос нежный\n" +
                                "И снились милые черты.\n" +
                                "\n" +
                                "Шли годы. Бурь порыв мятежный\n" +
                                "Рассеял прежние мечты,\n" +
                                "И я забыл твой голос нежный,\n" +
                                "Твои небесные черты.\n" +
                                "\n" +
                                "В глуши, во мраке заточенья\n" +
                                "Тянулись тихо дни мои\n" +
                                "Без божества, без вдохновенья,\n" +
                                "Без слез, без жизни, без любви.\n" +
                                "\n" +
                                "Душе настало пробужденье:\n" +
                                "И вот опять явилась ты,\n" +
                                "Как мимолетное виденье,\n" +
                                "Как гений чистой красоты.\n" +
                                "\n" +
                                "И сердце бьется в упоенье,\n" +
                                "И для него воскресли вновь\n" +
                                "И божество, и вдохновенье,\n" +
                                "И жизнь, и слезы, и любовь.",
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
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onMessageClick(text: String) {
        speaker.speak(text)
    }

    fun onMessageSend(text: String) {
        _state.update { it.copy(chat = it.chat + Message(text, Sender.USER),textFieldEnabled = false) }
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(chat = it.chat + Message(rep.sendMessage(text), Sender.ASSISTANT)) }
                },
                completionBlock = {
                    _state.update { it.copy(textFieldEnabled = true) }
                }
            )
        }
    }
}