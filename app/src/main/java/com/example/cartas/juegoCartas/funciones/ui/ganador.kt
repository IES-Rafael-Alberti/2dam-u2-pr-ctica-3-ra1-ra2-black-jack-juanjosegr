package com.example.cartas.juegoCartas.funciones.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.juegoCartas.funciones.model.Routes

/**
 * Función que muestra la pantalla del ganador.
 *
 * @param navController Controlador de navegación para la app.
 * @param ganador Nombre del jugador ganador.
 * @param nombresViewModel ViewModel para manejar los nombres de los jugadores.
 * @param juegoPrincipalVM ViewModel principal del juego.
 */

@Composable
fun Ganador(
    navController: NavHostController,
    ganador: String,
    nombresViewModel: NombresViewModel,
    juegoPrincipalVM: JuegoPrincipalVM
) {
    MostrarTapete(R.drawable.tapetepro)

    val jugador1 = nombresViewModel.obtenerNombreJugador1() // Obtiene el nombre del jugador 1
    val jugador2 = nombresViewModel.obtenerNombreJugador2() // Obtiene el nombre del jugador 2
    val reiniciar = juegoPrincipalVM.reiniciarJuego() // Reinicia el juego en el ViewModel principal

    // Diseño de la pantalla usando Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Button(            // Botón para volver a la pantalla de juego versus jugador
                onClick = {
                    reiniciar // Reinicia el juego
                    navController.navigate("${Routes.PantallaVsJugador.route}/$jugador1/$jugador2")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Volver")
            }
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.LightGray)
        ) {
            Text(// Muestra el nombre del ganador en un cuadro de texto
                text = "El ganador es: $ganador",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
