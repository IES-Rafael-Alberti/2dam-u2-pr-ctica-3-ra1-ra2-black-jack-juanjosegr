package com.example.cartas.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cartas.R

@Composable
fun MenuPrinciapl() {

    MostrarTapete(R.drawable.tapetedefinitivo)
    SelectorMenus()


}

@Composable
fun SelectorMenus() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MostrarImagenesJuego(R.drawable.jvsj)
            Spacer(modifier = Modifier.height(32.dp))
            MostrarImagenesJuego(R.drawable.vsia)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier.padding(top= 160.dp)
            ) {
                Text(text = "2 Jugadores")
            }
            Spacer(modifier = Modifier.height(160.dp))
            Button(
                onClick = {
                    // Acción del segundo botón
                },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Vs banca")
            }
        }
    }
}

@Composable
fun MostrarImagenesJuego(@DrawableRes tapeteImprimirResId: Int) {
    Image(
        painter = painterResource(id = tapeteImprimirResId),
        contentDescription = "",
        modifier = Modifier
            .size(width = 250.dp, height = 200.dp),
        contentScale = ContentScale.FillBounds
    )
}