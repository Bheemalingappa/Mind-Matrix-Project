package com.example.gramayatri.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gramayatri.data.BusPing
import com.example.gramayatri.data.FirebaseRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodayPingHistoryScreen() {

    val repository = remember {
        FirebaseRepository()
    }

    val busPings = remember {
        mutableStateListOf<BusPing>()
    }

    LaunchedEffect(Unit) {

        repository.listenToBusPings(

            onDataLoaded = { pings ->

                busPings.clear()

                val todayPings =
                    pings.sortedByDescending { it.timestamp }

                busPings.addAll(todayPings)
            },

            onError = {

            }
        )
    }

    Scaffold { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(busPings) { ping ->

                PingHistoryCard(ping)
            }
        }
    }
}

@Composable
fun PingHistoryCard(ping: BusPing) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = ping.busId,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = ping.routeName,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "ETA ${ping.etaMinutes} mins",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = formatPingTime(ping.timestamp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

fun formatPingTime(timestamp: Long): String {

    val formatter =
        SimpleDateFormat("hh:mm a", Locale.getDefault())

    return formatter.format(Date(timestamp))
}