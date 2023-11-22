package com.example.cartas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartas.Screens.*
import com.example.cartas.model.Routes

class MainActivity : ComponentActivity() {
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
                composable(Routes.PantallaVsJugador.route) {
                    Juego2Jugador(navController)
                }
                composable(Routes.PantallaVsIa.route) {
                    JuegoVsIa(navController)
                }
            }
        }
    }
}

