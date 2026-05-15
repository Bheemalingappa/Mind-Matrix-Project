package com.example.gramayatri.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.gramayatri.data.RouteRepository
import com.example.gramayatri.data.RouteStop
import com.example.gramayatri.utils.EtaCalculator

@Composable
fun RouteTimelineScreen() {

    val routeRepository = remember {
        RouteRepository()
    }

    val routeStops = remember {
        mutableStateListOf<RouteStop>()
    }

    val loading = remember {
        mutableStateOf(true)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    /**
     * Temporary MVP-safe current stop.
     *
     * Later this can come from:
     * - live GPS
     * - Firebase bus state
     * - route progression logic
     */
    val currentStopIndex = 0

    LaunchedEffect(Unit) {

        routeRepository.listenToRouteStops(
            routeId = "route_1",

            onDataLoaded = { stops ->

                routeStops.clear()
                routeStops.addAll(stops)

                loading.value = false
            },

            onError = { error ->

                errorMessage.value = error

                loading.value = false
            }
        )
    }

    val etaMap = EtaCalculator.calculateEtas(
        currentStopIndex = currentStopIndex,
        routeStops = routeStops
    )

    Scaffold { paddingValues ->

        when {

            loading.value -> {

                LoadingView(paddingValues)
            }

            errorMessage.value.isNotEmpty() -> {

                ErrorView(
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

                    itemsIndexed(routeStops) { index, stop ->

                        val statusText: String
                        val etaText: String

                        when {

                            index < currentStopIndex -> {

                                statusText = "✅ Reached"

                                etaText = ""
                            }

                            index == currentStopIndex -> {

                                statusText = "⏳ Current Stop"

                                etaText =
                                    etaMap[stop.stopName]
                                        ?.let {
                                            EtaCalculator.formatEta(it)
                                        } ?: ""
                            }

                            else -> {

                                statusText = "🔵 Upcoming"

                                etaText =
                                    etaMap[stop.stopName]
                                        ?.let {
                                            EtaCalculator.formatEta(it)
                                        } ?: ""
                            }
                        }

                        RouteStopCard(
                            stopName = stop.stopName,
                            status = statusText,
                            eta = etaText
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RouteStopCard(
    stopName: String,
    status: String,
    eta: String
) {

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
                text = stopName,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium
            )

            if (eta.isNotEmpty()) {

                Text(
                    text = eta,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun LoadingView(
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
fun ErrorView(
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