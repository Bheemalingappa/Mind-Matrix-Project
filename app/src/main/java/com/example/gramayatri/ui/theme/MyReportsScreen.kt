package com.example.gramayatri.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.*

data class BusReport(
    val busNumber: String = "",
    val routeName: String = "",
    val stopName: String = "",
    val eta: String = ""
)

@Composable
fun MyReportsScreen() {

    val reports = remember {
        mutableStateListOf<BusReport>()
    }

    LaunchedEffect(Unit) {

        val ref = FirebaseDatabase
            .getInstance()
            .getReference("bus_reports")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                reports.clear()

                for (reportSnapshot in snapshot.children) {

                    val report =
                        reportSnapshot.getValue(BusReport::class.java)

                    if (report != null) {
                        reports.add(report)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp),

        contentPadding = PaddingValues(bottom = 100.dp)
    ) {

        items(reports) { report ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Bus: ${report.busNumber}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(text = "Route: ${report.routeName}")

                    Text(text = "Stop: ${report.stopName}")

                    Text(text = "ETA: ${report.eta}")
                }
            }
        }
    }
}