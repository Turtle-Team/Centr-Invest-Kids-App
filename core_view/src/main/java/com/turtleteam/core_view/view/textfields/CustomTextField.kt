package com.turtleteam.core_view.view.textfields

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    backgroundColor: Color = Color.White,
    iconColor: Color = Color(0xFF00602A),
    @DrawableRes icon: Int = R.drawable.ic_phone,
    value: String,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = style,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, shape)
                    .background(backgroundColor)
                    .padding(horizontal = 10.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon), contentDescription = "",
                    tint = iconColor
                )
                Box {
                    if (value.isEmpty())
                        Text(
                            text = "введите номер или имя",
                            fontSize = 12.sp,
                            lineHeight = 28.sp,
                            color = Color(0x4D1F1F1F),
                        )
                    innerTextField()
                }
            }
        }
    )
}