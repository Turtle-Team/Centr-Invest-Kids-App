package com.turtleteam.core_view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


data class NavigationItem(
    val route: String,
    @StringRes
    val label: Int,
    @DrawableRes
    val icon: Int
)

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    routes: List<NavigationItem>,
    onClick: (route: String) -> Unit
) {
    var lastSelectedBtn by rememberSaveable { mutableStateOf(currentRoute) }

    NavigationBar(
        modifier =Modifier.zIndex(0f)
    ) {
        routes.forEach { item ->
            NavigationBarItem(
                selected = lastSelectedBtn == item.route,
                label = {
                    Text(
                        stringResource(id = item.label),
                        color = if (lastSelectedBtn == item.route) Color(0xFF1D192B) else Color(
                            0xFF49454F
                        )
                    )
                },
                onClick = {
                    if (routes.map { it.route }.contains(currentRoute)) onClick(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                        tint = if (lastSelectedBtn == item.route) Color(0xFF1D192B) else Color(
                            0xFF49454F
                        ),
                        modifier = Modifier.size(24.dp)
                    )
                })
        }
    }

    LaunchedEffect(key1 = currentRoute, block = {
        if (routes.map { it.route }.contains(currentRoute)) lastSelectedBtn = currentRoute
    })
}