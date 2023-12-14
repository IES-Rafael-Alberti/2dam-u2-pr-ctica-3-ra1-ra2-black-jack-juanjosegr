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
import com.example.cartas.juegoCartas.funciones.ui.juegoPrincipalVM

class MainActivity : ComponentActivity() {
    private val nombresViewModel: NombresViewModel by viewModels()
    private val juegoViewModel: juegoPrincipalVM by viewModels()

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

