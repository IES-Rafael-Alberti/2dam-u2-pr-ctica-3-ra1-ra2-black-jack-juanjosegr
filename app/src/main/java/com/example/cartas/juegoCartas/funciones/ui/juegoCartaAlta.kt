package com.example.cartas.juegoCartas.funciones.ui


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cartas.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("DiscouragedApi")
@Composable
fun JuegoCartaAlta(navController: NavHostController, juegoCartaAltaVM: juegoCartaAltaVM) {

    val context = LocalContext.current
    MostrarTapete(R.drawable.tapetepro)
    BotonVolverAlMenu(navController)

    val cartaBocaAbajo by juegoCartaAltaVM.cartaBocaAbajo.observeAsState("cartaabajoguapa")
    val cartaBocaArriba by juegoCartaAltaVM.cartaBocaArriba.observeAsState(R.drawable.cartaabajoguapa)
    val contadorJugador1 by juegoCartaAltaVM.contadorjug1.observeAsState(0)

    actualizarCartaBocaArriba2(cartaBocaAbajo,context)

    MostrarCartas(
        cartaBocaArriba = cartaBocaArriba,
        onDameCartaClick = {
            juegoCartaAltaVM.obtenerNuevaCarta()
        },
        onBarajarClick = {
            juegoCartaAltaVM.barajarCartas()
        },
        contadorJugador1
    )
}

@SuppressLint("DiscouragedApi")
fun actualizarCartaBocaArriba2(carta: String, context: Context): Int {
    return context.resources.getIdentifier(carta, "drawable", context.packageName)
}
@Composable
fun MostrarCartas(cartaBocaArriba: Int, onDameCartaClick: () -> Unit, onBarajarClick: () -> Unit,contadorJugador1:Int) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = cartaBocaArriba),
                contentDescription = ""
            )
        }
        Row(
            modifier = Modifier.padding(20.dp)
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
