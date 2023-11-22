package com.example.cartas.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cartas.R
import com.example.cartas.model.Routes

@Composable
fun MenuPrincipal(navController: NavHostController) {

    MostrarTapete(R.drawable.tapeteia)
    SelectorMenus(navController)

}

@Composable
fun SelectorMenus(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MostrarImagenesJuego(R.drawable.jugadorvsjugador)
            Spacer(modifier = Modifier.height(32.dp))
            MostrarImagenesJuego(R.drawable.jugadorvsia)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.PantallaVsJugador.route)
                },
                modifier = Modifier.padding(top= 160.dp)
            ) {
                Text(text = "2 Jugadores")
            }
            Spacer(modifier = Modifier.height(160.dp))
            Button(
                onClick = {
                     navController.navigate(Routes.PantallaVsIa.route)
                },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Vs banca")
            }
        }
    }
}

@Composable
fun MostrarImagenesJuego(@DrawableRes imprimirRedIdJugadores: Int) {
    Image(
        painter = painterResource(id = imprimirRedIdJugadores),
        contentDescription = "",
        modifier = Modifier
            .size(width = 250.dp, height = 200.dp),
        contentScale = ContentScale.FillBounds
    )
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
fun BotonVolverAlMenu(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate(Routes.PantallaInicio.route)
        },
    ) {
        Text(text = "Volver al Menú Principal")
    }
}