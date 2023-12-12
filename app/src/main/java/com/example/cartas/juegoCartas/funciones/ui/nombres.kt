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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.juegoCartas.funciones.model.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nombres (navController: NavHostController, nombresViewModel: NombresViewModel){

    MostrarTapete(R.drawable.tapetepro)

    var nombreJugador1 by rememberSaveable { mutableStateOf("") }
    var nombreJugador2 by rememberSaveable { mutableStateOf("") }

    var showDialog by rememberSaveable { mutableStateOf(true) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ingrese el nombre del Jugador 1:", color = Color.White)
                TextField(
                    value = nombreJugador1,
                    onValueChange = { nombreJugador1 = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Text("Ingrese el nombre del Jugador 2:",color = Color.White)
                TextField(
                    value = nombreJugador2,
                    onValueChange = { nombreJugador2 = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Button(
                    onClick = {
                        showDialog = false

                        val nombreDefectoJugador1 = if (nombreJugador1.isBlank()) "Jugador 1" else nombreJugador1
                        val nombreDefectoJugador2 = if (nombreJugador2.isBlank()) "Jugador 2" else nombreJugador2

                        nombresViewModel.guardarNombres(nombreDefectoJugador1, nombreDefectoJugador2)

                        navController.navigate("${Routes.PantallaVsJugador.route}/$nombreDefectoJugador1/$nombreDefectoJugador2")

                    }
                ) {
                    Text("Comenzar Juego")
                }
            }
        }
    }
}