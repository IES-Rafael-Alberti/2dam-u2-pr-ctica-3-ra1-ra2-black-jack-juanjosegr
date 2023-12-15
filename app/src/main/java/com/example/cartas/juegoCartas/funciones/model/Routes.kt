package com.example.cartas.juegoCartas.funciones.model

/**
 * Clase que representa las distintas rutas de navegación en la aplicación.
 *
 * @property route Ruta asociada a la clase.
 */
sealed class Routes(val route: String) {

    // Objeto que representa la pantalla de inicio del juego
    object PantallaInicio : Routes("MenuPrincipal")

    // Objeto que representa la pantalla de juego versus jugador
    object PantallaVsJugador : Routes("Juego2Jugador")

    // Objeto que representa la pantalla de juego versus la inteligencia artificial
    object PantallaVsIa : Routes("JuegoCartaAlta")

    // Objeto que representa la pantalla de resultado con el nombre del ganador
    object GanadorScreen : Routes("Ganador/{ganador}")

    // Objeto que representa la pantalla de ingreso de nombres
    object Nombres: Routes("Nombres")

}