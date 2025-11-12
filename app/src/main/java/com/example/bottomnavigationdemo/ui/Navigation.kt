package com.example.bottomnavigationdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    // --- Rutas para el BottomBar ---
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Notification : Screen("notification", "Notificationes", Icons.Default.Notifications)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
    object Settings : Screen("settings", "Ajustes", Icons.Default.Settings)
    object NotificationDetail : Screen("notification_detail/{notificationId}", "Detalle", Icons.Default.Inbox)

    // --- Rutas del Menú Lateral y Grid ---
    object Pagos : Screen("pagos", "Pagos", Icons.Default.AccountBalanceWallet)
    object Correo : Screen("correo", "Correo", Icons.Default.Email)
    object Estadias : Screen("estadias", "Estadías", Icons.Default.Work)
    object Adeudos : Screen("adeudos", "Adeudos", Icons.Default.AttachMoney)
    object ServicioSocial : Screen("servicio_social", "Servicio Social", Icons.Default.People)
    object ListaMaterias : Screen("lista_materias", "Lista Materias", Icons.AutoMirrored.Filled.List)
    object Agenda : Screen("agenda", "Agenda", Icons.Default.CalendarMonth)
    object Asesorias : Screen("asesorias", "Asesorías", Icons.Default.Groups)
    object Complementarias : Screen("complementarias", "Complementarias", Icons.Default.VerifiedUser)
    object SistemaGestion : Screen("sistema_gestion", "Sistema de Gestión", Icons.Default.Settings)
    object Manuales : Screen("manuales", "Manuales", Icons.Default.Book)
    object Asesor : Screen("asesor", "Asesor", Icons.Default.PersonSearch)
    object Repositorio : Screen("repositorio", "Repositorio", Icons.Default.Folder)
    object TestPsicometricos : Screen("test_psicometricos", "Test Psicométricos", Icons.Default.Psychology)

    // --- ✅ NUEVA RUTA DE DETALLE ---
    object MateriaDetail : Screen("materia_detail/{materiaId}", "Detalle de Materia", Icons.Default.Book) // El icono es solo de referencia
}

val bottomNavigationItems = listOf(
    Screen.Home,
    Screen.Notification,
    Screen.Profile,
    Screen.Settings
)

val sideMenuItems = mapOf(
    "Alumnos -Inicio" to Screen.Home,
    "Pagos" to Screen.Pagos,
    "Correo" to Screen.Correo,
    "Estadías" to Screen.Estadias,
    "Servicio Social" to Screen.ServicioSocial,
    "Adeudos" to Screen.Adeudos,
    "Lista Materias" to Screen.ListaMaterias,
    "Agenda" to Screen.Agenda,
    "Asesorías" to Screen.Asesorias,
    "Complementarias" to Screen.Complementarias,
    "Perfil" to Screen.Profile,
    "Sistema de Gestión" to Screen.SistemaGestion
)