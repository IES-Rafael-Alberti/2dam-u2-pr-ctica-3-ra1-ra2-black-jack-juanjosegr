package com.example.cartas.Screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.funciones.Baraja
import com.example.cartas.funciones.Baraja.Companion.obtenerNombreRecurso
import com.example.cartas.funciones.Carta


@Composable
fun Juego2Jugador(navController: NavHostController) {

    val context = LocalContext.current
    var barajasNuevasJug1 by rememberSaveable { mutableStateOf("abajo") }
    var barajasNuevasJug2 by rememberSaveable { mutableStateOf("abajo") }

    var cartaBocaArribaJugador1 by rememberSaveable { mutableStateOf<Int?>(null) }
    var cartaBocaArribaJugador2 by rememberSaveable { mutableStateOf<Int?>(null) }

    var listaCartasBocaArribaJugador1 by remember { mutableStateOf(listOf<Int>()) }
    var listaCartasBocaArribaJugador2 by remember { mutableStateOf(listOf<Int>()) }

    var permitirObtenerCarta1 by remember { mutableStateOf(true) }
    var permitirObtenerCarta2 by remember { mutableStateOf(true) }

    var textoPlantadoJug1 by remember { mutableStateOf("Dame carta") }
    var textoPlantadoJug2 by remember { mutableStateOf("Dame carta") }

    var cartasJugador1 by remember { mutableStateOf(listOf<Carta>()) }
    var cartasJugador2 by remember { mutableStateOf(listOf<Carta>()) }

    var puntajeJugador1 by remember { mutableIntStateOf(0) }
    var puntajeJugador2 by remember { mutableIntStateOf(0) }

    if (puntajeJugador1 >= 21){
        permitirObtenerCarta1 = false
        textoPlantadoJug1 = "Plantado"
    }

    if (puntajeJugador2 >= 21) {
        permitirObtenerCarta2 = false
        textoPlantadoJug2 = "Plantado"
    }

    determinarGanador(puntajeJugador1,puntajeJugador2)

    MostrarTapete(R.drawable.tapetepro)
    MostrarCartaBocaAbajo()

    MostrarCartasJugador1(listaCartasBocaArribaJugador1)
    MostrarCartasJugador2(listaCartasBocaArribaJugador2)

    BotonesJugador1(
        onDameCartaClick = {
            if (permitirObtenerCarta1) {
                Baraja.barajar()
                if (Baraja.listaCartas.isEmpty()) {
                    Baraja.crearBaraja()
                    barajasNuevasJug1 = "abajo"
                }
                val cartaNueva = Baraja.dameCarta()
                cartaNueva.let {
                    barajasNuevasJug1 = obtenerNombreRecurso(it)
                    val cartaActualizada = actualizarCartaBocaArriba(barajasNuevasJug1, context)
                    cartaBocaArribaJugador1 = cartaActualizada
                    if (cartaActualizada !in listaCartasBocaArribaJugador1) {
                        listaCartasBocaArribaJugador1 += cartaActualizada
                    }
                }
                cartasJugador1 += cartaNueva
                puntajeJugador1 = Baraja.calcularPuntaje(cartasJugador1)
            }
        },
        onPlantarseClick = {
            permitirObtenerCarta1 = false
            textoPlantadoJug1 = "Plantado"
        },
        textoBoton = textoPlantadoJug1
    )

    BotonesJugador2(
        onDameCartaClick = {
            if (permitirObtenerCarta2) {
                Baraja.barajar()
                if (Baraja.listaCartas.isEmpty()) {
                    Baraja.crearBaraja()
                    barajasNuevasJug2 = "abajo"
                }
                val cartaNueva = Baraja.dameCarta()
                cartaNueva.let {
                    barajasNuevasJug2 = obtenerNombreRecurso(it)
                    val cartaActualizada = actualizarCartaBocaArriba(barajasNuevasJug2, context)
                    cartaBocaArribaJugador2 = cartaActualizada
                    if (cartaActualizada !in listaCartasBocaArribaJugador2) {
                        listaCartasBocaArribaJugador2 += cartaActualizada
                    }
                }
                cartasJugador2 += cartaNueva
                puntajeJugador2 = Baraja.calcularPuntaje(cartasJugador2)
            }
        },
        onPlantarseClick = {
            permitirObtenerCarta2 = false
            textoPlantadoJug2 = "Plantado"
        },
        textoBoton = textoPlantadoJug2
    )

    BotonBarajas(
        onBarajarClick = {
            Baraja.crearBaraja()
            Baraja.barajar()
            Baraja.reiniciarCartas()
            barajasNuevasJug1 = "abajo"
            barajasNuevasJug2 = "abajo"
            cartaBocaArribaJugador1 = null
            cartaBocaArribaJugador2 = null
            listaCartasBocaArribaJugador1 = emptyList()
            listaCartasBocaArribaJugador2 = emptyList()
            permitirObtenerCarta1 = true
            permitirObtenerCarta2 = true
            textoPlantadoJug1 = "Dame carta"
            textoPlantadoJug2 = "Dame carta"
            puntajeJugador1 = 0
            puntajeJugador2 = 0
            cartasJugador1 = emptyList()
            cartasJugador2 = emptyList()
        })

    BotonVolverAlMenu(navController)

    TextosEnPantalla(
        name1 = "Elian",
        name2 = "Daniel",
        puntajeJugador1 = puntajeJugador1,
        puntajeJugador2 = puntajeJugador2,
    )
}


fun determinarGanador(puntajeJugador1: Int, puntajeJugador2: Int) {
    when {
        puntajeJugador1 < 22 && puntajeJugador2 < 22 -> {
            when {
                puntajeJugador1 > puntajeJugador2 -> println("Gana jugador 1 linea 1")
                puntajeJugador1 == puntajeJugador2 -> println("Empate por cartas")
                else -> println("Gana jugador 2 linea 1")
            }
        }
        puntajeJugador1 > 21 && puntajeJugador2 > 21 -> println("Empate por pasarse ambos jugadores")
        puntajeJugador1 < 22 && puntajeJugador2 > 21 -> println("Gana jugador 1 por jugador 2 pasado")
        puntajeJugador1 > 21 && puntajeJugador2 < 22 -> println("Gana jugador 2 por jugador 1 pasado")
    }
}


@Composable
fun MostrarCartasJugador1(cartasBocaArriba: List<Int>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 280.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        LazyRow(
            content = {
                items(cartasBocaArriba.size) { index ->
                    val carta = cartasBocaArriba[index]
                    Image(
                        painter = painterResource(id = carta),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }
        )
    }
}


@Composable
fun MostrarCartasJugador2(cartasBocaArriba: List<Int>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 250.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyRow(
            content = {
                items(cartasBocaArriba.size) { index ->
                    val carta = cartasBocaArriba[index]
                    Image(
                        painter = painterResource(id = carta),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun MostrarCartaBocaAbajo() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 125.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.cartaabajoguapa),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.cartaabajoguapa),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
}

@Composable
fun BotonesJugador1(
    onDameCartaClick: () -> Unit,
    onPlantarseClick: () -> Unit,
    textoBoton: String
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 45.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(20.dp),
        ) {
            Button(
                onClick = {
                    if (textoBoton == "Dame carta") {
                        onDameCartaClick()
                    } else {
                        onPlantarseClick()
                    }
                },
                modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = textoBoton,
                    color = Color.White,
                )
            }
            Button(
                onClick = { onPlantarseClick() },
                modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.DarkGray)
            ) {
                Text(
                    text = "Plantarse",
                    color = Color.White,

                    )
            }
        }
    }
}

@Composable
fun BotonesJugador2(
    onDameCartaClick: () -> Unit,
    onPlantarseClick: () -> Unit,
    textoBoton: String
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 28.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(20.dp),
        ) {
            Button(
                onClick = {
                    if (textoBoton == "Dame carta") {
                        onDameCartaClick()
                    } else {
                        onPlantarseClick()
                    }
                },
                modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = textoBoton,
                    color = Color.White,
                )
            }
            Button(
                onClick = { onPlantarseClick() },
                modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.DarkGray)
            ) {
                Text(
                    text = "Plantarse",
                    color = Color.White,

                    )
            }
        }
    }
}

@Composable
fun BotonBarajas(onBarajarClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(20.dp),
        ) {
            Button(
                onClick = { onBarajarClick() },
                colors = ButtonDefaults.textButtonColors(Color.LightGray)
            ) {
                Text(
                    text = "Barajar",
                    color = Color.Black
                )
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
fun actualizarCartaBocaArriba(carta: String, context: Context): Int {
    return context.resources.getIdentifier(carta, "drawable", context.packageName)
}

@Composable
fun TextosEnPantalla (name1: String,name2: String,puntajeJugador1: Int,puntajeJugador2: Int) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Text(
            text = "Score $name1: $puntajeJugador1",
            Modifier
                .align(Alignment.CenterStart),
            color = Color.White,
            fontSize = 24.sp,
        )
        Text(
            text = "Score $name2: $puntajeJugador2",
            Modifier
                .align(Alignment.CenterStart)
                .padding(bottom = 60.dp),
            color = Color.White,
            fontSize = 24.sp,

            )
    }
}