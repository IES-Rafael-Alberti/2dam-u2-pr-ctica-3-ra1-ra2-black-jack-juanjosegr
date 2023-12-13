package com.example.cartas.juegoCartas.funciones.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartas.juegoCartas.funciones.data.Baraja
import com.example.cartas.juegoCartas.funciones.data.Carta

class juegoPrincipalVM : ViewModel(){

    private val _cartasJugador1 = MutableLiveData<List<Carta>>()
    val cartasJugador1: LiveData<List<Carta>> = _cartasJugador1

    private val _cartasJugador2 = MutableLiveData<List<Carta>>()
    val cartasJugador2: LiveData<List<Carta>> = _cartasJugador2

    private var _puntajeJugador1 = MutableLiveData(0)
    var puntajeJugador1: LiveData<Int> = _puntajeJugador1

    private val _puntajeJugador2 = MutableLiveData(0)
    var puntajeJugador2: LiveData<Int> = _puntajeJugador2

    private val _permitirObtenerCartaJugador1 = MutableLiveData(true)
    val permitirObtenerCarta1: LiveData<Boolean> = _permitirObtenerCartaJugador1

    private val _permitirObtenerCartaJugador2 = MutableLiveData(true)
    val permitirObtenerCarta2: LiveData<Boolean> = _permitirObtenerCartaJugador2

    private var _barajasNuevasJug1 = MutableLiveData("abajo")
    val barajasNuevasJug1: LiveData<String> = _barajasNuevasJug1

    private var _barajasNuevasJug2 = MutableLiveData("abajo")
    val barajasNuevasJug2: LiveData<String> = _barajasNuevasJug2

    private val _cartaBocaArribaJugador1 =  MutableLiveData<Int>(null)
    val cartaBocaArribaJugador1: LiveData<Int?> = _cartaBocaArribaJugador1

    private val _cartaBocaArribaJugador2 =  MutableLiveData<Int>(null)
    val cartaBocaArribaJugador2: LiveData<Int?> = _cartaBocaArribaJugador2

    private val _listaCartasBocaArribaJugador1 =  MutableLiveData<List<Int>>()
    val listaCartasBocaArribaJugador1: LiveData<List<Int>> = _listaCartasBocaArribaJugador1


    private val _listaCartasBocaArribaJugador2  =  MutableLiveData<List<Int>>()
    val listaCartasBocaArribaJugador2: LiveData<List<Int>> = _listaCartasBocaArribaJugador2



    fun obtenerCartaJugador1() {
        if (_permitirObtenerCartaJugador1.value == true) {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                _barajasNuevasJug1.value = "abajo"
            }
            val cartaNueva = Baraja.dameCarta()
            cartaNueva.let {
                _barajasNuevasJug1.value = Baraja.obtenerNombreRecurso(it)
                val cartaActualizada = actualizarCartaBocaArriba(_barajasNuevasJug1.value!!, context)
                _cartaBocaArribaJugador1.value = cartaActualizada
                if (cartaActualizada !in _listaCartasBocaArribaJugador1) {
                    _listaCartasBocaArribaJugador1 += cartaActualizada
                }
            }
            _cartasJugador1.value += cartaNueva
            _puntajeJugador1.value = Baraja.calcularPuntaje(_cartasJugador1)
        }
    }

    @SuppressLint("DiscouragedApi")
    fun actualizarCartaBocaArriba(carta: String, context: Context): Int {
        return context.resources.getIdentifier(carta, "drawable", context.packageName)
    }


    /*fun obtenerCartaJugador2() {
        if (permitirObtenerCarta2.value == true) {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                barajasNuevasJug2 = "abajo"
            }
            val cartaNueva = Baraja.dameCarta()
            cartaNueva.let {
                barajasNuevasJug2 = obtenerNombreRecurso(it)
                val cartaActualizada = actualizarCartaBocaArriba(barajasNuevasJug2, context)
                cartaBocaArribaJugador2 = cartaActualizada
                if (cartaActualizada !in listaCartasBocaArribaJugador2) {
                    listaCartasBocaArribaJugador2 += cartaActualizada
                }
            }
            cartasJugador2 += cartaNueva
            puntajeJugador2 = Baraja.calcularPuntaje(cartasJugador2)
        }
    }

    fun plantarseJug1(){
        permitirObtenerCarta1 = false
        textoPlantadoJug1 = "Plantado"
    }

    fun plantarseJug2(){
        permitirObtenerCarta2 = false
        textoPlantadoJug2 = "Plantado"
    }
    fun reiniciarJuego() {
        Baraja.crearBaraja()
        Baraja.barajar()
        Baraja.reiniciarCartas()
        barajasNuevasJug1 = "abajo"
        barajasNuevasJug2 = "abajo"
        cartaBocaArribaJugador1 = null
        cartaBocaArribaJugador2 = null
        listaCartasBocaArribaJugador1 = emptyList()
        listaCartasBocaArribaJugador2 = emptyList()
        permitirObtenerCarta1 = true
        permitirObtenerCarta2 = true
        textoPlantadoJug1 = "Dame carta"
        textoPlantadoJug2 = "Dame carta"
        puntajeJugador1 = 0
        puntajeJugador2 = 0
        cartasJugador1 = emptyList()
        cartasJugador2 = emptyList()
    }*/







}

