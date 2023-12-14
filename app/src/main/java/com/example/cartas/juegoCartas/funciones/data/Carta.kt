package com.example.cartas.juegoCartas.funciones.data

/**
 * Clase que representa una carta de juego.
 *
 * @property nombre Nombre de la carta (por ejemplo: As, Dos, Tres, etc.).
 * @property palo Palo de la carta (por ejemplo: Corazones, Diamantes, etc.).
 * @property puntosMin Puntos mínimos asociados a la carta en un juego.
 * @property puntosMax Puntos máximos asociados a la carta en un juego.
 * @property idDrawable Identificador del recurso gráfico (drawable) asociado a la carta.
 * @property usada Indica si la carta ha sido utilizada en el juego.
 */
data class Carta(
    var nombre: Naipes,
    var palo: Palos,
    var puntosMin: Int = 0,
    var puntosMax: Int = 0,
    var idDrawable: Int = 0,
    var usada: Boolean = false
)