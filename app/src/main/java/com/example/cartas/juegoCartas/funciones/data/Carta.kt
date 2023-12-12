package com.example.cartas.juegoCartas.funciones.data

class Carta(
    var nombre: Naipes,
    var palo: Palos,
    var puntosMin: Int = 0,
    var puntosMax: Int = 0,
    var idDrawable: Int = 0,
    var usada: Boolean = false
) {
    override fun toString(): String {
        return "Carta(nombre=$nombre, palo=$palo, puntosMin=$puntosMin, puntosMax=$puntosMax, idDrawable=$idDrawable, usada=$usada)"
    }
}