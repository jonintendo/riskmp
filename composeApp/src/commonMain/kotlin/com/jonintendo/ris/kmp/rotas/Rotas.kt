package com.jonintendo.ris.kmp.rotas

import kotlinx.serialization.Serializable
import systems.untangle.karta.data.Coordinates

@Serializable
data class RotasItem(
    val dataHoraInicio: String?,
    val detectarNavegacaoBidirecional: Boolean,
    val id: Int,
    val latitudeMaxima: Double?,
    val latitudeMinima: Double?,
    val longitudeMaxima: Double?,
    val longitudeMinima: Double?,
    val nome: String,
    val pernadas: List<Pernada>,
    val sar: String?
) {
    @Serializable
    data class Pernada(
        val canal: Canal,
       // val id: Int,
        val larguraBB: Double,
        val larguraBE: Double,
        val numeroPernada: Int,
        val tipo: String,
        val velocPlanejada: Double,
        val waypointFinal: WaypointFinal,
        val waypointInicial: WaypointInicial
    ) {
        @Serializable
        data class Canal(
            val coordenadas: List<Coordenada>
        ) {
            @Serializable
            data class Coordenada(
                val latitude: Double,
                val longitude: Double
            )
        }

        @Serializable
        data class WaypointFinal(
           // val id: Int,
            val latitude: Double,
            val longitude: Double,
            val nome: String?
        )

        @Serializable
        data class WaypointInicial(
            //val id: Int,
            val latitude: Double,
            val longitude: Double,
            val nome: String?
        )
    }
}


fun RotasItem.Pernada.Canal.Coordenada.toCoordinates(): Coordinates {
    return Coordinates(
        latitude = latitude,
        longitude = longitude
    )
}