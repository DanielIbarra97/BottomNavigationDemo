package com.example.bottomnavigationdemo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomnavigationdemo.R
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.toArgb
import com.example.bottomnavigationdemo.ui.pages.models.LoginRequest
import com.example.bottomnavigationdemo.ui.pages.models.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun LoginPage(onLoginSuccess: () -> Unit) {
    val uppPurple = Color(0xFF4B2E83)

    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var matriculaError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    var apiError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val matriculaRegex = Regex("^\\d{10}$")
    val passwordRegex = Regex("^[a-zA-Z0-9]{10}$")

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    SideEffect {
        val window = (context as? Activity)?.window ?: return@SideEffect
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true

        window.statusBarColor = Color.White.toArgb()
        window.navigationBarColor = Color.White.toArgb()
    }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_upp),
                contentDescription = "Logo UPP",
                modifier = Modifier.size(140.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SIIUPP",
                style = MaterialTheme.typography.headlineMedium,
                color = uppPurple,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Text(
                text = "Versión Móvil",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(48.dp))

            // Campo de Matrícula con validación
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = matricula,
                    onValueChange = {
                        matricula = it
                        if (matriculaError.isNotEmpty()) matriculaError = ""
                        if (apiError.isNotEmpty()) apiError = ""
                    },
                    label = { Text("Matrícula", color = uppPurple.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (matriculaError.isNotEmpty()) Color.Red else uppPurple,
                        unfocusedBorderColor = if (matriculaError.isNotEmpty()) Color.Red else uppPurple.copy(alpha = 0.5f),
                        focusedLabelColor = uppPurple,
                        unfocusedLabelColor = uppPurple.copy(alpha = 0.75f),
                        cursorColor = uppPurple,
                        focusedTextColor = uppPurple,
                        unfocusedTextColor = Color.Black,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    isError = matriculaError.isNotEmpty(),
                    singleLine = true,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth()
                )

                if (matriculaError.isNotEmpty()) {
                    Text(
                        text = matriculaError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 4.dp)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Contraseña con validación
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (passwordError.isNotEmpty()) passwordError = ""
                        if (apiError.isNotEmpty()) apiError = ""
                    },
                    label = { Text("Contraseña", color = uppPurple.copy(alpha = 0.7f)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = uppPurple
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (passwordError.isNotEmpty()) Color.Red else uppPurple,
                        unfocusedBorderColor = if (passwordError.isNotEmpty()) Color.Red else uppPurple.copy(alpha = 0.5f),
                        focusedLabelColor = uppPurple,
                        unfocusedLabelColor = uppPurple.copy(alpha = 0.75f),
                        cursorColor = uppPurple,
                        focusedTextColor = uppPurple,
                        unfocusedTextColor = Color.Black,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    isError = passwordError.isNotEmpty(),
                    singleLine = true,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth()
                )

                if (passwordError.isNotEmpty()) {
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 4.dp)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Limpiar errores previos
                    matriculaError = ""
                    passwordError = ""
                    apiError = ""

                    // Validaciones antes de enviar
                    if (matricula.isEmpty()) {
                        matriculaError = "Este campo es obligatorio"
                    } else if (!matriculaRegex.matches(matricula)) {
                        matriculaError = "La matrícula debe ser de 10 dígitos numéricos."
                    }

                    if (password.isEmpty()) {
                        passwordError = "Este campo es obligatorio"
                    } else if (!passwordRegex.matches(password)) {
                        passwordError = "La contraseña debe ser de 10 caracteres (solo letras y números)."
                    }

                    // Si pasan las validaciones, llamar a la API
                    if (matriculaError.isEmpty() && passwordError.isEmpty()) {
                        isLoading = true

                        scope.launch {
                            try {
                                val response = RetrofitClient.apiService.login(
                                    LoginRequest(matricula, password)
                                )

                                isLoading = false

                                if (response.success) {
                                    onLoginSuccess()
                                } else {
                                    // Manejar errores específicos
                                    when {
                                        response.message.contains("matrícula", ignoreCase = true) ||
                                                response.message.contains("matricula", ignoreCase = true) ||
                                                response.message.contains("usuario no encontrado", ignoreCase = true) -> {
                                            matriculaError = "Matrícula incorrecta"
                                        }
                                        response.message.contains("contraseña", ignoreCase = true) ||
                                                response.message.contains("password", ignoreCase = true) -> {
                                            passwordError = "Contraseña incorrecta"
                                        }
                                        else -> {
                                            apiError = response.message
                                        }
                                    }
                                }
                            } catch (e: retrofit2.HttpException) {
                                isLoading = false
                                // Manejar error 401 específicamente
                                if (e.code() == 401) {
                                    val errorBody = e.response()?.errorBody()?.string()
                                    when {
                                        errorBody?.contains("matrícula", ignoreCase = true) == true ||
                                                errorBody?.contains("matricula", ignoreCase = true) == true ||
                                                errorBody?.contains("usuario", ignoreCase = true) == true -> {
                                            matriculaError = "Matrícula incorrecta"
                                        }
                                        errorBody?.contains("contraseña", ignoreCase = true) == true ||
                                                errorBody?.contains("password", ignoreCase = true) == true -> {
                                            passwordError = "Contraseña incorrecta"
                                        }
                                        else -> {
                                            // Por defecto, asumimos que es contraseña incorrecta en 401
                                            passwordError = "Contraseña incorrecta"
                                        }
                                    }
                                } else {
                                    apiError = "Error de conexión: ${e.message}"
                                }
                            } catch (e: Exception) {
                                isLoading = false
                                apiError = "Error de conexión: ${e.message}"
                            }
                        }
                    }
                },
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = uppPurple),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Iniciar Sesión",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            // Mostrar error de la API si existe
            if (apiError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = apiError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            }
    }
}