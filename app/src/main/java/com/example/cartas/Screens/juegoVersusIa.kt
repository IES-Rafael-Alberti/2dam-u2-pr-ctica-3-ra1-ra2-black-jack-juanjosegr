package com.example.cartas.Screens



import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.funciones.Baraja

@Composable
fun JuegoVsIa(navController: NavHostController) {

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

    MostrarTapete(R.drawable.tapete4)
    //MostrarJugador1(cartaBocaArriba)
    MostrarJugador2(cartaBocaArribaPlayer2)

    BotonesJuego(
        onDameCartaClick = {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                cartaBocaAbajo = "abajo"
            }
            val cartaNueva = Baraja.dameCarta()
            cartaNueva.let {
                cartaBocaAbajo = Baraja.obtenerNombreRecurso(it)
                cartaBocaArriba = actualizarCartaBocaArriba(cartaBocaAbajo, context)
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