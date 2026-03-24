package com.jonintendo.ris.kmp.rotas

import androidx.compose.runtime.mutableStateListOf
import kotlinx.serialization.json.Json
import systems.untangle.karta.data.Coordinates

class Rota {

//    var rotasCoords = MutableStateFlow(
//        mutableStateListOf(
//            Coordinates(-20.265507, -40.296735),
//        )
//    )
//
//    fun detectRotas(rotas: String) {
////        val dfJson = DataFrame.read()
////        println(dfJson)
//        val jsonString = rotas
//        println(jsonString)
//        val rotas = Json.Default.decodeFromString<ArrayList<RotasItem>>(jsonString)
//        // println(product[0].pernadas[0].canal.coordenadas)
//
//        rotasCoords.value.clear()
//        rotas.forEach { rota ->
//            rota.pernadas.forEach { pernada ->
//                pernada.canal.coordenadas.mapTo(rotasCoords.value) { it.toCoordinates() }
//            }
//        }
//
//        // product[0].pernadas[0].canal.coordenadas.mapTo(rotasCoords.value) { it.toModel() }
//
//    }
//
//    fun clearRotas(){
//        rotasCoords.value.clear()
//    }

    private val rotasWayPoints = mutableStateListOf(
        Coordinates(-20.265507, -40.296735),
    )

    fun detectRotasWayPoints(rotas: String) {
//        val dfJson = DataFrame.read()
//        println(dfJson)
        val jsonString = rotas
        println(jsonString)
        val rotas = Json.Default.decodeFromString<ArrayList<RotasItem>>(jsonString)
        // println(product[0].pernadas[0].canal.coordenadas)

        rotasWayPoints.clear()
        rotas.forEach { rota ->
            rota.pernadas.forEach { pernada ->
                pernada.canal.coordenadas.mapTo(rotasWayPoints) { it.toCoordinates() }
            }
        }

        // product[0].pernadas[0].canal.coordenadas.mapTo(rotasCoords.value) { it.toModel() }

    }

    fun clearWayPoints(){
        rotasWayPoints.clear()
    }

    fun waypoints():List<Coordinates>{
        return rotasWayPoints.toList()
    }
}