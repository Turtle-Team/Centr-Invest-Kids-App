package com.turtleteam.impl.presentation.detail_card.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.DetailCardNavigator
import com.turtleteam.impl.presentation.detail_card.state.DetailCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailCardViewModel(
    private val navigator: DetailCardNavigator,
): ViewModel() {

    private val _state = MutableStateFlow(DetailCardState(
        limitBegin = 100,
        limitEnd = 100
    ))
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
    fun onShowRequisites(){
        _state.update { it.copy(isDetailsShown = true) }
    }
}