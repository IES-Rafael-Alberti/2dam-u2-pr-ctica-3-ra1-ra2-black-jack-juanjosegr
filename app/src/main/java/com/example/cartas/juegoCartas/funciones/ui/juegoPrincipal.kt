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
import androidx.navigation.NavHostController
import com.example.cartas.R

/**
 * Composable que muestra la interfaz de juego entre dos jugadores.
 *
 * @param navController Controlador de navegación para la app.
 * @param nombresViewModel ViewModel que gestiona los nombres de los jugadores.
 * @param juegoViewModel ViewModel principal del juego.
 */
@Composable
fun Juego2Jugador(
    navController: NavHostController,
    nombresViewModel: NombresViewModel,
    juegoViewModel: JuegoPrincipalVM
) {
    MostrarTapete(R.drawable.tapetepro) // Muestra un tapete de juego

    MostrarCartaBocaAbajo() // Muestra una carta boca abajo

    val context = LocalContext.current // Obtiene el contexto actual

    // Obtiene los nombres de los jugadores
    val jugador1 = nombresViewModel.obtenerNombreJugador1()
    val jugador2 = nombresViewModel.obtenerNombreJugador2()

    // Observa el puntaje de los jugadores
    val puntajeJugador1 by juegoViewModel.puntajeJugador1.observeAsState(0)
    val puntajeJugador2 by juegoViewModel.puntajeJugador2.observeAsState(0)

    // Observa las listas de cartas boca arriba de cada jugador
    val listaCartasBocaArribaJugador1 by juegoViewModel.listaCartasBocaArribaJugador1.observeAsState(emptyList())
    val listaCartasBocaArribaJugador2 by juegoViewModel.listaCartasBocaArribaJugador2.observeAsState(emptyList())

    // Observa los textos asociados al estado de plantado de cada jugador
    val textoPlantadoJug1 by juegoViewModel.textoPlantadoJug1.observeAsState("")
    val textoPlantadoJug2 by juegoViewModel.textoPlantadoJug2.observeAsState("")

    // Observa los contadores asociados a cada jugador
    val contadorJugador1 by juegoViewModel.contadorjug1.observeAsState(0)
    val contadorJugador2 by juegoViewModel.contadorjug2.observeAsState(0)

    // Muestra las cartas boca arriba del jugador 1
    MostrarCartasJugador1(listaCartasBocaArribaJugador1, contadorJugador1)

    // Muestra las cartas boca arriba del jugador 2
    MostrarCartasJugador2(listaCartasBocaArribaJugador2, contadorJugador2)

    // Determina el ganador del juego
    juegoViewModel.determinarGanador(navController, puntajeJugador1, puntajeJugador2, jugador1, jugador2)

    // Botones para el jugador 1
    BotonesJugador1(
        onDameCartaClick = {
            juegoViewModel.obtenerCartaJugador1(context)
        },
        onPlantarseClick = {
            juegoViewModel.plantarseJug1()
        },
        textoBoton = textoPlantadoJug1
    )

    // Botones para el jugador 2
    BotonesJugador2(
        onDameCartaClick = {
            juegoViewModel.obtenerCartaJugador2(context)
        },
        onPlantarseClick = {
            juegoViewModel.plantarseJug2()
        },
        textoBoton = textoPlantadoJug2
    )

    // Botón para barajar las cartas
    BotonBarajas(
        onBarajarClick = {
            juegoViewModel.reiniciarJuego()
        }
    )

    // Botón para volver al menú principal
    BotonVolverAlMenu(navController)

    // Muestra los nombres y puntajes de los jugadores en pantalla
    TextosEnPantalla(
        name1 = jugador1,
        name2 = jugador2,
        puntajeJugador1 = puntajeJugador1,
        puntajeJugador2 = puntajeJugador2,
    )
}

/**
 * Obtiene el identificador de recursos de una carta específica para mostrarla boca arriba.
 *
 * @param carta Nombre de la carta a mostrar.
 * @param context Contexto de la aplicación.
 * @return Identificador de recursos de la carta a mostrar.
 */
@SuppressLint("DiscouragedApi")
fun actualizarCartaBocaArriba(carta: String, context: Context): Int {
    return context.resources.getIdentifier(carta, "drawable", context.packageName)
}

/**
 * Composable que muestra las cartas boca arriba del jugador 1 en una disposición de fila horizontal.
 *
 * @param cartasBocaArriba Lista de identificadores de recursos de las cartas boca arriba del jugador 1.
 * @param contcartas Cantidad de cartas boca arriba del jugador 1.
 */
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

/**
 * Composable que muestra las cartas boca arriba del jugador 2 en una disposición de fila horizontal.
 *
 * @param cartasBocaArriba Lista de identificadores de recursos de las cartas boca arriba del jugador 2.
 * @param contcartas Cantidad de cartas boca arriba del jugador 2.
 */
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

/**
 * Función que muestra dos imágenes de una carta boca abajo en la pantalla.
 */
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

/**
 * Función que muestra los botones de acción para el jugador 1 en la interfaz del juego.
 *
 * @param onDameCartaClick Acción a ejecutar al hacer clic en el botón "Dame carta".
 * @param onPlantarseClick Acción a ejecutar al hacer clic en el botón "Plantarse".
 * @param textoBoton Texto dinámico para el botón principal ("Dame carta" o "Plantarse").
 */
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

/**
 * Función que muestra los botones de acción para el jugador 2 en la interfaz del juego.
 *
 * @param onDameCartaClick Acción a ejecutar al hacer clic en el botón "Dame carta".
 * @param onPlantarseClick Acción a ejecutar al hacer clic en el botón "Plantarse".
 * @param textoBoton Texto dinámico para el botón principal ("Dame carta" o "Plantarse").
 */
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

/**
 * Composable que muestra un botón para barajar las cartas en la interfaz del juego.
 *
 * @param onBarajarClick Acción a ejecutar al hacer clic en el botón "Barajar".
 */
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

/**
 * Composable que muestra los puntajes de los jugadores en la interfaz del juego.
 *
 * @param name1 Nombre del jugador 1.
 * @param name2 Nombre del jugador 2.
 * @param puntajeJugador1 Puntaje del jugador 1.
 * @param puntajeJugador2 Puntaje del jugador 2.
 */
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