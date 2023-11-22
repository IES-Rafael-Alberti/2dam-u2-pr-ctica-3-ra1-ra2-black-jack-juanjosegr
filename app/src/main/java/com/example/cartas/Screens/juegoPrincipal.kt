package com.example.cartas.Screens

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.funciones.Baraja
import com.example.cartas.funciones.Baraja.Companion.obtenerNombreRecurso
import com.example.cartas.funciones.Jugador
import com.example.cartas.model.Routes


@Composable
fun Juego2Jugador(navController: NavHostController) {

    val context = LocalContext.current
    var cartaBocaAbajo by rememberSaveable { mutableStateOf("abajo") }

    var cartaBocaArriba by rememberSaveable { mutableStateOf<Int?>(null) }

    val cartaBocaArribaPlayer2 by rememberSaveable {
        mutableIntStateOf(
            context.resources.getIdentifier(
                cartaBocaAbajo,
                "drawable",
                context.packageName
            )
        )
    }

    var jugador1 = remember { Jugador() }
    var jugador2 = remember { Jugador() }
    var cartasBocaArribaJugador1 by remember { mutableStateOf(listOf<Int>()) }



    MostrarTapete(R.drawable.tapete4)
    MostrarCartasJugador1(cartasBocaArribaJugador1)
    MostrarJugador2(cartaBocaArribaPlayer2)
    MostrarCartaBocaAbajo()
    BotonesJuego(
        onDameCartaClick = {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                cartaBocaAbajo = "abajo"
            }
            val cartaNueva = Baraja.dameCarta()
            cartaNueva.let {
                cartaBocaAbajo = obtenerNombreRecurso(it)
                cartaBocaArriba = actualizarCartaBocaArriba(cartaBocaAbajo, context)

                if (cartaBocaArriba != null && cartaBocaArriba !in cartasBocaArribaJugador1) {
                    cartasBocaArribaJugador1 = cartasBocaArribaJugador1.toMutableList().apply {
                        add(cartaBocaArriba!!)
                    }
                }
            }
        },
        onBarajarClick = {
            Baraja.crearBaraja()
            Baraja.barajar()
            cartaBocaAbajo = "abajo"
            cartaBocaArriba = null
            Baraja.reiniciarCartas()
        }
    )

    BotonVolverAlMenu(navController)

}

@Composable
fun MostrarTapete(@DrawableRes tapeteImprimirResId: Int) {
    Image(
        painter = painterResource(id = tapeteImprimirResId),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MostrarCartasJugador1(cartasBocaArriba: List<Int>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Box{
            LazyRow(
                content = {
                    items(cartasBocaArriba.size) { index ->
                        val carta = cartasBocaArriba[index]
                        Image(
                            painter = painterResource(id = carta),
                            contentDescription = "",
                            modifier = Modifier
                                .size(300.dp)
                                .scale(0.4f)
                                .offset(y = (-320).dp)
                        )
                    }
                },
            )
        }
    }
}

@Composable
fun MostrarCartaBocaAbajo() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.abajo),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .scale(0.4f)
            )
        }
    }
}

@Composable
fun MostrarJugador2(cartaBocaArribaPlayer2: Int) {
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = cartaBocaArribaPlayer2),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .scale(0.4f)
        )
    }

}

@Composable
fun BotonesJuego(
    onDameCartaClick: () -> Unit,
    onBarajarClick: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(20.dp),
        ) {
            Button(
                onClick = { onDameCartaClick() },
                modifier = Modifier.padding(end = 10.dp),
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = "Dame carta",
                    color = Color.White
                )
            }
            Button(
                onClick = { onBarajarClick() },
                colors = ButtonDefaults.textButtonColors(Color.Red)
            ) {
                Text(
                    text = "Barajar",
                    color = Color.White
                )
            }
        }
    }
}

fun actualizarCartaBocaArriba(carta: String, context: Context): Int {
    return context.resources.getIdentifier(carta, "drawable", context.packageName)
}

@Composable
fun BotonVolverAlMenu(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate(Routes.PantallaInicio.route)
        },
    ) {
        Text(text = "Volver al Menú Principal")
    }
}