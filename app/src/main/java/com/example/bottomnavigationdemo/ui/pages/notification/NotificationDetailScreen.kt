package com.example.bottomnavigationdemo.ui.pages.notification

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Esta pantalla muestra el detalle de una notificación.
 * Recibe onNotificationRead para marcarla como leída.
 */
@Composable
fun NotificationDetailScreen(
    notificationId: String?,
    navController: NavController,
    onNotificationRead: (String) -> Unit // Recibe el evento desde MainScreen
) {
    // ✅ ARREGLO DEL BUG: Marcar como leído en cuanto se abre la pantalla
    LaunchedEffect(key1 = notificationId) {
        if (notificationId != null) {
            onNotificationRead(notificationId)
        }
    }

    // Busca la notificación en la lista (que ahora es 'internal')
    val notification = try {
        dummyNotificationList.find { it.id == notificationId }
    } catch (e: Exception) {
        null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (notification != null) {
            Text(
                text = notification.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = notification.time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = notification.message, // Mensaje completo
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        } else {
            Text(
                text = "Error: Notificación no encontrada.",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Red
            )
        }
    }
}