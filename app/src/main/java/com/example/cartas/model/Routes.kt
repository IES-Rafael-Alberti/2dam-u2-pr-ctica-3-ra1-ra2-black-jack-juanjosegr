package com.example.cartas.model

sealed class Routes(val route: String) {
    object PantallaInicio : Routes("MenuPrincipal")
    object PantallaVsJugador : Routes("Juego2Jugador")
    object PantallaVsIa : Routes("PantallaVsIa")
}