package com.example.cartas.juegoCartas.funciones.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cartas.juegoCartas.funciones.data.Baraja
import com.example.cartas.juegoCartas.funciones.data.Baraja.Companion.obtenerNombreRecurso
import com.example.cartas.juegoCartas.funciones.data.Carta
import com.example.cartas.juegoCartas.funciones.model.Routes

class juegoPrincipalVM : ViewModel() {

    private val _cartasJugador1 = MutableLiveData<List<Carta>>(emptyList())
    private val _cartasJugador2 = MutableLiveData<List<Carta>>(emptyList())
    private var _puntajeJugador1 = MutableLiveData(0)
    private val _puntajeJugador2 = MutableLiveData(0)
    private val _permitirObtenerCartaJugador1 = MutableLiveData(true)
    private val _permitirObtenerCartaJugador2 = MutableLiveData(true)
    private var _barajasNuevasJug1 = MutableLiveData("abajo")
    private var _barajasNuevasJug2 = MutableLiveData("abajo")
    private val _cartaBocaArribaJugador1 = MutableLiveData<Int>(null)
    private val _cartaBocaArribaJugador2 = MutableLiveData<Int>(null)
    private val _listaCartasBocaArribaJugador1 = MutableLiveData<List<Int>>()
    private val _listaCartasBocaArribaJugador2 = MutableLiveData<List<Int>>()
    private var _textoPlantadoJug1 = MutableLiveData("Dame carta")
    private var _textoPlantadoJug2 = MutableLiveData("Dame carta")
    private val _contadorJug1 = MutableLiveData<Int>()
    private val _contadorJug2 = MutableLiveData<Int>()


    var puntajeJugador1: LiveData<Int> = _puntajeJugador1
    var puntajeJugador2: LiveData<Int> = _puntajeJugador2
    val permitirObtenerCartaJugador1: LiveData<Boolean> = _permitirObtenerCartaJugador1
    val permitirObtenerCartaJugador2: LiveData<Boolean> = _permitirObtenerCartaJugador2
    val listaCartasBocaArribaJugador1: LiveData<List<Int>> = _listaCartasBocaArribaJugador1
    val listaCartasBocaArribaJugador2: LiveData<List<Int>> = _listaCartasBocaArribaJugador2
    val textoPlantadoJug1: LiveData<String> = _textoPlantadoJug1
    val textoPlantadoJug2: LiveData<String> = _textoPlantadoJug2
    val contadorjug1: LiveData<Int> = _contadorJug1
    val contadorjug2: LiveData<Int> = _contadorJug2


    fun obtenerCartaJugador1(context: Context) {
        if (permitirObtenerCartaJugador1.value == true) {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                _barajasNuevasJug1.value = "abajo"
            }

            val cartaNueva = Baraja.dameCarta()
            val cartaDrawable = obtenerNombreRecurso(cartaNueva)
            val cartaDrawableId = actualizarCartaBocaArriba(cartaDrawable, context)
            _cartaBocaArribaJugador1.value = cartaDrawableId

            if (cartaDrawableId !in _listaCartasBocaArribaJugador1.value.orEmpty()) {
                _listaCartasBocaArribaJugador1.value =
                    _listaCartasBocaArribaJugador1.value.orEmpty() + cartaDrawableId
            }
            _cartasJugador1.value = (_cartasJugador1.value.orEmpty() + cartaNueva)
            _puntajeJugador1.value = Baraja.calcularPuntaje(_cartasJugador1.value.orEmpty())
        }
    }

    fun obtenerCartaJugador2(context: Context) {
        if (permitirObtenerCartaJugador2.value == true) {
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                _barajasNuevasJug2.value = "abajo"
            }

            val cartaNueva = Baraja.dameCarta()
            val cartaDrawable = obtenerNombreRecurso(cartaNueva)
            val cartaDrawableId = actualizarCartaBocaArriba(cartaDrawable, context)
            _cartaBocaArribaJugador2.value = cartaDrawableId
            if (cartaDrawableId !in _listaCartasBocaArribaJugador2.value.orEmpty()) {
                _listaCartasBocaArribaJugador2.value =
                    _listaCartasBocaArribaJugador2.value.orEmpty() + cartaDrawableId
            }
            _cartasJugador2.value = (_cartasJugador2.value.orEmpty() + cartaNueva)
            _puntajeJugador2.value = Baraja.calcularPuntaje(_cartasJugador2.value.orEmpty())
        }
    }

    fun determinarGanador(
        navController: NavHostController,
        puntajeJugador1: Int,
        puntajeJugador2: Int,
        jugador1: String,
        jugador2: String
    ) {
        if (puntajeJugador1 >= 21) {
            _permitirObtenerCartaJugador1.value = false
            _textoPlantadoJug1.value = "Plantado"
        }

        if (puntajeJugador2 >= 21) {
            _permitirObtenerCartaJugador2.value = false
            _textoPlantadoJug2.value = "Plantado"
        }
        if (textoPlantadoJug1.value == "Plantado" && textoPlantadoJug2.value == "Plantado") {
            val ganador = when {
                puntajeJugador1 > 21 && puntajeJugador2 > 21 -> "Empate por pasarse ambos jugadores"
                puntajeJugador1 > 21 -> jugador2
                puntajeJugador2 > 21 -> jugador1
                else -> {
                    when {
                        puntajeJugador1 > puntajeJugador2 -> jugador1
                        puntajeJugador1 == puntajeJugador2 -> "Empate por cartas"
                        else -> jugador2
                    }
                }
            }
            navController.navigate("${Routes.GanadorScreen.route}/$ganador")
        }
    }

    fun plantarseJug1() {
        _permitirObtenerCartaJugador1.value = false
        _textoPlantadoJug1.value = "Plantado"
    }

    fun plantarseJug2() {
        _permitirObtenerCartaJugador2.value = false
        _textoPlantadoJug2.value = "Plantado"
    }

    fun reiniciarJuego() {
        Baraja.crearBaraja()
        Baraja.barajar()
        Baraja.reiniciarCartas()
        _barajasNuevasJug1.value = "abajo"
        _barajasNuevasJug2.value = "abajo"
        _cartaBocaArribaJugador1.value = null
        _cartaBocaArribaJugador2.value = null
        _listaCartasBocaArribaJugador1.value = emptyList()
        _listaCartasBocaArribaJugador2.value = emptyList()
        _permitirObtenerCartaJugador1.value = true
        _permitirObtenerCartaJugador2.value = true
        _textoPlantadoJug1.value = "Dame carta"
        _textoPlantadoJug2.value = "Dame carta"
        _puntajeJugador1.value = 0
        _puntajeJugador2.value = 0
        _cartasJugador1.value = emptyList()
        _cartasJugador2.value = emptyList()
    }

}