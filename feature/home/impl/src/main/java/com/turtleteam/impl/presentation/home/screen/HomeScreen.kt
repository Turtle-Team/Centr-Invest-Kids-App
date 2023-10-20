package com.turtleteam.impl.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.api.model.Bill
import com.turtleteam.api.model.Card
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    val cardList = listOf(
        Card(
            numCards = "2200 1706 0156 6172",
            dateClose = "08/28",
            code = "231",
            limitBegin = "10 000",
            limitEnd = "3 723",
            bill = Bill(
                title = "ГБПОУ РО \"РКСИ\" сотрудники",
                owner = "Egor Lyadsky",
                number = "",
                dateOpen = "09.08.2023",
                agreement = "",
                remainder = "91 234"
            )
        ),
        Card(
            numCards = "2200 3213 5345 1233",
            dateClose = "08/28",
            code = "231",
            limitBegin = "75 000",
            limitEnd = "42 712",
            bill = Bill(
                title = "ГБПОУ РО \"РИНХ\" сотрудники",
                owner = "Egor Lyadsky",
                number = "",
                dateOpen = "09.08.2023",
                agreement = "",
                remainder = "1 275"
            )
        )
    )



    Column {
//        Text("Home Screen")
//
//        TextButton(
//            modifier = Modifier,
//            onClick = { viewModel.navigateToWelcome() }) {
//            Text(text = "Авторизоваться")
//        }
//
//        TextButton(onClick = { viewModel.navigateToAssistant() }) {
//            Text(text = ("Ассистент"))
//        }


        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(items = cardList) { card ->
                CardView(card = card) {
                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(modifier: Modifier = Modifier, card: Card, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick() }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = card.bill.title)
                Text(text = card.bill.remainder)
            }
            Text(text = card.numCards, modifier = Modifier.padding(top = 8.dp))
        }
    }
}