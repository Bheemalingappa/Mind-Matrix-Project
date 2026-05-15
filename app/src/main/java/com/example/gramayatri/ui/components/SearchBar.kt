package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(

    query: String,

    onQueryChange: (String) -> Unit,

    onSearchClick: () -> Unit
) {

    OutlinedTextField(

        value = query,

        onValueChange = {

            onQueryChange(it)
        },

        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF14213D),
                RoundedCornerShape(18.dp)
            ),

        shape = RoundedCornerShape(18.dp),

        placeholder = {

            Text(
                text = "Search bus, route, stop...",
                color = Color.LightGray
            )
        },

        leadingIcon = {

            Icon(

                imageVector = Icons.Default.Search,

                contentDescription = null,

                tint = Color.White
            )
        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = Color(0xFF14213D),

            unfocusedContainerColor = Color(0xFF14213D),

            focusedBorderColor = Color(0xFF2563EB),

            unfocusedBorderColor = Color.Transparent,

            cursorColor = Color.White,

            focusedTextColor = Color.White,

            unfocusedTextColor = Color.White
        ),

        singleLine = true
    )
}