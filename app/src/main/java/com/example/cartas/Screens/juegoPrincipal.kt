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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.funciones.Baraja
import com.example.cartas.funciones.Baraja.Companion.obtenerNombreRecurso
import com.example.cartas.model.Routes


@Composable
fun Juego2Jugador(navController: NavHostController) {

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

    val cartaBocaArribaPlayer2 by rememberSaveable {
        mutableStateOf(
            context.resources.getIdentifier(
                cartaBocaAbajo,
                "drawable",
                context.packageName
            )
        )
    }

    MostrarTapete(R.drawable.tapete4)
    MostrarJugador1(cartaBocaArriba)
    MostrarJugador2(cartaBocaArribaPlayer2)

    BotonesJuego(
        onDameCartaClick = {
            Baraja.barajar()
            if (Baraja.listaCartas.size == 0) {
                Baraja.crearBaraja()
                cartaBocaAbajo = "abajo"
            }
            val cartaNueva = Baraja.dameCarta()
            cartaNueva.let {
                cartaBocaAbajo = obtenerNombreRecurso(it)
                cartaBocaArriba = actualizarCartaBocaArriba(cartaBocaAbajo, context)
            }
            println("$cartaNueva || $cartaBocaAbajo || ${Baraja.listaCartas.size}")

            cartaBocaArriba = actualizarCartaBocaArriba(cartaBocaAbajo, context)
        },
        onBarajarClick = {
            Baraja.crearBaraja()
            Baraja.barajar()
            cartaBocaAbajo = "abajo"

            cartaBocaArriba = actualizarCartaBocaArriba(cartaBocaAbajo, context)

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
fun MostrarJugador1(cartaBocaArriba: Int) {
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
                    .scale(0.5f)
            )
            Image(
                painter = painterResource(id = cartaBocaArriba),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .scale(0.5f)
                    .offset(y = (-320).dp)
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
                .scale(0.5f)
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