package com.example.cartas.juegoCartas.funciones.ui

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.juegoCartas.funciones.data.Baraja


@Composable
fun Juego2Jugador(navController: NavHostController, nombresViewModel: NombresViewModel, juegoViewModel: juegoPrincipalVM) {
    MostrarTapete(R.drawable.tapetepro)
    MostrarCartaBocaAbajo()

    val context = LocalContext.current

    val jugador1 = nombresViewModel.obtenerNombreJugador1()
    val jugador2 = nombresViewModel.obtenerNombreJugador2()

    val puntajeJugador1 by juegoViewModel.puntajeJugador1.observeAsState(0)
    val puntajeJugador2 by juegoViewModel.puntajeJugador2.observeAsState(0)

    val listaCartasBocaArribaJugador1 by juegoViewModel.listaCartasBocaArribaJugador1.observeAsState(emptyList())
    val listaCartasBocaArribaJugador2 by juegoViewModel.listaCartasBocaArribaJugador2.observeAsState(emptyList())

    val textoPlantadoJug1 by juegoViewModel.textoPlantadoJug1.observeAsState("")
    val textoPlantadoJug2 by juegoViewModel.textoPlantadoJug2.observeAsState("")

    val contadorJugador1 by juegoViewModel.contadorjug1.observeAsState(0)
    val contadorJugador2 by juegoViewModel.contadorjug2.observeAsState(0)


    MostrarCartasJugador1(listaCartasBocaArribaJugador1,contadorJugador1)
    MostrarCartasJugador2(listaCartasBocaArribaJugador2,contadorJugador2)

    juegoViewModel.determinarGanador(navController,puntajeJugador1, puntajeJugador2,jugador1, jugador2)


    BotonesJugador1(
        onDameCartaClick = {
            juegoViewModel.obtenerCartaJugador1(context)
        },
        onPlantarseClick = {
            juegoViewModel.plantarseJug1()
        },
        textoBoton = textoPlantadoJug1
    )

    BotonesJugador2(
        onDameCartaClick = {
            juegoViewModel.obtenerCartaJugador2(context)
        },
        onPlantarseClick = {
            juegoViewModel.plantarseJug2()
        },
        textoBoton = textoPlantadoJug2
    )

    BotonBarajas(
        onBarajarClick = {
            juegoViewModel.reiniciarJuego()
        })

    BotonVolverAlMenu(navController)

    TextosEnPantalla(
        name1 = jugador1,
        name2 = jugador2,
        puntajeJugador1 = puntajeJugador1,
        puntajeJugador2 = puntajeJugador2,
    )
}

@SuppressLint("DiscouragedApi")
fun actualizarCartaBocaArriba(carta: String, context: Context): Int {
    return context.resources.getIdentifier(carta, "drawable", context.packageName)
}

@Composable
fun MostrarCartasJugador1(cartasBocaArriba: List<Int>, contcartas:Int ) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 280.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        LazyRow(
            content = {
                items(cartasBocaArriba) { carta ->
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
fun MostrarCartasJugador2(cartasBocaArriba: List<Int>,contcartas:Int) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 250.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyRow(
            content = {
                items(cartasBocaArriba) { carta ->
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