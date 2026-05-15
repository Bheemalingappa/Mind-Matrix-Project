package com.example.gramayatri.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonGroup(

    modifier: Modifier = Modifier,

    onLocationClick: () -> Unit,

    onNavigationClick: () -> Unit
) {

    Column(

        modifier = modifier,

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        FloatingActionButton(

            onClick = onLocationClick,

            containerColor = Color(0xFF172033)
        ) {

            Icon(

                imageVector = Icons.Default.LocationOn,

                contentDescription = null,

                tint = Color.White,

                modifier = Modifier.size(24.dp)
            )
        }

        FloatingActionButton(

            onClick = onNavigationClick,

            containerColor = Color(0xFF172033)
        ) {

            Icon(

                imageVector = Icons.Default.Navigation,

                contentDescription = null,

                tint = Color.White,

                modifier = Modifier.size(24.dp)
            )
        }
    }
}