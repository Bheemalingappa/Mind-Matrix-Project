package com.example.gramayatri.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RealtimeIndicator() {

    val infiniteTransition =
        rememberInfiniteTransition(
            label = "pulse"
        )

    val alpha by infiniteTransition.animateFloat(

        initialValue = 0.3f,

        targetValue = 1f,

        animationSpec = infiniteRepeatable(

            animation = tween(900),

            repeatMode = RepeatMode.Reverse
        ),

        label = "alpha"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    Color(0xFF10B981).copy(alpha = alpha),
                    shape = CircleShape
                )
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text = "LIVE",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}