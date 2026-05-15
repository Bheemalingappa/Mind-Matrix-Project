package com.example.gramayatri.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gramayatri.data.Alert
import com.example.gramayatri.data.AlertRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AlertScreen() {

    val repository = remember {
        AlertRepository()
    }

    val alerts = remember {
        mutableStateListOf<Alert>()
    }

    val loading = remember {
        mutableStateOf(true)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        repository.listenToAlerts(

            onDataLoaded = { alertList ->

                alerts.clear()
                alerts.addAll(alertList)

                loading.value = false
            },

            onError = { error ->

                errorMessage.value = error
                loading.value = false
            }
        )
    }

    Scaffold { paddingValues ->

        when {

            loading.value -> {

                LoadingSection(paddingValues)
            }

            errorMessage.value.isNotEmpty() -> {

                ErrorSection(
                    message = errorMessage.value,
                    paddingValues = paddingValues
                )
            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),

                    contentPadding = PaddingValues(16.dp),

                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(alerts) { alert ->

                        AlertCard(alert)
                    }
                }
            }
        }
    }
}

@Composable
fun AlertCard(alert: Alert) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = alert.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = alert.message,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Type: ${alert.type}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = formatTimestamp(alert.timestamp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun LoadingSection(
    paddingValues: PaddingValues
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),

        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator()
    }
}

@Composable
fun ErrorSection(
    message: String,
    paddingValues: PaddingValues
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )
    }
}

fun formatTimestamp(timestamp: Long): String {

    val formatter =
        SimpleDateFormat(
            "dd MMM yyyy, hh:mm a",
            Locale.getDefault()
        )

    return formatter.format(Date(timestamp))
}