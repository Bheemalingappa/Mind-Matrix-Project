package com.example.gramayatri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.gramayatri.navigation.AppNavigation
import com.google.firebase.database.FirebaseDatabase
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gramayatri.ui.theme.GramaYatriTheme
import com.example.gramayatri.ui.theme.GramaYatriTheme
class MainActivity : ComponentActivity() {

   override fun onCreate(
    savedInstanceState: Bundle?
) {

    super.onCreate(savedInstanceState)

    if (

        ContextCompat.checkSelfPermission(

            this,

            Manifest.permission.ACCESS_FINE_LOCATION

        ) != PackageManager.PERMISSION_GRANTED
    ) {

        ActivityCompat.requestPermissions(

            this,

            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ),

            1001
        )
    }

    setContent {

        GramaYatriTheme {

            AppNavigation()
        }
    }
}
}
