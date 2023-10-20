package com.turtleteam.api.model


data class Card (
    val numCards: String,
    val dateClose: String,
    val code: String,
    val limitBegin: String,
    val limitEnd: String,
    val bill: Bill
)

data class Bill(
    val title: String,
    val owner: String,
    val number: String,
    val dateOpen: String,
    val agreement: String,
    val remainder: String
)
