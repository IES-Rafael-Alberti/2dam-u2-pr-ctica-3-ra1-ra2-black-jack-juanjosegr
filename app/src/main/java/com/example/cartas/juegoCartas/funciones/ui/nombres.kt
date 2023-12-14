package com.example.cartas.juegoCartas.funciones.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.juegoCartas.funciones.model.Routes

/**
 * Composable que permite ingresar los nombres de los jugadores antes de comenzar el juego.
 *
 * @param navController Controlador de navegación para la app.
 * @param nombresViewModel ViewModel que gestiona los nombres de los jugadores.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nombres(navController: NavHostController, nombresViewModel: NombresViewModel) {

    MostrarTapete(R.drawable.tapetepro)

    val nombreJugador1 by nombresViewModel.jugador1Nombre.observeAsState("") // Observa el nombre del jugador 1
    val nombreJugador2 by nombresViewModel.jugador2Nombre.observeAsState("") // Observa el nombre del jugador 2

    var showDialog by rememberSaveable { mutableStateOf(true) }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ingrese el nombre del Jugador 1:", color = Color.White)
                // Campo de texto para el nombre del jugador 2
                TextField(
                    value = nombreJugador1,
                    onValueChange = { nombresViewModel.guardarNombreJugador1(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Text("Ingrese el nombre del Jugador 2:", color = Color.White)
                // Campo de texto para el nombre del jugador 2
                TextField(
                    value = nombreJugador2,
                    onValueChange = { nombresViewModel.guardarNombreJugador2(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                // Botón para comenzar el juego con los nombres ingresados
                Button(
                    onClick = {
                        showDialog = false // Oculta el diálogo al comenzar el juego

                        // Si no se ingresa un nombre, se establece un nombre por defecto
                        val nombreDefectoJugador1 =
                            if (nombreJugador1.isBlank()) "Jugador 1" else nombreJugador1
                        val nombreDefectoJugador2 =
                            if (nombreJugador2.isBlank()) "Jugador 2" else nombreJugador2

                        // Guarda los nombres ingresados en el ViewModel
                        nombresViewModel.guardarNombres(
                            nombreDefectoJugador1,
                            nombreDefectoJugador2
                        )

                        // Navega a la pantalla de juego versus jugador con los nombres establecidos
                        navController.navigate("${Routes.PantallaVsJugador.route}/$nombreDefectoJugador1/$nombreDefectoJugador2")
                    }
                ) {
                    Text("Comenzar Juego")
                }
            }
        }
    }
}