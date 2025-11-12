package com.example.bottomnavigationdemo.ui.pages.pagos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class PagoHistorial(
    val id: String,
    val concepto: String,
    val fecha: String,
    val monto: String,
    val estatus: String // "Pagado", "Rechazado"
)

val dummyPagosList = listOf(
    PagoHistorial("1", "Reinscripción 9no Cuatrimestre", "01 de Sept, 2025", "$1,900.00", "Pagado"),
    PagoHistorial("2", "Curso de Nivelación - Cálculo", "15 de May, 2025", "$700.00", "Pagado"),
    PagoHistorial("3", "Examen Extraordinario", "05 de Abr, 2025", "$351.00", "Rechazado"),
    PagoHistorial("4", "Reinscripción 8vo Cuatrimestre", "01 de Ago, 2025", "$1,900.00", "Pagado")
)

@Composable
fun PagosScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            Text(
                text = "Historial de Pagos",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(dummyPagosList) { pago ->
            PagoHistorialItem(pago = pago)
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagoHistorialItem(pago: PagoHistorial) {
    val isPagado = pago.estatus == "Pagado"
    val icon = if (isPagado) Icons.Default.CheckCircle else Icons.Default.Error
    val iconColor = if (isPagado) Color(0xFF388E3C) else Color(0xFFD32F2F)
    val montoColor = if (isPagado) MaterialTheme.colorScheme.onSurface else Color(0xFFD32F2F)

    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Podría navegar a un recibo/detalle */ },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = {
            Text(pago.concepto, fontWeight = FontWeight.Medium)
        },
        supportingContent = {
            Text(pago.fecha, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(iconColor.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = pago.estatus,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        trailingContent = {
            Text(
                text = "-${pago.monto}",
                color = montoColor,
                fontWeight = FontWeight.Bold
            )
        }
    )
}