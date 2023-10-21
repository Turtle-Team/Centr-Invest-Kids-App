package com.turtleteam.core_view.view.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun AssistantTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "перевести деньги",
    style: TextStyle = TextStyle.Default,
    singleLine: Boolean = false,
    onMicClick: () -> Unit
) {
    val shape = RoundedCornerShape(100.dp)
    BasicTextField(
        modifier = Modifier
            .height(48.dp)
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        textStyle = style,
        singleLine = singleLine
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, shape)
                .background(Color.White, shape)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty())
                    Text(
                        text = placeholder,
                        fontSize = style.fontSize,
                        lineHeight = style.lineHeight,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF76786B)
                    )
                innerTextField()
            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF42C24F), CircleShape)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple()
                    ) {
                      onMicClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_microphone),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}