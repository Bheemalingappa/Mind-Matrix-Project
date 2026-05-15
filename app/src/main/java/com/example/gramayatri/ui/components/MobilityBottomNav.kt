package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MobilityBottomNav(

    modifier: Modifier = Modifier,

    selectedIndex: Int,

    onItemSelected: (Int) -> Unit
) {

    val icons = listOf(

        Icons.Default.Home,
        Icons.Default.Timeline,
        Icons.Default.Route,
        Icons.Default.Notifications,
        Icons.Default.Person
    )

    Row(

        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp)
            .background(
                Color(0xFF172033),
                RoundedCornerShape(28.dp)
            )
            .padding(vertical = 10.dp),

        horizontalArrangement =
            Arrangement.SpaceEvenly
    ) {

        icons.forEachIndexed { index, icon ->

            IconButton(

                onClick = {

                    onItemSelected(index)
                }
            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint =
                        if (selectedIndex == index)
                            Color.White
                        else
                            Color(0xFF94A3B8)
                )
            }
        }
    }
}