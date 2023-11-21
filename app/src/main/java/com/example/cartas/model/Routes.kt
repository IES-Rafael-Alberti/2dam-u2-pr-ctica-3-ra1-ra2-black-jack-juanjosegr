package com.example.cartas.model

sealed class Routes(val route: String) {

    object Pantalla1: Routes("pantalla1")
    object Pantalla2: Routes("pantalla2")
    object Pantalla3: Routes("pantalla3")


}