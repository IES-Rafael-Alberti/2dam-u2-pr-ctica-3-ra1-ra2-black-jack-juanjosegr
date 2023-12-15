package com.example.cartas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartas.juegoCartas.funciones.ui.Ganador
import com.example.cartas.juegoCartas.funciones.ui.Juego2Jugador
import com.example.cartas.juegoCartas.funciones.ui.JuegoCartaAlta
import com.example.cartas.juegoCartas.funciones.ui.MenuPrincipal
import com.example.cartas.juegoCartas.funciones.ui.Nombres
import com.example.cartas.juegoCartas.funciones.ui.NombresViewModel
import com.example.cartas.juegoCartas.funciones.model.Routes
import com.example.cartas.juegoCartas.funciones.ui.JuegoPrincipalVM
import com.example.cartas.juegoCartas.funciones.ui.juegoCartaAltaVM

/**
 * Clase principal que representa la actividad principal de la aplicación.
 */
class MainActivity : ComponentActivity() {
    private val nombresViewModel: NombresViewModel by viewModels()     // ViewModel para gestionar los nombres de los jugadores
    private val juegoViewModel: JuegoPrincipalVM by viewModels()    // ViewModel principal del juego
    private val juegoCartaAltaViewModel: juegoCartaAltaVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()            // Controlador de navegación para la app

            // Configuración de la navegación entre pantallas
            NavHost(
                navController = navController,
                startDestination = Routes.PantallaInicio.route
            ) {
                composable(Routes.PantallaInicio.route) {
                    MenuPrincipal(navController)
                }
                composable(Routes.PantallaVsIa.route) {
                    JuegoCartaAlta(navController,juegoCartaAltaViewModel)
                }
                composable(Routes.GanadorScreen.route + "/{ganador}") { backStackEntry ->
                    val ganador = backStackEntry.arguments?.getString("ganador") ?: "Ganador Desconocido"
                    Ganador(navController, ganador,nombresViewModel,juegoViewModel)
                }
                composable(Routes.Nombres.route) {
                    Nombres(navController, nombresViewModel)
                }
                composable(Routes.PantallaVsJugador.route + "/{jugador1}/{jugador2}") {
                    Juego2Jugador(navController, nombresViewModel, juegoViewModel)
                }
            }
        }
    }
}

