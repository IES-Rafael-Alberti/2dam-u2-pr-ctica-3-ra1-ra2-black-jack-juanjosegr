package com.example.cartas.funciones

enum class Palos {
    CORAZONES, DIAMANTES, TREBOLES, PICAS
}

enum class Naipes {
    AS, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, JOTA, REINA, REY

}

class Baraja {

    companion object {
        var listaCartas = arrayListOf<Carta>()
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

        fun barajar() {
            listaCartas.shuffle()
        }

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
        fun reiniciarCartas() {
            listaCartas.forEach { it.usada = false }
        }

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