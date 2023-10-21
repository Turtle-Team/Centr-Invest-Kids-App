package com.turtleteam.core_view.view.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun DetailCardInfo(modifier: Modifier = Modifier, name: String) {
    Column(
        Modifier
            .then(modifier)
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