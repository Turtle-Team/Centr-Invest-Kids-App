package com.turtleteam.impl.presentation.viewModel

data class AssistantState(
    val chat: List<Message> = listOf(),
    val textFieldEnabled: Boolean = true
)

data class Message(
    val text: String,
    val sender: Sender
)
enum class Sender {
    USER,ASSISTANT
}