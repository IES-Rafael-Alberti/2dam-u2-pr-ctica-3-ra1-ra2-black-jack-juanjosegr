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
import com.example.cartas.juegoCartas.funciones.ui.JuegoVsIa
import com.example.cartas.juegoCartas.funciones.ui.MenuPrincipal
import com.example.cartas.juegoCartas.funciones.ui.Nombres
import com.example.cartas.juegoCartas.funciones.ui.NombresViewModel
import com.example.cartas.juegoCartas.funciones.model.Routes

class MainActivity : ComponentActivity() {
    private val nombresViewModel by viewModels<NombresViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.PantallaInicio.route
            ) {
                composable(Routes.PantallaInicio.route) {
                    MenuPrincipal(navController)
                }
                composable(Routes.PantallaVsIa.route) {
                    JuegoVsIa(navController)
                }
                composable(Routes.GanadorScreen.route + "/{ganador}") { backStackEntry ->
                    val ganador = backStackEntry.arguments?.getString("ganador") ?: "Ganador Desconocido"
                    Ganador(navController, ganador,nombresViewModel)
                }
                composable(Routes.PantallaVsJugador.route + "/{jugador1}/{jugador2}") { backStackEntry ->
                    val jugador1 = backStackEntry.arguments?.getString("jugador1") ?: ""
                    val jugador2 = backStackEntry.arguments?.getString("jugador2") ?: ""
                    Juego2Jugador(navController, nombresViewModel)
                }
                composable(Routes.Nombres.route) {
                    Nombres(navController, nombresViewModel)
                }
            }
        }
    }
}

