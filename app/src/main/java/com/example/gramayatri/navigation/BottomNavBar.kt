package com.example.gramayatri.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(

    selectedIndex: Int,

    onItemSelected: (Int) -> Unit

) {

    val items = listOf(
        Icons.Default.Home,
        Icons.Default.Route,
        Icons.Default.DirectionsBus,
        Icons.Default.Notifications,
        Icons.Default.Person
    )

    NavigationBar(

        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .background(Color.Transparent),

        containerColor = Color(0xFF14213D),

        tonalElevation = 0.dp
    ) {

        items.forEachIndexed { index, icon ->

            NavigationBarItem(

                selected = selectedIndex == index,

                onClick = {

                    onItemSelected(index)
                },

                icon = {

                    Icon(

                        imageVector = icon,

                        contentDescription = null,

                        tint =
                            if (selectedIndex == index)
                                Color.White
                            else
                                Color(0xFF94A3B8)
                    )
                },

                colors = NavigationBarItemDefaults.colors(

                    indicatorColor = Color(0xFF2563EB)
                )
            )
        }
    }
}