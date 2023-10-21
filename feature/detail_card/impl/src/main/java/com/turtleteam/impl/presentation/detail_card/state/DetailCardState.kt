package com.turtleteam.impl.presentation.detail_card.state

data class DetailCardState(
    val isDetailsShown: Boolean = false,
    val limitBegin: Int? = null,
    val limitEnd: Int? = null
)