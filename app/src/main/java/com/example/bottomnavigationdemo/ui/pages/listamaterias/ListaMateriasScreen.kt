package com.example.bottomnavigationdemo.ui.pages.listamaterias

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottomnavigationdemo.Screen

// --- ESTRUCTURA DE DATOS ---
data class HorarioDetalle(
    val dia: String,
    val hora: String,
    val aula: String
)

data class Materia(
    val id: String,
    val nombre: String,
    val profesor: String,
    val horarios: List<HorarioDetalle>,
    val color: Color
)

// --- BASE DE DATOS SIMULADA COMPLETA (DE TUS PDFS) ---
internal val materiasSem9 = listOf(
    Materia("143105", "Programación para Móviles II", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN", "09:00-10:30", "L-SISGAF"), HorarioDetalle("MAR", "08:00-09:00", "A36"), HorarioDetalle("VIE", "08:00-10:00", "A36")), Color(0xFF1976D2)),
    Materia("143109", "Arquitectura Orientada a Servicios", "Rodríguez Flores Jazmín", listOf(HorarioDetalle("LUN", "13:00-15:00", "L-SISGAF"), HorarioDetalle("MAR", "13:00-15:00", "L-SISGAF"), HorarioDetalle("JUE", "14:00-15:00", "A38")), Color(0xFF388E3C)),
    Materia("143108", "Administración de Proyectos de Software", "Gomez Ramos Marcos Yamir", listOf(HorarioDetalle("MIÉ", "09:00-12:00", "A41"), HorarioDetalle("JUE", "10:00-12:00", "L-SISGAF"), HorarioDetalle("VIE", "10:00-12:00", "L-SISGAF")), Color(0xFF7B1FA2)),
    Materia("143106", "Seguridad de la Información", "Hernández Rodríguez Abigail", listOf(HorarioDetalle("MAR", "10:00-11:30", "L-SISGAF"), HorarioDetalle("MIÉ", "11:30-13:00", "A38"), HorarioDetalle("JUE", "12:00-14:00", "A35")), Color(0xFFD32F2F)),
    Materia("143107", "Minería de Datos", "Cosío León María de los Ángeles", listOf(HorarioDetalle("LUN", "11:00-13:00", "L-SISGAF"), HorarioDetalle("MIÉ", "13:00-16:00", "A37/A39"), HorarioDetalle("MAR", "14:00-15:00", "A39")), Color(0xFFF57C00)),
    Materia("143104", "Inglés IX", "Guzmán García Mary Carmen", listOf(HorarioDetalle("MIÉ", "15:00-16:30", "A39/A37"), HorarioDetalle("JUE", "15:00-17:00", "A37/A39")), Color(0xFF0097A7)),
    Materia("143110", "Expresión Oral y Escrita II", "Naranjo García Edilberta", listOf(HorarioDetalle("MAR", "11:00-12:00", "A37"), HorarioDetalle("JUE", "15:00-17:00", "A35")), Color(0xFF512DA8)),
    Materia("143111", "Tutoría IX", "Domínguez Mayorga Carlos Roberto", listOf(HorarioDetalle("JUE", "13:00-14:00", "A36")), Color(0xFF689F38))
)
internal val materiasSem8 = listOf(
    Materia("139675", "Programación para Móviles I", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN/MIE", "09:00-10:00", "L-INGSOF"), HorarioDetalle("JUE", "12:00-13:00", "A35")), Color(0xFF1976D2)),
    Materia("139679", "Mantenimiento de Software", "Gómez Ramos Marcos Yamir", listOf(HorarioDetalle("LUN/MIE", "09:00-10:00", "L-COMPU3"), HorarioDetalle("MAR/JUE", "14:00-15:00", "L-INGSOF/A35")), Color(0xFF388E3C)),
    Materia("139678", "Análisis Financiero de Software", "Coiffier Loaiza Erick Daniel", listOf(HorarioDetalle("LUN/MIE", "10:00-12:00", "L-REDTEL/A39"), HorarioDetalle("JUE", "11:00-12:00", "A38")), Color(0xFF7B1FA2)),
    Materia("139680", "Multimedia y Diseño Digital", "Rodríguez Flores Jazmín", listOf(HorarioDetalle("MAR/MIE", "10:00-11:00", "A41/A42"), HorarioDetalle("JUE", "15:00-17:00", "L-INGSOF"), HorarioDetalle("VIE", "14:00-17:00", "L-SISGAF")), Color(0xFFD32F2F)),
    Materia("140366", "Producción Audiovisual", "N/A (Síncrona)", listOf(HorarioDetalle("MAR/MIE/JUE", "10:00-13:00", "Sincrona")), Color(0xFFF57C00)),
    Materia("139676", "Compiladores e Intérpretes", "Flores Rodríguez Enrique", listOf(HorarioDetalle("JUE/VIE", "11:00-12:00", "A38/L-INGSOF"), HorarioDetalle("MAR/MIE", "12:00-13:00", "A38/L-INGSOF"), HorarioDetalle("VIE", "14:00-16:00", "L-INGSOF")), Color(0xFF0097A7)),
    Materia("139677", "Inteligencia Artificial", "Cosío León María de los Ángeles", listOf(HorarioDetalle("LUN", "12:00-14:00", "L-INGSOF"), HorarioDetalle("MAR/MIE", "13:00-15:00", "A36/L-INGSOF"), HorarioDetalle("VIE", "14:00-16:00", "L-INGSOF")), Color(0xFF512DA8)),
    Materia("139674", "Inglés VIII", "Vázquez Espinosa Erika", listOf(HorarioDetalle("JUE/VIE", "15:00-17:00", "A40/A21"), HorarioDetalle("MAR/MIE", "16:00-18:00", "A40/A21")), Color(0xFF689F38))
)
internal val materiasSem7 = listOf(
    Materia("136761", "Estancia II", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN/JUE", "08:00-10:00", "A42")), Color(0xFF1976D2)),
    Materia("136760", "Pruebas del Software", "Gómez Ramos Marcos Yamir", listOf(HorarioDetalle("MAR", "08:00-10:00", "L-INGSOF"), HorarioDetalle("LUN/MIE", "13:00-15:00", "L-SISGAF/L-INGSOF"), HorarioDetalle("VIE", "14:00-16:00", "L-INGSOF")), Color(0xFF388E3C)),
    Materia("136759", "Programación Concurrente", "Chávez Hernández Jesús Giovanni", listOf(HorarioDetalle("MIE", "08:00-10:00", "L-INGSOF"), HorarioDetalle("MAR/JUE", "13:00-15:00", "A42")), Color(0xFF7B1FA2)),
    Materia("136758", "Sistemas Operativos", "Flores Rodríguez Enrique", listOf(HorarioDetalle("JUE", "09:00-11:00", "L-SISGAF"), HorarioDetalle("VIE", "10:00-12:00", "A42"), HorarioDetalle("MIE", "13:00-14:00", "A42")), Color(0xFFD32F2F)),
    Materia("136755", "Inglés VII", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN", "10:00-11:00", "A42"), HorarioDetalle("MAR/MIE", "10:00-13:00", "A42"), HorarioDetalle("JUE", "11:00-13:00", "A42")), Color(0xFFF57C00)),
    Materia("136757", "Lenguajes y Autómatas", "Flores Rodríguez Enrique", listOf(HorarioDetalle("LUN/MAR", "11:00-13:00", "A42"), HorarioDetalle("MIE", "11:00-13:00", "L-SISGAF")), Color(0xFF0097A7)),
    Materia("136756", "Liderazgo de Equipos de Alto Desempeño", "Jiménez García José Guadalupe", listOf(HorarioDetalle("VIE", "12:00-14:00", "A42"), HorarioDetalle("LUN", "15:00-16:00", "A42")), Color(0xFF512DA8))
)
internal val materiasSem6 = listOf(
    Materia("132957", "Calidad del Software", "Gómez Ramos Marcos Yamir", listOf(HorarioDetalle("LUN/MAR", "07:00-09:00", "A21/A42"), HorarioDetalle("MIE/JUE", "07:00-09:00", "A42")), Color(0xFF1976D2)),
    Materia("132954", "Probabilidad y Estadística", "Serrano Guerrero Gabriel", listOf(HorarioDetalle("LUN/MAR", "07:00-09:00", "A42"), HorarioDetalle("JUE", "10:00-12:00", "A42")), Color(0xFF388E3C)),
    Materia("132958", "Estancia I", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN/MAR", "08:00-10:00", "A42"), HorarioDetalle("JUE", "11:00-12:00", "A42")), Color(0xFF7B1FA2)),
    Materia("132956", "Redes", "Ramos Ramírez Juan Manuel", listOf(HorarioDetalle("LUN/MAR", "09:00-11:00", "A42/L-REDTEL"), HorarioDetalle("MIE/JUE", "09:00-11:00", "L-REDTEL/A42")), Color(0xFFD32F2F)),
    Materia("132952", "Inglés VI", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN/MIE", "10:00-15:00", "A42")), Color(0xFFF57C00)),
    Materia("132955", "Arquitectura de Computadoras", "Barrera Zúñiga Samuel", listOf(HorarioDetalle("LUN/MAR", "12:00-14:00", "A42/A39"), HorarioDetalle("JUE/VIE", "15:00-17:00", "L-ELEANA")), Color(0xFF0097A7)),
    Materia("132953", "Habilidades Gerenciales", "Jiménez García José Guadalupe", listOf(HorarioDetalle("MAR/MIE", "12:00-15:00", "A21")), Color(0xFF512DA8))
)
internal val materiasSem5 = listOf(
    Materia("129963", "Fundamentos de Redes", "Ramos Ramírez Juan Manuel", listOf(HorarioDetalle("LUN/MAR", "08:00-10:00", "L-REDTEL/A37"), HorarioDetalle("MIE/JUE", "08:00-10:00", "A37")), Color(0xFF1976D2)),
    Materia("129962", "Programación Cliente/Servidor", "Cervantes Rangel Rocío", listOf(HorarioDetalle("LUN/MAR", "10:00-12:00", "A37"), HorarioDetalle("MIE/JUE", "08:00-10:00", "L-SISGAF"), HorarioDetalle("MIE", "13:00-15:00", "L-SISGAF")), Color(0xFF388E3C)),
    Materia("129964", "Arquitectura de Software", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN/MAR", "09:00-12:00", "L-SISGAF/A37"), HorarioDetalle("JUE", "10:00-12:00", "L-SISGAF")), Color(0xFF7B1FA2)),
    Materia("129960", "Ética Profesional", "Jiménez García José Guadalupe", listOf(HorarioDetalle("LUN/MAR", "10:00-12:00", "A37")), Color(0xFFD32F2F)),
    Materia("129966", "Tutoría V", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN/MAR", "11:00-12:00", "A37")), Color(0xFFF57C00)),
    Materia("129959", "Inglés V", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN/MAR", "11:00-13:00", "A37"), HorarioDetalle("MIE/JUE", "12:00-13:00", "A37"), HorarioDetalle("VIE", "14:00-16:00", "A37")), Color(0xFF0097A7)),
    Materia("129965", "Sistemas Digitales", "Barrera Zúñiga Samuel", listOf(HorarioDetalle("LUN/MAR", "12:00-14:00", "L-ELEANA"), HorarioDetalle("MIE", "12:00-15:00", "L-ELEANA/A37"), HorarioDetalle("VIE", "12:00-14:00", "L-ELEANA")), Color(0xFF512DA8)),
    Materia("129961", "Matemáticas para Ingeniería", "Serrano Guerrero Gabriel", listOf(HorarioDetalle("MIE/JUE", "14:00-17:00", "A37/L-SISGAF"), HorarioDetalle("VIE", "14:00-16:00", "L-SISGAF")), Color(0xFF689F38))
)
internal val materiasSem4 = listOf(
    Materia("127078", "Diseño de Interfaces", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("LUN", "07:00-08:00", "A38"), HorarioDetalle("MAR/MIE", "08:00-10:00", "L-SISGAF")), Color(0xFF1976D2)),
    Materia("127080", "Electricidad y Magnetismo", "Barrera Zúñiga Samuel", listOf(HorarioDetalle("LUN/MAR", "08:00-09:00", "A38"), HorarioDetalle("JUE/VIE", "10:00-12:00", "L-ELEANA")), Color(0xFF388E3C)),
    Materia("127075", "Habilidades Cognitivas y Creatividad", "Jiménez García José Guadalupe", listOf(HorarioDetalle("LUN/MAR", "08:00-10:00", "A36"), HorarioDetalle("VIE", "14:00-15:00", "A37")), Color(0xFF7B1FA2)),
    Materia("127076", "Matemáticas para Ingeniería", "Serrano Guerrero Gabriel", listOf(HorarioDetalle("LUN/MAR", "08:00-10:00", "A39/A38"), HorarioDetalle("JUE", "10:00-12:00", "A38")), Color(0xFFD32F2F)),
    Materia("127074", "Inglés IV", "Vázquez Espinosa Erika", listOf(HorarioDetalle("JUE/VIE", "10:00-12:00", "A35/A40"), HorarioDetalle("MIE", "12:00-14:00", "A40"), HorarioDetalle("MAR", "14:00-15:00", "A37/A41")), Color(0xFFF57C00)),
    Materia("127079", "Bases de Datos", "Chávez Hernández Jesús Giovanni", listOf(HorarioDetalle("JUE/VIE", "10:00-13:00", "A40/L-COMPU5"), HorarioDetalle("MAR", "12:00-13:00", "A40"), HorarioDetalle("MIE", "14:00-15:00", "A37")), Color(0xFF0097A7)),
    Materia("127077", "Programación Web", "Cervantes Rangel Rocío", listOf(HorarioDetalle("MIE", "12:00-13:00", "A20"), HorarioDetalle("MAR/JUE", "14:00-16:00", "L-SISGAF/A35"), HorarioDetalle("VIE", "12:00-15:00", "A20/A35")), Color(0xFF512DA8)),
    Materia("127081", "Tutoría IV", "Cervantes Rangel Rocío", listOf(HorarioDetalle("VIE", "15:00-16:00", "A35")), Color(0xFF689F38))
)
internal val materiasSem3 = listOf(
    Materia("123994", "Tutoría III", "Domínguez Mayorga Carlos Roberto", listOf(HorarioDetalle("VIE", "10:00-11:00", "A37")), Color(0xFF1976D2)),
    Materia("123990", "Programación Visual", "Cervantes Rangel Rocío", listOf(HorarioDetalle("LUN/MIE", "08:00-10:00", "L-SISGAF"), HorarioDetalle("JUE/VIE", "12:00-14:00", "A38/A37")), Color(0xFF388E3C)),
    Materia("123991", "Estructuras de Datos Avanzadas", "Flores Rodríguez Enrique", listOf(HorarioDetalle("LUN/MIE", "09:00-11:00", "L-SISGAF/A37"), HorarioDetalle("JUE", "12:00-13:00", "A38")), Color(0xFF7B1FA2)),
    Materia("123987", "Inglés III", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN/MIE", "09:00-11:00", "A21/A41"), HorarioDetalle("JUE", "10:00-11:00", "A41")), Color(0xFFD32F2F)),
    Materia("123989", "Cálculo Integral", "Cárdenas Flores Arturo", listOf(HorarioDetalle("LUN/MIE", "10:00-12:00", "A37/A20"), HorarioDetalle("VIE", "14:00-16:00", "A39")), Color(0xFF0097A7)),
    Materia("123988", "Inteligencia Emocional y Manejo de Conflictos", "Jiménez García José Guadalupe", listOf(HorarioDetalle("LUN/MIE", "11:00-13:00", "A35/A20"), HorarioDetalle("JUE", "11:00-12:00", "A35")), Color(0xFF512DA8)),
    Materia("123993", "Ingeniería de Requerimientos de Software", "Montoya Cerro Miguel Ángel", listOf(HorarioDetalle("JUE/VIE", "13:00-15:00", "A21/L-SISGAF")), Color(0xFF689F38)),
    Materia("123992", "Fundamentos de Bases de Datos", "Chávez Hernández Jesús Giovanni", listOf(HorarioDetalle("VIE", "14:00-17:00", "L-COMPU3/4"), HorarioDetalle("MAR", "15:00-17:00", "A35")), Color(0xFFE64A19))
)
internal val materiasSem2 = listOf(
    Materia("121498", "Programación Orientada a Objetos", "Chávez Hernández Jesús Giovanni", listOf(HorarioDetalle("LUN/MAR", "07:00-09:00", "L-COMPU3"), HorarioDetalle("MIE/JUE", "10:00-11:00", "A20/L-SISGAF")), Color(0xFF1976D2)),
    Materia("121495", "Inglés II", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN/MAR", "08:00-10:00", "A20/A41"), HorarioDetalle("MIE/JUE", "09:00-10:00", "A40")), Color(0xFF388E3C)),
    Materia("121502", "Tutoría II", "Chávez Hernández Jesús Giovanni", listOf(HorarioDetalle("LUN/MAR", "09:00-10:00", "A20")), Color(0xFF7B1FA2)),
    Materia("122610", "Atletismo", "Valera Soto David", listOf(HorarioDetalle("SAB", "08:00-11:00", "Síncrona")), Color(0xFFD32F2F)),
    Materia("121501", "Procesos de Desarrollo de Software", "Flores Rodríguez Enrique", listOf(HorarioDetalle("MIE/JUE", "10:00-12:00", "A37"), HorarioDetalle("MAR/VIE", "14:00-16:00", "L-SISGAF/A20")), Color(0xFFF57C00)),
    Materia("121496", "Desarrollo Humano y Valores", "Jiménez García José Guadalupe", listOf(HorarioDetalle("MIE/JUE", "11:00-13:00", "A37/A40")), Color(0xFF0097A7)),
    Materia("121500", "Ingeniería de Software Asistida por Computadora", "Hernández Ángeles Verónica", listOf(HorarioDetalle("MIE/JUE", "11:00-12:00", "A35"), HorarioDetalle("LUN/VIE", "14:00-17:00", "A38/L-COMPU5")), Color(0xFF512DA8)),
    Materia("121497", "Cálculo Diferencial", "Cárdenas Flores Arturo", listOf(HorarioDetalle("LUN/MAR", "12:00-14:00", "A36/A40"), HorarioDetalle("MIE", "12:00-14:00", "A37")), Color(0xFF689F38)),
    Materia("121499", "Estructuras de Datos", "Flores Rodríguez Enrique", listOf(HorarioDetalle("LUN/MAR", "12:00-14:00", "A20/L-SISGAF"), HorarioDetalle("VIE", "14:00-17:00", "L-SISGAF/A20")), Color(0xFFE64A19))
)
internal val materiasSem1 = listOf(
    Materia("118879", "Inglés I", "Vázquez Espinosa Erika", listOf(HorarioDetalle("LUN", "07:00-09:00", "A21"), HorarioDetalle("MIE", "08:00-10:00", "A21")), Color(0xFF1976D2)),
    Materia("118880", "Fundamentos de Computación", "Hernández Ángeles Verónica", listOf(HorarioDetalle("LUN/MIE", "07:00-09:00", "L-COMPU2"), HorarioDetalle("MAR/JUE", "12:00-14:00", "A21")), Color(0xFF388E3C)),
    Materia("118886", "Tutoría I", "Hernández Ángeles Verónica", listOf(HorarioDetalle("LUN/MAR", "08:00-09:00", "A41")), Color(0xFF7B1FA2)),
    Materia("118883", "Algoritmos", "Flores Rodríguez Enrique", listOf(HorarioDetalle("LUN/MAR", "09:00-11:00", "A37/A36"), HorarioDetalle("MIE/JUE", "13:00-15:00", "L-SISGAF")), Color(0xFFD32F2F)),
    Materia("118881", "Algebra Lineal", "Lira Hernández Luz María", listOf(HorarioDetalle("LUN/MAR", "11:00-13:00", "A35/A38"), HorarioDetalle("JUE", "10:00-12:00", "A38")), Color(0xFFF57C00)),
    Materia("118885", "Expresión Oral y Escrita I", "Naranjo García Edilberta", listOf(HorarioDetalle("LUN/MAR", "10:00-12:00", "A20/A36"), HorarioDetalle("MIE/JUE", "12:00-14:00", "A36")), Color(0xFF0097A7)),
    Materia("118884", "Matemáticas Discretas", "Pérez López Verónica", listOf(HorarioDetalle("MAR/MIE", "10:00-12:00", "A41/A37"), HorarioDetalle("JUE/VIE", "12:00-14:00", "A35/A37")), Color(0xFF512DA8)),
    Materia("118882", "Química Básica", "Pérez Azpeitia Elvia", listOf(HorarioDetalle("JUE/VIE", "14:00-16:00", "L-QUIBA1")), Color(0xFF689F38)),
    Materia("120165", "Beisbol", "Valera Soto David", listOf(HorarioDetalle("LUN/MIE", "16:30-18:00", "Síncrona")), Color(0xFFE64A19))
)

// ✅ CAMBIO 1: Mapa principal actualizado con todos los periodos de tus imágenes.
// Los periodos sin datos se enlazan a 'emptyList()'
internal val periodosMap = mapOf(
    "SEPTIEMBRE - DICIEMBRE 2025" to materiasSem9,
    "MAYO - AGOSTO 2025" to materiasSem8,
    "ENERO - ABRIL 2025" to materiasSem7,
    "SEPTIEMBRE - DICIEMBRE 2024" to materiasSem6,
    "MAYO - AGOSTO 2024" to materiasSem5,
    "ENERO - ABRIL 2024" to materiasSem4,
    "SEPTIEMBRE - DICIEMBRE 2023" to materiasSem3,
    "MAYO - AGOSTO 2023" to materiasSem2,
    "ENERO - ABRIL 2023" to materiasSem1,

)

// Mapa combinado para que la pantalla de detalle encuentre cualquier materia por ID
internal val todasLasMaterias = periodosMap.values.flatten().associateBy { it.id }


// --- PANTALLA LISTA DE MATERIAS ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaMateriasScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // El 'periodosMap' actualizado se usará aquí
    val periodosKeys = periodosMap.keys.toList()

    var expanded by remember { mutableStateOf(false) }
    var selectedPeriodoKey by remember { mutableStateOf<String?>(null) }

    val materiasMostradas = periodosMap[selectedPeriodoKey] ?: emptyList()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // --- Selector de Periodo (Sin cambios, ya era correcto) ---
        item {
            Text(
                text = "Seleccione el periodo a consultar:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedPeriodoKey ?: "Seleccione un periodo",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    periodosKeys.forEach { periodoKey ->
                        DropdownMenuItem(
                            text = { Text(periodoKey) },
                            onClick = {
                                selectedPeriodoKey = periodoKey
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
        }

        // --- Lista de Materias (Sin cambios, ya era correcto) ---
        if (materiasMostradas.isEmpty()) {
            item {
                Text(
                    text = if (selectedPeriodoKey == null) "Por favor, seleccione un periodo para ver sus materias." else "No hay materias registradas para este periodo.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                )
            }
        } else {
            items(materiasMostradas, key = { it.id }) { materia ->
                MateriaItemCard(
                    materia = materia,
                    onClick = {
                        navController.navigate(
                            Screen.MateriaDetail.route.replace("{materiaId}", materia.id)
                        )
                    }
                )
            }
        }
    }
}

// --- TARJETA DE MATERIA (CON HORARIO DETALLADO) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriaItemCard(materia: Materia, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(materia.color)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = materia.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- Profesor ---
                InfoRow(icon = Icons.Default.Person, text = materia.profesor)
                Spacer(modifier = Modifier.height(12.dp))

                // Itera sobre la lista de horarios
                materia.horarios.forEach { horario ->
                    // ✅ CAMBIO 2: Corrección de layout del salón
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp), // Espacio entre horarios
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // (Icono de Horario)
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Horario",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        // (Texto de Horario)
                        Text(
                            text = "${horario.dia}: ${horario.hora}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            // ✅ CAMBIO 2: Se eliminó el modifier = Modifier.weight(1f)
                        )

                        // ✅ CAMBIO 2: Spacer añadido para separación
                        Spacer(modifier = Modifier.width(16.dp))

                        // (Icono de Aula)
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Aula",
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Menos espacio
                        // (Texto de Aula)
                        Text(
                            text = horario.aula,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}