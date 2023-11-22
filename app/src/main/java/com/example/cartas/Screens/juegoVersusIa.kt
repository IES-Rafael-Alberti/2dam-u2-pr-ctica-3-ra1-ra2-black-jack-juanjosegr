package com.example.cartas.Screens



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cartas.R

@Composable
fun JuegoVsIa(navController: NavHostController) {

    MostrarTapete(R.drawable.tapetejugadores)
    BotonVolverAlMenu(navController)


}