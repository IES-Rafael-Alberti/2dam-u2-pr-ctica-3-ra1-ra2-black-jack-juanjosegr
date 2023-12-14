package com.example.cartas.juegoCartas.funciones.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel para gestionar los nombres de los jugadores.
 */
class NombresViewModel : ViewModel() {

    private val _jugador1Nombre = MutableLiveData<String>()
    private val _jugador2Nombre = MutableLiveData<String>()

    val jugador1Nombre: LiveData<String> = _jugador1Nombre
    val jugador2Nombre: LiveData<String> = _jugador2Nombre

    /**
     * Guarda los nombres de los jugadores.
     *
     * @param jugador1 Nombre del jugador 1.
     * @param jugador2 Nombre del jugador 2.
     */
    fun guardarNombres(jugador1: String, jugador2: String) {
        _jugador1Nombre.value = jugador1
        _jugador2Nombre.value = jugador2
    }

    /**
     * Guarda el nombre del jugador 1.
     *
     * @param jugador1 Nombre del jugador 1.
     */
    fun guardarNombreJugador1(jugador1: String) {
        _jugador1Nombre.value = jugador1
    }

    /**
     * Guarda el nombre del jugador 2.
     *
     * @param jugador2 Nombre del jugador 2.
     */
    fun guardarNombreJugador2(jugador2: String) {
        _jugador2Nombre.value = jugador2
    }

    /**
     * Obtiene el nombre del jugador 1.
     *
     * @return Nombre del jugador 1 o una cadena vacía si no está definido.
     */
    fun obtenerNombreJugador1(): String {
        return _jugador1Nombre.value ?: ""
    }

    /**
     * Obtiene el nombre del jugador 2.
     *
     * @return Nombre del jugador 2 o una cadena vacía si no está definido.
     */
    fun obtenerNombreJugador2(): String {
        return _jugador2Nombre.value ?: ""
    }
}