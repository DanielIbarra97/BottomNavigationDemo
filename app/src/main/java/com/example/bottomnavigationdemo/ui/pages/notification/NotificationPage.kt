package com.example.bottomnavigationdemo.ui.pages.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavigationdemo.Screen

enum class NotificationType {
    ACADEMICO, TAREA, REUNION, PAGO, BECA, EVENTO
}

data class Notification(
    val id: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val time: String,
    val isRead: Boolean = false
)

// ✅ LISTA ACTUALIZADA CON MÁS NOTIFICACIONES
val dummyNotificationList = listOf(
    Notification(id = "1", type = NotificationType.ACADEMICO, title = "Clase Cancelada", message = "La clase de Programación para Móviles II ha sido cancelada...", time = "Hace 10 min", isRead = false),
    Notification(id = "2", type = NotificationType.TAREA, title = "Tareas Pendientes", message = "Tienes 3 tareas sin entregar en Arquitectura Orientada...", time = "Hace 1 hora", isRead = false),
    Notification(id = "3", type = NotificationType.REUNION, title = "Tutoría Programada", message = "Reunión con Miguel Ángel Montoya Cerro en 30 minutos...", time = "Hace 2 horas", isRead = true),
    Notification(id = "4", type = NotificationType.PAGO, title = "Recordatorio de Pago", message = "Tu pago de re-inscripción vence el 1 de Enero...", time = "Hoy 9:00 AM", isRead = false),
    Notification(id = "5", type = NotificationType.BECA, title = "Nueva Convocatoria de Becas", message = "Becas del Bienestar disponibles. Revisa los requisitos...", time = "Ayer", isRead = true),
    Notification(id = "6", type = NotificationType.EVENTO, title = "Conferencia de IA", message = "No te pierdas la conferencia de IA en el auditorio principal.", time = "Ayer", isRead = false),
    Notification(id = "7", type = NotificationType.ACADEMICO, title = "Calificaciones Publicadas", message = "Tus calificaciones del segundo parcial ya están en el sistema.", time = "Hace 2 días", isRead = true),
    Notification(id = "8", type = NotificationType.PAGO, title = "Pago Rechazado", message = "Tu pago de la biblioteca fue rechazado. Intenta de nuevo.", time = "Hace 2 días", isRead = false),
    Notification(id = "9", type = NotificationType.TAREA, title = "Nueva Tarea: Redes", message = "Se ha asignado una nueva práctica de Minería de Datos en SISGAF.", time = "Hace 3 días", isRead = true),
    Notification(id = "10", type = NotificationType.REUNION, title = "Junta de Estadías", message = "Junta informativa sobre el proceso de estadías. Jueves 10:00 AM.", time = "Hace 3 días", isRead = true),
    Notification(id = "11", type = NotificationType.ACADEMICO, title = "Aviso: Mantenimiento", message = "La plataforma SIIUPP estará en mantenimiento el viernes.", time = "Hace 4 días", isRead = false)
)

@Composable
fun NotificationPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    notifications: List<Notification>,
    onNotificationRead: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(
            items = notifications,
            key = { it.id }
        ) { notification ->
            NotificationItemRow(
                notification = notification,
                onClick = {
                    navController.navigate(
                        Screen.NotificationDetail.route.replace(
                            "{notificationId}",
                            notification.id
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun NotificationItemRow(notification: Notification, onClick: () -> Unit) {

    val (icon, iconColor) = when (notification.type) {
        NotificationType.ACADEMICO -> Pair(Icons.Default.MenuBook, Color(0xFF1976D2))
        NotificationType.TAREA -> Pair(Icons.Default.Assignment, Color(0xFFF57C00))
        NotificationType.REUNION -> Pair(Icons.Default.Group, Color(0xFF388E3C))
        NotificationType.PAGO -> Pair(Icons.Default.AccountBalance, Color(0xFFD32F2F))
        NotificationType.BECA -> Pair(Icons.Default.School, Color(0xFF7B1FA2))
        NotificationType.EVENTO -> Pair(Icons.Default.Event, Color(0xFFF9A825))
    }

    val backgroundColor = if (notification.isRead) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    }

    val titleWeight = if (notification.isRead) FontWeight.Normal else FontWeight.Bold
    val messageColor = if (notification.isRead) Color.Gray else MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(iconColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = notification.type.name,
                tint = iconColor,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = notification.title,
                fontWeight = titleWeight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.message,
                fontWeight = FontWeight.Normal,
                color = messageColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = notification.time,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}