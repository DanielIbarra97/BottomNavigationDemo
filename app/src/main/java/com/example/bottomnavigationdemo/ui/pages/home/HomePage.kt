package com.example.bottomnavigationdemo.ui.pages.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavigationdemo.Screen

data class ServiceItem(
    val title: String,
    val description: String,
    val color: Color,
    val icon: ImageVector,
    val route: String // ✅ Añadida la ruta de navegación
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val customPurple = Color(0xFF4B2E83)
    val context = LocalContext.current

    // ✅ CORRECCIÓN: La lista ahora incluye la ruta de navegación
    val services = listOf(
        ServiceItem("Adeudos", "Servicios", Color(0xFFFF9800), Icons.Default.AttachMoney, Screen.Adeudos.route),
        ServiceItem("Lista", "Materias", Color(0xFF2196F3), Icons.AutoMirrored.Filled.List, Screen.ListaMaterias.route),
        ServiceItem("Manuales", "Asignatura y laboratorio", Color(0xFFE63946), Icons.Default.Book, Screen.Manuales.route),
        ServiceItem("Pagos", "Pagos e historial", Color(0xFF06A77D), Icons.AutoMirrored.Filled.ReceiptLong, Screen.Pagos.route),
        ServiceItem("Correo", "Activar cuenta de google", Color(0xFF7B2D8C), Icons.Default.Email, Screen.Correo.route),
        ServiceItem("Estadías", "Servicios", Color(0xFFE63946), Icons.Default.Work, Screen.Estadias.route),
        ServiceItem("Servicio Social", "Servicios", Color(0xFF06A77D), Icons.Default.VolunteerActivism, Screen.ServicioSocial.route),
        ServiceItem("Asesor", "Catálogo", Color(0xFFFF9800), Icons.Default.PersonSearch, Screen.Asesor.route),
        ServiceItem("Repositorio", "De Tesis", Color(0xFF06A77D), Icons.Default.Folder, Screen.Repositorio.route),
        ServiceItem("Sistema", "Gestión de la Calidad", Color(0xFFFF9800), Icons.Default.Settings, Screen.SistemaGestion.route),
        ServiceItem("Test", "Psicométricos", Color(0xFF7B2D8C), Icons.Default.Psychology, Screen.TestPsicometricos.route)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(services) { service ->
            ServiceCard(
                service = service,
                onClick = {
                    // ✅ CORRECCIÓN: Navegar a la ruta del servicio
                    navController.navigate(service.route)
                }
            )
        }

        item(span = { GridItemSpan(2) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "UNIVERSIDAD POLITÉCNICA DE PACHUCA",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Carretera Pachuca-Cd. Sahagún km 20, Ex-Hacienda de Santa Bárbara, Municipio de Zempoala, Hidalgo.",
                    color = Color.Black,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Tel. 52+ (771) 547 7510",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ServiceCard(service: ServiceItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = service.color),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = service.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 20.dp, y = 20.dp),
                tint = Color.White.copy(alpha = 0.2f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = service.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Column {
                    Text(
                        text = service.description,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ir a la liga >",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}