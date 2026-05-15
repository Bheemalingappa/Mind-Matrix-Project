package com.example.gramayatri.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(

    selectedRole: String = "",

    onLoginSuccess: () -> Unit,

    onCreateAccountClick: () -> Unit

) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF06111F),
            Color(0xFF0B1727),
            Color(0xFF111827)
        )
    )

    val roleColor = when (selectedRole) {

        "passenger" -> Color(0xFF4F46E5)

        "conductor" -> Color(0xFF0F766E)

        "admin" -> Color(0xFFF59E0B)

        else -> Color(0xFF4F46E5)
    }

    val roleIcon = when (selectedRole) {

        "passenger" -> Icons.Default.Person

        "conductor" -> Icons.Default.DirectionsBus

        "admin" -> Icons.Default.AdminPanelSettings

        else -> Icons.Default.Person
    }

    val roleTitle = when (selectedRole) {

        "passenger" -> "Passenger Access"

        "conductor" -> "Conductor Operations Login"

        "admin" -> "Admin Control Access"

        else -> "Platform Login"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(92.dp)
                .background(
                    roleColor,
                    shape = RoundedCornerShape(28.dp)
                ),

            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = roleIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(46.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = roleTitle,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Realtime Mobility Intelligence Platform",
            color = Color(0xFFCBD5E1),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(36.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(30.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF172033)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 14.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = "Secure Authentication",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,

                    onValueChange = {
                        email = it
                    },

                    label = {
                        Text("Email")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(18.dp),

                    singleLine = true,

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedBorderColor = roleColor,
                        unfocusedBorderColor = Color(0xFF475569),

                        focusedContainerColor = Color(0xFF0F172A),
                        unfocusedContainerColor = Color(0xFF0F172A),

                        cursorColor = Color.White,

                        focusedLabelColor = roleColor,
                        unfocusedLabelColor = Color(0xFF94A3B8)
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = password,

                    onValueChange = {
                        password = it
                    },

                    label = {
                        Text("Password")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    visualTransformation = PasswordVisualTransformation(),

                    singleLine = true,

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedBorderColor = roleColor,
                        unfocusedBorderColor = Color(0xFF475569),

                        focusedContainerColor = Color(0xFF0F172A),
                        unfocusedContainerColor = Color(0xFF0F172A),

                        cursorColor = Color.White,

                        focusedLabelColor = roleColor,
                        unfocusedLabelColor = Color(0xFF94A3B8)
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {

                        if (
                            email.isNotEmpty() &&
                            password.isNotEmpty()
                        ) {

                            auth.signInWithEmailAndPassword(
                                email,
                                password
                            ).addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    onLoginSuccess()

                                } else {

                                    Toast.makeText(
                                        context,
                                        "Login failed: ${task.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        } else {

                            Toast.makeText(
                                context,
                                "Please enter email and password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = roleColor
                    )
                ) {

                    Text(
                        text = "Secure Login",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (selectedRole == "passenger") {

                    Spacer(modifier = Modifier.height(18.dp))

                    TextButton(
                        onClick = {
                            onCreateAccountClick()
                        },

                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Create Passenger Account",
                            color = roleColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}