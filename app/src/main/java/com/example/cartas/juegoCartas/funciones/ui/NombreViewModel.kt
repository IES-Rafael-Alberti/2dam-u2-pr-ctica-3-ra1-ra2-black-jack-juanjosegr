package com.example.cartas.juegoCartas.funciones.ui

import androidx.lifecycle.ViewModel

class NombresViewModel : ViewModel() {
    private var jugador1Nombre: String = ""
    private var jugador2Nombre: String = ""

    fun guardarNombres(jugador1: String, jugador2: String) {
        jugador1Nombre = jugador1
        jugador2Nombre = jugador2
    }

    fun obtenerNombreJugador1(): String {
        return jugador1Nombre
    }

    fun obtenerNombreJugador2(): String {
        return jugador2Nombre
    }
}