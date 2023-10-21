package com.turtleteam.impl.presentation.detail_card.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.DetailCardNavigator
import com.turtleteam.impl.presentation.detail_card.state.DetailCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailCardViewModel(
    private val navigator: DetailCardNavigator
): ViewModel() {

    private val _state = MutableStateFlow(DetailCardState())
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}