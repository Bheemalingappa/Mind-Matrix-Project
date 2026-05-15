package com.example.gramayatri.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
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
import com.google.firebase.database.FirebaseDatabase

@Composable
fun CreateAccountScreen(
    onLoginClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    val database = FirebaseDatabase.getInstance()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF071120),
            Color(0xFF0F172A),
            Color(0xFF111827)
        )
    )

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
                .size(90.dp)
                .background(
                    Color(0xFF4F46E5),
                    shape = RoundedCornerShape(26.dp)
                ),

            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.PersonAdd,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(42.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Create Passenger Account",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Join the Grama-Yatri realtime mobility network",
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
                defaultElevation = 12.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = "Passenger Registration",
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
                        Text(
                            "Email",
                            color = Color(0xFFCBD5E1)
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedBorderColor = Color(0xFF4F46E5),
                        unfocusedBorderColor = Color(0xFF475569),

                        focusedContainerColor = Color(0xFF0F172A),
                        unfocusedContainerColor = Color(0xFF0F172A),

                        cursorColor = Color.White,

                        focusedLabelColor = Color(0xFF4F46E5),
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
                        Text(
                            "Password",
                            color = Color(0xFFCBD5E1)
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),

                    visualTransformation = PasswordVisualTransformation(),

                    singleLine = true,

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedBorderColor = Color(0xFF4F46E5),
                        unfocusedBorderColor = Color(0xFF475569),

                        focusedContainerColor = Color(0xFF0F172A),
                        unfocusedContainerColor = Color(0xFF0F172A),

                        cursorColor = Color.White,

                        focusedLabelColor = Color(0xFF4F46E5),
                        unfocusedLabelColor = Color(0xFF94A3B8)
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = confirmPassword,

                    onValueChange = {
                        confirmPassword = it
                    },

                    label = {
                        Text(
                            "Confirm Password",
                            color = Color(0xFFCBD5E1)
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),

                    visualTransformation = PasswordVisualTransformation(),

                    singleLine = true,

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedBorderColor = Color(0xFF4F46E5),
                        unfocusedBorderColor = Color(0xFF475569),

                        focusedContainerColor = Color(0xFF0F172A),
                        unfocusedContainerColor = Color(0xFF0F172A),

                        cursorColor = Color.White,

                        focusedLabelColor = Color(0xFF4F46E5),
                        unfocusedLabelColor = Color(0xFF94A3B8)
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {

                        if (
                            email.isNotEmpty() &&
                            password.isNotEmpty() &&
                            confirmPassword.isNotEmpty()
                        ) {

                            if (password == confirmPassword) {

                                auth.createUserWithEmailAndPassword(
                                    email,
                                    password
                                ).addOnCompleteListener { task ->

                                    if (task.isSuccessful) {

                                        val uid =
                                            auth.currentUser?.uid

                                        if (uid != null) {

                                            database
                                                .reference
                                                .child("users")
                                                .child(uid)
                                                .child("role")
                                                .setValue("passenger")
                                        }

                                        Toast.makeText(
                                            context,
                                            "Passenger account created successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        onLoginClick()

                                    } else {

                                        Toast.makeText(
                                            context,
                                            "Error: ${task.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            } else {

                                Toast.makeText(
                                    context,
                                    "Passwords do not match",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {

                            Toast.makeText(
                                context,
                                "Please fill all fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4F46E5)
                    )
                ) {

                    Text(
                        text = "Create Passenger Account",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Already have an account? Login",

                    color = Color(0xFFCBD5E1),

                    fontSize = 14.sp,

                    modifier = Modifier.clickable {
                        onLoginClick()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}