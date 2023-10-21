package com.turtleteam.impl.presentation.home.state

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.CardShort

data class HomeState(
    val cards: List<CardShort>? = null
)