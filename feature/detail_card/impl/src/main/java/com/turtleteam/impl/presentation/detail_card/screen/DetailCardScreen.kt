package com.turtleteam.impl.presentation.detail_card.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.detail_card.viewModel.DetailCardViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun DetailCardScreen(cardId: String, viewModel: DetailCardViewModel) {
    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .clipToBounds(),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Row {
                IconButton(onClick = { viewModel.onBackButtonClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                }

                CardInfo(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 16.dp, bottom = 24.dp),
                    name = "Egor Lyadsky"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentPadding = PaddingValues(vertical = 28.dp)
        ) {

            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp),
                ) {
                    RequisitesView(numberCode = "", date = "", code = "")
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp),
                    text = "История операций",
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(600),
                )
            }

            items(count = 8) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Крайнюков А.",
                            fontSize = 14.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(600),
                        )
                        Text(
                            text = "Переводы",
                            fontSize = 12.sp,
                            lineHeight = 28.sp,
                        )
                    }
                    Text(
                        text = "-85 ₽ ",
                        fontSize = 14.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                    )
                }
            }
        }
    }
}

@Composable
fun RequisitesView(numberCode: String, date: String, code: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .shadow(8.dp)
    ) {

    }

    Card(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(top = 24.dp, bottom = 17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Реквизиты карты",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Показать",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Column(Modifier.fillMaxWidth()) {

                HiddenText(Modifier.fillMaxWidth(), text = "•••• •••• •••• 6789")

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    HiddenText(Modifier.fillMaxWidth(), text = "•••")
                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    HiddenText(Modifier.fillMaxWidth(), text = "••/••")
                }
            }
        }
    }
}

@Composable
fun HiddenText(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier
            .background(Color(0xFFEFEFEF))
            .padding(horizontal = 10.dp, vertical = 14.dp)
    )
}

@Composable
fun CardInfo(modifier: Modifier = Modifier, name: String) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF4DB45F))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 15.dp, top = 21.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plata), contentDescription = null,
                modifier = Modifier.size(width = 60.dp, height = 40.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_mir), contentDescription = null,
                modifier = Modifier.size(width = 62.dp, height = 18.dp),
                tint = Color.White
            )
        }


        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 76.dp, start = 26.dp, bottom = 22.dp, end = 26.dp)
        ) {
            Text(
                text = "Имя владельца",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
            )
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }
    }
}