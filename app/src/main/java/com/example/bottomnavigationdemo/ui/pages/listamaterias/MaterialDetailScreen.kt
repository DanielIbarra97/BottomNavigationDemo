package com.example.bottomnavigationdemo.ui.pages.listamaterias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavigationdemo.ui.pages.listamaterias.todasLasMaterias
import com.example.bottomnavigationdemo.ui.pages.listamaterias.InfoRow

// --- LÓGICA DE DESCRIPCIÓN (Sin cambios) ---
private fun generateShortDescription(materiaNombre: String): String {
    return when {
        materiaNombre.contains("Móviles II") -> "Explora el desarrollo avanzado de aplicaciones móviles, profundizando en frameworks modernos (Jetpack Compose, Swift UI) y patrones de diseño (MVVM, MVI) para crear apps robustas y eficientes."
        materiaNombre.contains("Móviles I") -> "Introduce los fundamentos del desarrollo de aplicaciones móviles, cubriendo los ciclos de vida de la actividad, interfaces de usuario básicas y almacenamiento local."
        materiaNombre.contains("Servicios") -> "Diseña y construye sistemas distribuidos eficientes usando principios de arquitecturas orientadas a servicios (SOA), microservicios y APIs RESTful."
        materiaNombre.contains("Proyectos de Software") -> "Aprende metodologías ágiles (Scrum, Kanban) y herramientas para la gestión exitosa de proyectos de software, desde la planificación y estimación hasta la entrega y el mantenimiento."
        materiaNombre.contains("Seguridad") -> "Estudia técnicas y contramedidas para proteger sistemas y datos contra amenazas cibernéticas, asegurando la integridad y confidencialidad."
        materiaNombre.contains("Minería de Datos") -> "Descubre patrones ocultos y extrae conocimiento valioso de grandes conjuntos de datos (Big Data) utilizando algoritmos de clasificación, clustering y herramientas de machine learning."
        materiaNombre.contains("Inglés") -> "Desarrolla habilidades avanzadas en el idioma inglés (Nivel B2/C1), enfocándose en la comunicación técnica, redacción de reportes y presentaciones profesionales."
        materiaNombre.contains("Expresión Oral") -> "Perfecciona tus habilidades de comunicación (soft skills) para presentar ideas, debatir y redactar documentos de forma clara y efectiva en contextos académicos y profesionales."
        materiaNombre.contains("Tutoría") -> "Sesiones personalizadas de apoyo académico y orientación profesional para potenciar tu desempeño, definir tu ruta de carrera y prepararte para el mundo laboral."
        materiaNombre.contains("Computación") -> "Introduce los conceptos básicos de hardware, software, algoritmos y sistemas operativos que forman la base de la ingeniería de software."
        materiaNombre.contains("Algoritmos") -> "Diseña y analiza algoritmos eficientes, estudiando su complejidad temporal y espacial y aplicando estructuras de datos para resolver problemas complejos."
        materiaNombre.contains("Algebra") -> "Aplica conceptos de álgebra lineal y matemática vectorial para la computación gráfica, el análisis de datos y la optimización de sistemas."
        materiaNombre.contains("Química") -> "Comprende los principios fundamentales de la química y su aplicación en la ciencia de materiales y la tecnología."
        materiaNombre.contains("Beisbol") -> "Desarrollo de habilidades físicas y estratégicas en béisbol como parte de la formación integral y deportiva."
        materiaNombre.contains("Orientada a Objetos") -> "Domina el paradigma de Programación Orientada a Objetos (POO) usando lenguajes como Java o C++, enfocándose en encapsulamiento, herencia y polimorfismo."
        materiaNombre.contains("Atletismo") -> "Desarrollo de la condición física, disciplina y técnicas de atletismo como parte de la formación integral y deportiva."
        materiaNombre.contains("Valores") -> "Reflexiona sobre los principios éticos y valores humanísticos que guían el ejercicio profesional de la ingeniería."
        materiaNombre.contains("Cálculo Diferencial") -> "Estudia los fundamentos del cálculo, límites, derivadas y sus aplicaciones en la modelación de problemas de ingeniería."
        materiaNombre.contains("Estructuras de Datos") -> "Implementa y analiza estructuras de datos fundamentales como listas, pilas, colas, árboles y grafos para la solución de problemas algorítmicos."
        materiaNombre.contains("Cálculo Integral") -> "Aprende técnicas de integración y su aplicación en el cálculo de áreas, volúmenes y la resolución de ecuaciones diferenciales."
        materiaNombre.contains("Bases de Datos") -> "Diseña, implementa y gestiona bases de datos relacionales. Aprende SQL y modelado de datos (ER)."
        materiaNombre.contains("Interfaces") -> "Principios de Diseño de Experiencia de Usuario (UX) e Interfaz de Usuario (UI) para la creación de aplicaciones intuitivas y atractivas."
        materiaNombre.contains("Electricidad") -> "Fundamentos de circuitos eléctricos, ley de Ohm y electromagnetismo aplicados a la ingeniería en computación."
        materiaNombre.contains("Programación Web") -> "Desarrollo de aplicaciones web del lado del cliente (Frontend) y servidor (Backend), cubriendo HTML, CSS, JavaScript y frameworks modernos."
        materiaNombre.contains("Redes") -> "Comprende el modelo OSI, TCP/IP, configuración de routers, switches y protocolos de enrutamiento para la comunicación de datos."
        materiaNombre.contains("Cliente/Servidor") -> "Desarrolla aplicaciones bajo el modelo cliente-servidor, manejando sockets, peticiones y respuestas en un entorno de red."
        materiaNombre.contains("Sistemas Digitales") -> "Diseño y análisis de circuitos lógicos, compuertas y sistemas digitales utilizando álgebra booleana y VHDL."
        materiaNombre.contains("Arquitectura de Computadoras") -> "Estudia la organización interna de una computadora, incluyendo CPU, memoria, buses y periféricos."
        materiaNombre.contains("Pruebas del Software") -> "Aprende diferentes tipos de pruebas (unitarias, integración, sistema) y utiliza herramientas para asegurar la calidad del software."
        materiaNombre.contains("Sistemas Operativos") -> "Comprende la gestión de procesos, hilos, memoria y sistemas de archivos que realiza un sistema operativo."
        materiaNombre.contains("Lenguajes y Autómatas") -> "Estudio formal de los lenguajes de programación, gramáticas y la teoría de autómatas que fundamenta los compiladores."
        materiaNombre.contains("Mantenimiento de Software") -> "Técnicas y estrategias para el mantenimiento correctivo, adaptativo y perfectivo del software, asegurando su longevidad."
        materiaNombre.contains("Compiladores") -> "Diseña y construye las fases de un compilador, desde el análisis léxico y sintáctico hasta la generación de código."
        materiaNombre.contains("Inteligencia Artificial") -> "Introduce los fundamentos de la IA, incluyendo búsqueda, representación del conocimiento, lógica difusa y redes neuronales."
        materiaNombre.contains("Liderazgo") -> "Desarrolla habilidades de liderazgo, negociación y gestión de equipos para dirigir proyectos tecnológicos de alto impacto."
        materiaNombre.contains("Habilidades Gerenciales") -> "Fomenta las competencias clave para la gerencia de TI, incluyendo toma de decisiones, planeación estratégica y finanzas."
        materiaNombre.contains("Ética Profesional") -> "Analiza los dilemas éticos y la responsabilidad social del ingeniero de software en la sociedad de la información."
        else -> "Descripción detallada de la materia, sus objetivos, temario y criterios de evaluación. Esta es una simulación para el performance del proyecto."
    }
}
// --- FIN LÓGICA DE DESCRIPCIÓN ---


@Composable
fun MateriaDetailScreen(
    modifier: Modifier = Modifier,
    materiaId: String?,
    navController: NavController
) {
    val materia = todasLasMaterias[materiaId]

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        // Dejamos el padding horizontal para los items individuales
        // para que el Box del header pueda llenar el ancho
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (materia != null) {
            // --- Encabezado ---
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                        .height(120.dp)
                        .background(
                            materia.color.copy(alpha = 0.8f),
                            shape = MaterialTheme.shapes.medium
                        )
                        // ✅ CAMBIO 1: Este padding interno de 16.dp es el que alinea el texto
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = materia.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- Detalles ---
            item {
                Text(
                    text = "Detalles del Curso",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 22.dp) // Este padding alinea con el título
                        .padding(bottom = 8.dp)
                )
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 22.dp)) { // Este padding alinea
                    InfoRow(icon = Icons.Default.Person, text = materia.profesor)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.5f))
                }
            }

            // Itera sobre los horarios detallados
            materia.horarios.forEachIndexed { index, horario ->
                item {
                    Column(modifier = Modifier.padding(horizontal = 22.dp)) { // Este padding alinea

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Horario",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "${horario.dia}: ${horario.hora}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                // ✅ CAMBIO 2: Se eliminó el modifier = Modifier.weight(1f)
                            )

                            // ✅ CAMBIO 2: Se añadió un Spacer para separar la hora del salón
                            Spacer(modifier = Modifier.width(16.dp))

                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Aula",
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = horario.aula,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (index < materia.horarios.lastIndex) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                        }
                    }
                }
            }

            // --- Descripción ---
            item {
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(modifier = Modifier.padding(horizontal = 22.dp), color = Color.LightGray.copy(alpha = 0.5f)) // Este padding alinea
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 22.dp) // Este padding alinea
                        .padding(bottom = 8.dp)
                )
            }
            item {
                Text(
                    text = generateShortDescription(materia.nombre),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 22.dp) // Este padding alinea
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp)) // Espacio al final
            }

        } else {
            // Caso de error
            item {
                Text(
                    text = "Error: Materia no encontrada",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}