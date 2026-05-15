package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ETAChip(
    eta: String,
    color: Color
) {

    Box(
        modifier = Modifier
            .background(
                color,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
    ) {

        Text(
            text = eta,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}