package com.example.cartas.juegoCartas.funciones.ui



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cartas.R

/**
 * Función que muestra la pantalla de juego versus la inteligencia artificial.
 *
 * @param navController Controlador de navegación para la app.
 */
@Composable
fun JuegoVsIa(navController: NavHostController) {

    MostrarTapete(R.drawable.tapetejugadores)
    BotonVolverAlMenu(navController)


}