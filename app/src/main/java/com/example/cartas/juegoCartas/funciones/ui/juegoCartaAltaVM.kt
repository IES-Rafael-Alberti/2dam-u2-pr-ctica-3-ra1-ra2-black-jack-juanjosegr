package com.example.cartas.juegoCartas.funciones.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartas.R
import com.example.cartas.juegoCartas.funciones.data.Baraja
import com.example.cartas.juegoCartas.funciones.data.Baraja.Companion.obtenerNombreRecurso
import com.example.cartas.juegoCartas.funciones.data.Carta

class juegoCartaAltaVM :ViewModel() {

    private val _cartaBocaAbajo = MutableLiveData("cartaabajoguapa")
    val cartaBocaAbajo: LiveData<String> = _cartaBocaAbajo

    private val _cartaBocaArriba = MutableLiveData(R.drawable.cartaabajoguapa)
    val cartaBocaArriba: LiveData<Int> = _cartaBocaArriba

    private val _contadorJug1 = MutableLiveData<Int>()
    val contadorjug1: LiveData<Int> = _contadorJug1

    fun obtenerNuevaCarta() {
        Baraja.barajar()
        if (Baraja.listaCartas.size == 0) {
            Baraja.crearBaraja()
            _cartaBocaAbajo.value = "cartaabajoguapa"
        }
        val cartaNueva = Baraja.dameCarta()
        _cartaBocaAbajo.value = obtenerNombreRecurso(cartaNueva)
    }

    fun barajarCartas() {
        Baraja.crearBaraja()
        Baraja.barajar()
        _cartaBocaAbajo.value = "cartaabajoguapa"
    }

}