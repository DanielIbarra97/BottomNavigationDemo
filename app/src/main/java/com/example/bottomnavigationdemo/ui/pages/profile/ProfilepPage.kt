package com.example.bottomnavigationdemo.ui.pages.profile

import androidx.compose.foundation.Image // ✅ Importar Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale // ✅ Importar ContentScale
import androidx.compose.ui.res.painterResource // ✅ Importar painterResource
import com.example.bottomnavigationdemo.R // ✅ Importar R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    onEditProfile: () -> Unit = {},
    onChangePassword: () -> Unit = {}
) {
    val customPurple = Color(0xFF4B2E83)

    // 🛑 ELIMINADO: Scaffold y TopAppBar (ahora están en MainScreen.kt)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 80.dp) // Espacio para el BottomNav
    ) {
        // Header con foto de perfil
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(customPurple)
                    .padding(vertical = 32.dp), // Padding vertical
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // 🛑 ELIMINADO: Text("Mi Perfil") redundante

                    // ✅ CAMBIO: Reemplazado Box con "DP" por una Image
                    Image(
                        painter = painterResource(id = R.drawable.placeholder_profile),
                        contentDescription = "Foto de Perfil",
                        contentScale = ContentScale.Crop, // Rellena el círculo
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "PEREZ IBARRA DANIEL",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Ingeniería en Software",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }

        // Información personal
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Información Personal",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    ProfileInfoRow(
                        icon = Icons.Default.Person,
                        label = "Matrícula",
                        value = "2311122571"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(
                        icon = Icons.Default.Email,
                        label = "Correo Institucional",
                        value = "dpi97@micorreo.upp.edu.mx"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(
                        icon = Icons.Default.School,
                        label = "Cuatrimestre",
                        value = "9no Cuatrimestre"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(
                        icon = Icons.Default.DateRange,
                        label = "Fecha de Ingreso",
                        value = "Septiembre 2022"
                    )
                }
            }
        }

        // Estadísticas académicas
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Estadísticas Académicas",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Promedio",
                    value = "9.2",
                    icon = Icons.Default.Star,
                    color = Color(0xFFF9A825),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Créditos",
                    value = "280",
                    icon = Icons.Default.CheckCircle,
                    color = Color(0xFF388E3C),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Materias",
                    value = "45",
                    icon = Icons.AutoMirrored.Filled.MenuBook,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Avance",
                    value = "90%",
                    icon = Icons.AutoMirrored.Filled.TrendingUp,
                    color = Color(0xFF7B1FA2),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Acciones
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Acciones",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    ProfileActionItem(
                        icon = Icons.Default.Edit,
                        title = "Datos Personales",
                        onClick = onEditProfile
                    )
                    HorizontalDivider()
                    ProfileActionItem(
                        icon = Icons.Default.Lock,
                        title = "Cambiar NIP",
                        onClick = onChangePassword
                    )
                    HorizontalDivider()
                    ProfileActionItem(
                        icon = Icons.Default.Download,
                        title = "Descargar Historial Académico",
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp) // Añadido padding
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4B2E83),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(120.dp), // Ligeramente más alto
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Usar fillMaxSize
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centrar verticalmente
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp) // Ligeramente más pequeño
                    .background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp) // Ligeramente más pequeño
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 22.sp, // Ligeramente más pequeño
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ProfileActionItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(title, fontWeight = FontWeight.Medium)
        },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF4B2E83)
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        modifier = Modifier.clickable { onClick() }
    )
}