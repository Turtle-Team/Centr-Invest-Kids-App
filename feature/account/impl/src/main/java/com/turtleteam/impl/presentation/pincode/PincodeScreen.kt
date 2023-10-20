package com.turtleteam.impl.presentation.pincode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.turtleteam.core_view.R.drawable.ic_speaker
import com.turtleteam.core_view.view.pincode.PincodeIndicators
import com.turtleteam.core_view.view.pincode.PincodeKeyboard
import com.turtleteam.impl.R
import com.turtleteam.impl.presentation.pincode.viewmodel.PINCODE_LENGTH
import com.turtleteam.impl.presentation.pincode.viewmodel.PincodeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PincodeScreen(
    viewModel: PincodeViewModel
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeTopAppBar(
            title = { Text(text = "Пин-код") },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFFE8F5E9)),
            actions = {
                IconButton(onClick = { viewModel.onSpeakerClick() }) {
                    Icon(painter = painterResource(id = ic_speaker), contentDescription = "speaker")
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        PincodeIndicators(pincode = state.value.pincode.length, length = PINCODE_LENGTH)
        Spacer(modifier = Modifier.weight(1f))
        PincodeKeyboard(
            onClick = { viewModel.onPincodeTextChanged(it) },
            onDeleteClick = { viewModel.onBackspaceClick() },
            onDoneClick = if (state.value.saveBtnEnabled) {
                { viewModel.onDoneClick() }
            } else null)
        Spacer(modifier = Modifier.weight(1f))
    }
}