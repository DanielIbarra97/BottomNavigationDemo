package com.example.bottomnavigationdemo.ui.pages.adeudos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Adeudo(
    val id: String,
    val concepto: String,
    val vencimiento: String,
    val monto: Double,
    val estatus: String // "Vencido", "Pendiente"
)

val dummyAdeudosList = listOf(
    Adeudo("1", "Inscripción 10mo Cuatrimestre", "Vence el 01/DIC/2025", 1900.00, "Pendiente"),
    Adeudo("2", "Multa de Biblioteca", "Vence el 15/NOV/2025", 50.00, "Vencido"),
    Adeudo("3", "Material Laboratorio de Redes", "Vence el 20/NOV/2025", 250.00, "Pendiente")
)

@Composable
fun AdeudosScreen(modifier: Modifier = Modifier) {
    val totalAdeudado = dummyAdeudosList.sumOf { it.monto }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total Adeudado",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$ ${"%,.2f".format(totalAdeudado)} MXN",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Acción de Pagar Todo */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(Icons.Default.CreditCard, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Pagar Total")
                    }
                }
            }
        }

        item {
            Text(
                text = "Desglose de Adeudos",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        items(dummyAdeudosList) { adeudo ->
            AdeudoItem(adeudo = adeudo)
        }
    }
}

@Composable
fun AdeudoItem(adeudo: Adeudo) {
    val isVencido = adeudo.estatus == "Vencido"
    val cardColor = if (isVencido) Color(0xFFFFEBEE) else MaterialTheme.colorScheme.surface
    val icon = if (isVencido) Icons.Default.Warning else Icons.Default.Info
    val iconColor = if (isVencido) Color(0xFFD32F2F) else MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = adeudo.estatus,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = adeudo.concepto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = adeudo.vencimiento,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = "$ ${"%,.2f".format(adeudo.monto)} MXN",
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = iconColor
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Pagar adeudo individual */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = iconColor,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text("Pagar", fontSize = 12.sp)
            }
        }
    }
}