package com.example.cartas.juegoCartas.funciones.data

// Enumera los palos posibles de las cartas
enum class Palos {
    CORAZONES, DIAMANTES, TREBOLES, PICAS
}

// Enumera los valores de las cartas
enum class Naipes {
    AS, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, JOTA, REINA, REY

}

/**
 * Clase que representa una baraja de cartas.
 */
class Baraja {

    companion object {
        // Lista mutable que contendrá las cartas
        var listaCartas = arrayListOf<Carta>()

        /**
         * Función para crear una baraja estándar de cartas.
         * Asigna un identificador gráfico a cada carta.
         */
        fun crearBaraja() {
            var idDrawable = 1
            for (palo in Palos.values()) {
                for (numero in Naipes.values()) {
                    val cartaNueva =
                        Carta(numero, palo, numero.ordinal + 1, numero.ordinal + 1, idDrawable)
                    if (numero.name == "AS") cartaNueva.puntosMax = 11
                    listaCartas.add(cartaNueva)
                    idDrawable++
                }
            }
        }

        /**
         * Función para mezclar las cartas en la baraja.
         */
        fun barajar() {
            listaCartas.shuffle()
        }

        /**
         * Función para obtener una carta de la baraja.
         * Si la baraja está vacía, crea una nueva baraja y la reinicia.
         */
        fun dameCarta(): Carta {
            if (listaCartas.isEmpty()) {
                crearBaraja()
                reiniciarCartas()
                return listaCartas.removeLast()
            }
            val carta = listaCartas.removeLast()
            return if (!carta.usada) {
                carta.usada = true
                carta
            } else {
                dameCarta()
            }
        }

        /**
         * Función para calcular el puntaje total de un conjunto de cartas.
         */
        fun calcularPuntaje(cartas: List<Carta>): Int {
            val cantidadCartas = cartas.size
            val tieneMasDeDosCartas = cantidadCartas > 2

            return cartas.sumBy {
                when(it.nombre){
                    Naipes.AS ->
                        if (tieneMasDeDosCartas && it.puntosMax == 11) 1 else 11
                    Naipes.DIEZ, Naipes.JOTA, Naipes.REINA, Naipes.REY -> 10
                    else -> it.puntosMin
                }
            }
        }

        /**
         * Función para reiniciar el estado de las cartas en la baraja.
         */
        fun reiniciarCartas() {
            listaCartas.forEach { it.usada = false }
        }

        /**
         * Función para obtener el nombre del recurso gráfico asociado a una carta.
         */
        fun obtenerNombreRecurso(carta: Carta): String {
            val paloString = when (carta.palo) {
                Palos.CORAZONES -> "c"
                Palos.DIAMANTES -> "d"
                Palos.TREBOLES -> "t"
                Palos.PICAS -> "n" // Nota: "n" es para picas (corazón negro)
            }
            val numeroString = when (carta.nombre) {
                Naipes.AS -> "1"
                Naipes.DOS -> "2"
                Naipes.TRES -> "3"
                Naipes.CUATRO -> "4"
                Naipes.CINCO -> "5"
                Naipes.SEIS -> "6"
                Naipes.SIETE -> "7"
                Naipes.OCHO -> "8"
                Naipes.NUEVE -> "9"
                Naipes.DIEZ -> "10"
                Naipes.JOTA -> "11"
                Naipes.REINA -> "12"
                Naipes.REY -> "13"
            }

            return "$paloString$numeroString"
        }

    }
}