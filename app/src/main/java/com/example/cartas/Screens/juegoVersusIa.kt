package com.example.cartas.Screens


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.funciones.Baraja
import com.example.cartas.model.Routes

@Composable
fun JuegoVsIa(navController: NavHostController) {

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
                cartaBocaAbajo = Baraja.obtenerNombreRecurso(it)
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