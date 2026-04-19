package com.jonintendo.ris.kmp.avisos

import com.jonintendo.ris.kmp.rotas.RotasItem
import kotlinx.serialization.Serializable
import systems.untangle.karta.data.Coordinates

@Serializable
data class AvRadio(
    val atualizado: String,
    val avisos: List<Aviso>
) {@Serializable
    data class Aviso(
        val costa: String,
        val geometry: String,
        val numero: String,
        val textoEN: String,
        val textoPT: String,
        val tipo: String
    )
}



//fun AvRadio.Aviso.geometry.toCoordinates(): Coordinates {
//    return Coordinates(
//        latitude = latitude,
//        longitude = longitude
//    )
//}

