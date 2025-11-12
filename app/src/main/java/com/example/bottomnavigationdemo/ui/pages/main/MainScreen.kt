package com.example.bottomnavigationdemo.ui.pages.main

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnavigationdemo.Screen
import com.example.bottomnavigationdemo.bottomNavigationItems
import com.example.bottomnavigationdemo.sideMenuItems
// Imports de las nuevas pantallas
import com.example.bottomnavigationdemo.ui.pages.adeudos.AdeudosScreen
import com.example.bottomnavigationdemo.ui.pages.home.HomePage
import com.example.bottomnavigationdemo.ui.pages.listamaterias.ListaMateriasScreen
import com.example.bottomnavigationdemo.ui.pages.listamaterias.MateriaDetailScreen
import com.example.bottomnavigationdemo.ui.pages.notification.NotificationDetailScreen
import com.example.bottomnavigationdemo.ui.pages.notification.NotificationPage
import com.example.bottomnavigationdemo.ui.pages.notification.dummyNotificationList
import com.example.bottomnavigationdemo.ui.pages.pagos.PagosScreen
import com.example.bottomnavigationdemo.ui.pages.profile.ProfilePage
import com.example.bottomnavigationdemo.ui.pages.setting.SettingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("DEPRECATION")
@Composable
fun MainScreen(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onLogout: () -> Unit
) {
    val customPurple = Color(0xFF4B2E83)
    val context = LocalContext.current

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isHomeScreen = currentRoute == Screen.Home.route

    val allScreens = bottomNavigationItems + sideMenuItems.values.toList() +
            listOf(
                Screen.NotificationDetail, Screen.MateriaDetail, Screen.Manuales,
                Screen.Asesor, Screen.Repositorio, Screen.TestPsicometricos
            )
    val currentScreen = allScreens.find { it.route.startsWith(currentRoute?.split("/")?.first() ?: "") }


    val bottomBackground = if (isDarkTheme) Color(0xFF1C1C1E) else Color(0xFFF2F2F2)
    val dividerColor = if (isDarkTheme) Color(0xFF3A3A3C) else Color(0xFFCCCCCC)
    val iconColor = if (isDarkTheme) Color.Gray else Color.Black
    val iconSelectedColor = customPurple

    val drawerSurfaceColor = MaterialTheme.colorScheme.surface

    var notifications by remember { mutableStateOf(dummyNotificationList) }

    val onNotificationRead = { id: String ->
        notifications = notifications.map {
            if (it.id == id) it.copy(isRead = true) else it
        }
    }

    SideEffect {
        val window = (context as? Activity)?.window ?: return@SideEffect
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        window.navigationBarColor = bottomBackground.toArgb()
        insetsController.isAppearanceLightNavigationBars = !isDarkTheme

        if (drawerState.isOpen) {
            window.statusBarColor = drawerSurfaceColor.toArgb()
            insetsController.isAppearanceLightStatusBars = !isDarkTheme
        } else {
            window.statusBarColor = customPurple.toArgb()
            insetsController.isAppearanceLightStatusBars = false
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    item {
                        Text(
                            "Menú",
                            style = MaterialTheme.typography.headlineSmall,
                            color = customPurple,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        HorizontalDivider()
                    }

                    items(items = sideMenuItems.entries.toList()) { (title, screen) ->
                        NavigationDrawerItem(
                            label = { Text(title) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                scope.launch { drawerState.close() }
                                if (screen.route == Screen.Home.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                } else {
                                    navController.navigate(screen.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                    item {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        NavigationDrawerItem(
                            label = { Text("Cerrar sesión", color = Color.Red) },
                            selected = false,
                            onClick = {
                                scope.launch { drawerState.close() }
                                onLogout()
                            },
                            icon = { Icon(Icons.Default.Logout, "Cerrar sesión", tint = Color.Red) },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            currentScreen?.label ?: "Home",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        if (isHomeScreen) {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, "Menú", tint = Color.White)
                            }
                        } else {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = Color.White)
                            }
                        }
                    },
                    actions = {
                        Spacer(modifier = Modifier.width(48.dp))
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = customPurple
                    )
                )
            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bottomBackground)
                        .navigationBarsPadding()
                ) {
                    HorizontalDivider(color = dividerColor, thickness = 1.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        val currentDestination = navBackStackEntry?.destination
                        bottomNavigationItems.forEach { screen ->
                            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                            val color = if (isSelected) iconSelectedColor else iconColor

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clickable {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                    .background(
                                        color = if (isSelected) bottomBackground.copy(alpha = 0.3f) else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.label,
                                        tint = color
                                    )
                                    Text(
                                        text = screen.label,
                                        color = color,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            },
            containerColor = Color.Transparent,
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomePage(modifier = Modifier.fillMaxSize(), navController = navController)
                }
                composable(Screen.Notification.route) {
                    NotificationPage(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        notifications = notifications,
                        onNotificationRead = onNotificationRead
                    )
                }
                composable(Screen.Profile.route) {
                    ProfilePage(
                        modifier = Modifier.fillMaxSize(),
                        onEditProfile = {},
                        onChangePassword = {}
                    )
                }
                composable(Screen.Settings.route) {
                    SettingPage(
                        modifier = Modifier.fillMaxSize(),
                        isDarkMode = isDarkTheme,
                        onToggleTheme = onToggleTheme,
                        onLogout = onLogout
                    )
                }
                composable(Screen.NotificationDetail.route) { backStackEntry ->
                    val notificationId = backStackEntry.arguments?.getString("notificationId")
                    NotificationDetailScreen(
                        notificationId = notificationId,
                        navController = navController,
                        onNotificationRead = onNotificationRead
                    )
                }
                composable(Screen.MateriaDetail.route) { backStackEntry ->
                    val materiaId = backStackEntry.arguments?.getString("materiaId")
                    MateriaDetailScreen(
                        modifier = Modifier.fillMaxSize(),
                        materiaId = materiaId,
                        navController = navController
                    )
                }
                composable(Screen.Pagos.route) {
                    PagosScreen(modifier = Modifier.fillMaxSize())
                }
                composable(Screen.Adeudos.route) {
                    AdeudosScreen(modifier = Modifier.fillMaxSize())
                }
                composable(Screen.ListaMaterias.route) {
                    ListaMateriasScreen(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController
                    )
                }

                composable(Screen.Manuales.route) { PlaceholderScreenWithIcon(Screen.Manuales) }
                composable(Screen.Correo.route) { PlaceholderScreenWithIcon(Screen.Correo) }
                composable(Screen.Estadias.route) { PlaceholderScreenWithIcon(Screen.Estadias) }
                composable(Screen.ServicioSocial.route) { PlaceholderScreenWithIcon(Screen.ServicioSocial) }
                composable(Screen.Asesor.route) { PlaceholderScreenWithIcon(Screen.Asesor) }
                composable(Screen.Repositorio.route) { PlaceholderScreenWithIcon(Screen.Repositorio) }
                composable(Screen.SistemaGestion.route) { PlaceholderScreenWithIcon(Screen.SistemaGestion) }
                composable(Screen.TestPsicometricos.route) { PlaceholderScreenWithIcon(Screen.TestPsicometricos) }
                composable(Screen.Agenda.route) { PlaceholderScreenWithIcon(Screen.Agenda) }
                composable(Screen.Asesorias.route) { PlaceholderScreenWithIcon(Screen.Asesorias) }
                composable(Screen.Complementarias.route) { PlaceholderScreenWithIcon(Screen.Complementarias) }
            }
        }
    }
}

/**
 * Un Composable genérico para centrar texto y un icono en las pantallas de marcador de posición.
 */
@Composable
fun PlaceholderScreenWithIcon(screen: Screen) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = screen.label,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
        Text(
            text = "(Contenido próximamente)",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}