package com.example.cartas.funciones

class Jugador(mano:MutableList<Carta>,fichas:Int) {

    var Mano = mano
    var Fichas = fichas

    constructor():this(mutableListOf<Carta>(),0)
}