package com.example.cartas.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.cartas.R
import com.example.cartas.Screens.Baraja.Companion.obtenerNombreRecurso

@Composable
fun Juego() {

    val context = LocalContext.current
    var cartaBocaAbajo by rememberSaveable { mutableStateOf("abajo") }
    var cartaBocaArriba by rememberSaveable {
        mutableStateOf(
            context.resources.getIdentifier(
                cartaBocaAbajo,
                "drawable",
                context.packageName
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.tapete4),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 105.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = cartaBocaArriba),
                contentDescription = ""
            )
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(20.dp),
        ) {
            Button(
                onClick = {
                    Baraja.barajar()
                    if (Baraja.listaCartas.size == 0) {
                        Baraja.crearBaraja()
                        cartaBocaAbajo = "abajo"
                    }
                    val cartaNueva = Baraja.dameCarta()
                    cartaBocaAbajo = obtenerNombreRecurso(cartaNueva)
                    println("$cartaNueva || $cartaBocaAbajo || ${Baraja.listaCartas.size}")
                }, modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = "Dame carta",
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    Baraja.crearBaraja()
                    Baraja.barajar()
                    cartaBocaAbajo = "abajo"
                },
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = "Barajar",
                    color = Color.White
                )
            }
        }
    }

    LaunchedEffect(cartaBocaAbajo) {
        val carta = context.resources.getIdentifier(cartaBocaAbajo, "drawable", context.packageName)
        cartaBocaArriba = carta
    }


}