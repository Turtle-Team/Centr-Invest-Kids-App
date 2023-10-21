package com.turtleteam.impl.presentation.home.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R.drawable
import com.turtleteam.impl.presentation.home.screen.component.CardView
import com.turtleteam.impl.presentation.home.screen.component.SmallCardView
import com.turtleteam.impl.presentation.home.screen.component.cardList
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

data class PaymentVariant(
    val label: String,
    @DrawableRes
    val icon: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val pagerState = rememberPagerState { cardList.size }
    val progress = remember { mutableFloatStateOf(0f) }

    val paymentVariantList = listOf(
        PaymentVariant(
            label = "По номеру телефона",
            icon = drawable.ic_phone,
        ),
        PaymentVariant(
            label = "По номеру карты",
            icon = drawable.ic_card
        ),
        PaymentVariant(
            label = "По номеру карты",
            icon = drawable.ic_ruble
        ),
        PaymentVariant(
            label = "По номеру карты",
            icon = drawable.ic_sbp
        ),
        PaymentVariant(
            label = "По номеру карты",
            icon = drawable.ic_between_bill
        )
    )


    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(Color(0xFFE8F5E9))
            .clipToBounds(),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val scale = (progress.floatValue * 10f) * 0.02f
            HorizontalPager(
                modifier = Modifier
                    .progress { progress.floatValue = it }
                    .alpha(progress.floatValue)
                    .padding(top = 105.dp)
                    .scale(0.8f + scale),
                state = pagerState,
                contentPadding = PaddingValues(16.dp),
                pageSpacing = 16.dp
            ) {
                CardView(card = cardList[it]) {
                    viewModel.navigateToDetailCard(cardList[it].numCards)
                }
            }
            Row(
                modifier = Modifier
                    .pin()
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    painter = painterResource(id = drawable.ic_user_circle),
                    contentDescription = "",
                    tint = Color(0xFF00602A)
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Зубенко Михаил",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                )
                Icon(
                    modifier = Modifier.offset(y = 2.dp),
                    painter = painterResource(id = drawable.ic_arrow_right),
                    contentDescription = "",
                    tint = Color(0xFF00602A)
                )
            }
        }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentPadding = PaddingValues(vertical = 28.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Переводы",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                )
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(top = 20.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = paymentVariantList) { paymentVariant ->
                        SmallCardView(
                            icon = paymentVariant.icon,
                            text = paymentVariant.label,
                        ) {

                        }
                    }
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
