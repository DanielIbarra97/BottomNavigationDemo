package com.example.bottomnavigationdemo.ui.pages.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.bottomnavigationdemo.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onLogout: () -> Unit
) {
    var showAboutDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item { SettingHeader(title = "Preferencias de la App") }

        item {
            var notificationsEnabled by rememberSaveable { mutableStateOf(true) }
            ToggleSettingItem(
                icon = Icons.Default.Notifications,
                title = "Recibir Notificaciones",
                description = "Activa o desactiva las alertas de avisos.",
                checkedState = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }

        item {
            ToggleSettingItem(
                icon = Icons.Default.Settings,
                title = "Modo Oscuro",
                description = "Cambia el tema de la aplicación a colores oscuros.",
                checkedState = isDarkMode,
                onCheckedChange = onToggleTheme
            )
        }

        item { SettingHeader(title = "Cuenta y Privacidad") }

        item {
            ClickableSettingItem(
                icon = Icons.Default.Info,
                title = "Acerca de la App",
                onClick = { showAboutDialog = true }
            )
        }

        item { Spacer(modifier = Modifier.height(30.dp)) }
    }

    if (showAboutDialog) {
        AboutAppDialog(
            onDismiss = { showAboutDialog = false }
        )
    }
}

@Composable
fun AboutAppDialog(onDismiss: () -> Unit) {
    val customPurple = Color(0xFF4B2E83)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_upp),
                    contentDescription = "Logo UPP",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "SIIUPP Móvil",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = customPurple
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Versión 1.0.0",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.LightGray)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sistema Integral de Información",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Universidad Politécnica de Pachuca",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Aplicación móvil para gestionar servicios estudiantiles, notificaciones, adeudos, pagos y más.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = Color.LightGray)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "© 2025 Universidad Politécnica de Pachuca",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Todos los derechos reservados",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = customPurple),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SettingHeader(title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 8.dp)
        )
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    }
}

// ✅ --- SECCIÓN CORREGIDA ---
@Composable
fun ToggleSettingItem(
    icon: ImageVector,
    title: String,
    description: String,
    checkedState: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checkedState) }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ✅ CORRECCIÓN: Colores explícitos para el Switch
        Switch(
            checked = checkedState,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                // --- Estado ENCENDIDO (Checked) ---
                // Pulgar (bolita): Blanco
                checkedThumbColor = Color.White,
                // Pista (fondo): Morado (Color primario)
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                checkedBorderColor = MaterialTheme.colorScheme.primary,

                // --- Estado APAGADO (Unchecked) ---
                // Pulgar (bolita): Gris claro
                uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                // Pista (fondo): Gris más oscuro
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                uncheckedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}
// ✅ --- FIN DE LA SECCIÓN CORREGIDA ---

@Composable
fun ClickableSettingItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = { Text(title) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingContent = {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
}