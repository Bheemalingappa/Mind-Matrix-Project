package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF0F172A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Settings",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        SettingsGroup("Preferences")
        SettingsToggleItem(
            icon = Icons.Default.Notifications,
            title = "Push Notifications",
            checked = viewModel.notificationsEnabled,
            onCheckedChange = { 
                viewModel.notificationsEnabled = it
                viewModel.saveSettings()
            }
        )

        SettingsToggleItem(
            icon = Icons.Default.Shield,
            title = "Dark Mode",
            checked = viewModel.darkModeEnabled,
            onCheckedChange = { 
                viewModel.darkModeEnabled = it
                viewModel.saveSettings()
            }
        )

        SettingsGroup("Language")
        SettingsLanguageSelector(
            current = viewModel.selectedLanguage,
            onSelect = { 
                viewModel.selectedLanguage = it
                viewModel.saveSettings()
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Grama-Yatri Mobility Intelligence\nVersion 1.0.4-Industrial",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun SettingsGroup(title: String) {
    Text(
        text = title.uppercase(),
        color = Color(0xFF3B82F6),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@Composable
fun SettingsToggleItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color(0xFF3B82F6))
                Spacer(modifier = Modifier.width(16.dp))
                Text(title, color = Color.White, fontSize = 16.sp)
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF2563EB)
                )
            )
        }
    }
}

@Composable
fun SettingsLanguageSelector(current: String, onSelect: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(if (current == "English") "Kannada" else "English") },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Language, contentDescription = null, tint = Color(0xFF3B82F6))
            Spacer(modifier = Modifier.width(16.dp))
            Text("App Language", color = Color.White, modifier = Modifier.weight(1f))
            Text(current, color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
        }
    }
}