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

/**
 * ViewModel para la lógica principal del juego.
 * Controla las acciones de los jugadores, puntajes, cartas, y determina al ganador.
 */
class JuegoPrincipalVM : ViewModel() {

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
    private val permitirObtenerCartaJugador1: LiveData<Boolean> = _permitirObtenerCartaJugador1
    private val permitirObtenerCartaJugador2: LiveData<Boolean> = _permitirObtenerCartaJugador2
    val listaCartasBocaArribaJugador1: LiveData<List<Int>> = _listaCartasBocaArribaJugador1
    val listaCartasBocaArribaJugador2: LiveData<List<Int>> = _listaCartasBocaArribaJugador2
    val textoPlantadoJug1: LiveData<String> = _textoPlantadoJug1
    val textoPlantadoJug2: LiveData<String> = _textoPlantadoJug2
    val contadorjug1: LiveData<Int> = _contadorJug1
    val contadorjug2: LiveData<Int> = _contadorJug2


    /**
     * Método para que el Jugador 1 obtenga una nueva carta del mazo.
     * Si se le permite, se obtiene una carta, se actualiza la información del juego y se calcula el puntaje.
     *
     * @param context El contexto de la aplicación.
     */
    fun obtenerCartaJugador1(context: Context) {
        // Verificar si al Jugador 1 se le permite obtener una carta
        if (permitirObtenerCartaJugador1.value == true) {
            // Barajar el mazo si está vacío
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                _barajasNuevasJug1.value = "abajo"
            }

            // Obtener una nueva carta del mazo
            val cartaNueva = Baraja.dameCarta()

            // Obtener el identificador del recurso drawable de la carta
            val cartaDrawable = obtenerNombreRecurso(cartaNueva)
            val cartaDrawableId = actualizarCartaBocaArriba(cartaDrawable, context)

            // Actualizar la carta boca arriba del Jugador 1
            _cartaBocaArribaJugador1.value = cartaDrawableId

            // Actualizar la lista de cartas boca arriba del Jugador 1
            if (cartaDrawableId !in _listaCartasBocaArribaJugador1.value.orEmpty()) {
                _listaCartasBocaArribaJugador1.value =
                    _listaCartasBocaArribaJugador1.value.orEmpty() + cartaDrawableId
            }

            // Actualizar la lista de cartas del Jugador 1 y recalcular su puntaje
            _cartasJugador1.value = (_cartasJugador1.value.orEmpty() + cartaNueva)
            _puntajeJugador1.value = Baraja.calcularPuntaje(_cartasJugador1.value.orEmpty())
        }
    }


    /**
     * Método para que el Jugador 2 obtenga una nueva carta del mazo.
     * Si se le permite, se obtiene una carta, se actualiza la información del juego y se calcula el puntaje.
     *
     * @param context El contexto de la aplicación.
     */
    fun obtenerCartaJugador2(context: Context) {
        // Verificar si al Jugador 2 se le permite obtener una carta
        if (permitirObtenerCartaJugador2.value == true) {
            // Barajar el mazo si está vacío
            Baraja.barajar()
            if (Baraja.listaCartas.isEmpty()) {
                Baraja.crearBaraja()
                _barajasNuevasJug2.value = "abajo"
            }

            // Obtener una nueva carta del mazo
            val cartaNueva = Baraja.dameCarta()

            // Obtener el identificador del recurso drawable de la carta
            val cartaDrawable = obtenerNombreRecurso(cartaNueva)
            val cartaDrawableId = actualizarCartaBocaArriba(cartaDrawable, context)

            // Actualizar la carta boca arriba del Jugador 2
            _cartaBocaArribaJugador2.value = cartaDrawableId

            // Actualizar la lista de cartas boca arriba del Jugador 2
            if (cartaDrawableId !in _listaCartasBocaArribaJugador2.value.orEmpty()) {
                _listaCartasBocaArribaJugador2.value =
                    _listaCartasBocaArribaJugador2.value.orEmpty() + cartaDrawableId
            }

            // Actualizar la lista de cartas del Jugador 2 y recalcular su puntaje
            _cartasJugador2.value = (_cartasJugador2.value.orEmpty() + cartaNueva)
            _puntajeJugador2.value = Baraja.calcularPuntaje(_cartasJugador2.value.orEmpty())
        }
    }


    /**
     * Determina al ganador basándose en las reglas del juego y actualiza el estado del juego.
     * Se evalúa si alguno de los jugadores alcanza 21 puntos, se pasa o si hay empate.
     * Se navega a la pantalla de ganador mostrando el resultado.
     *
     * @param navController El controlador de navegación para cambiar de pantalla.
     * @param puntajeJugador1 Puntaje actual del Jugador 1.
     * @param puntajeJugador2 Puntaje actual del Jugador 2.
     * @param jugador1 Nombre del Jugador 1.
     * @param jugador2 Nombre del Jugador 2.
     */
    fun determinarGanador(
        navController: NavHostController,
        puntajeJugador1: Int,
        puntajeJugador2: Int,
        jugador1: String,
        jugador2: String
    ) {
        // Si algún jugador alcanza 21 puntos, se bloquea su turno y se muestra "Plantado"
        if (puntajeJugador1 >= 21) {
            _permitirObtenerCartaJugador1.value = false
            _textoPlantadoJug1.value = "Plantado"
        }

        if (puntajeJugador2 >= 21) {
            _permitirObtenerCartaJugador2.value = false
            _textoPlantadoJug2.value = "Plantado"
        }

        // Si ambos jugadores están "Plantados", se determina al ganador
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
            // Navegar a la pantalla de ganador mostrando el resultado
            navController.navigate("${Routes.GanadorScreen.route}/$ganador")
        }
    }

    /**
     * El Jugador 1 decide plantarse, bloqueando su turno para obtener más cartas.
     */
    fun plantarseJug1() {
        _permitirObtenerCartaJugador1.value = false
        _textoPlantadoJug1.value = "Plantado"
    }

    /**
     * El Jugador 2 decide plantarse, bloqueando su turno para obtener más cartas.
     */
    fun plantarseJug2() {
        _permitirObtenerCartaJugador2.value = false
        _textoPlantadoJug2.value = "Plantado"
    }

    /**
     * Reinicia el juego a su estado inicial, incluyendo las cartas y puntajes de los jugadores.
     */
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