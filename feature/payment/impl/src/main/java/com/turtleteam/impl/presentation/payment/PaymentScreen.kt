package com.turtleteam.impl.presentation.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.model.PaymentType
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.payment.screen.layout.BetweenLayout
import com.turtleteam.impl.presentation.payment.screen.layout.NumberBillLayout
import com.turtleteam.impl.presentation.payment.screen.layout.NumberCardLayout
import com.turtleteam.impl.presentation.payment.screen.layout.PhoneLayout
import com.turtleteam.impl.presentation.payment.screen.layout.SbpLayout
import com.turtleteam.impl.presentation.payment.viewModel.PaymentViewModel

@Composable
fun PaymentScreen(paymentType: String, viewModel: PaymentViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.onBackButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Перевести", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ), modifier = Modifier.padding(start = 20.dp)
            )
        }

        when (paymentType) {
            PaymentType.PHONE.name -> {
                PhoneLayout(viewModel)
            }

            PaymentType.NUMBER_CARD.name -> {
                NumberCardLayout()
            }

            PaymentType.NUMBER_BILL.name -> {
                NumberBillLayout()
            }

            PaymentType.SBP.name -> {
                SbpLayout()
            }

            PaymentType.BETWEEN_BILL.name -> {
                BetweenLayout()
            }
        }
    }
}