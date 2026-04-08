package com.jonintendo.ris.kmp.rotas

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.connection.http.client.ClientHTTP
import com.connection.http.Header
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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


    suspend fun getRotas() {
        val deferredResult: Deferred<String> = coroutineScope {
            async {
                ClientHTTP.get(
                    "https://acquavia.acquaway.com/navegacao/v1/rotas/mapa", listOf(
                        Header(
                            "Authorization",
                            "Apikey aGlkcmEtYXBpa2V5LjEuWWhYU3F3VWdVQlRBR0hZWDhlTjNIUDdoTllPYTJrWHU1cHdmNkxqMHBFYVFVU3k5bUhj"
                        )
                    )
                )
            }
        }

        detectRotasWayPoints(deferredResult.await())
    }

    fun detectRotasWayPoints(rotasString: String) {
//        val dfJson = DataFrame.read()
//        println(dfJson)

        println(rotasString)
        try {
            val rotas = Json.Default.decodeFromString<ArrayList<RotasItem>>(rotasString)
            // println(product[0].pernadas[0].canal.coordenadas)

            rotasWayPoints.clear()
            rotas.forEach { rota ->
                rota.pernadas.forEach { pernada ->
                    pernada.canal.coordenadas.mapTo(rotasWayPoints) { it.toCoordinates() }
                }
            }

            // product[0].pernadas[0].canal.coordenadas.mapTo(rotasCoords.value) { it.toModel() }
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun clearWayPoints() {
        rotasWayPoints.clear()
    }

    fun waypoints(): List<Coordinates> {
        return rotasWayPoints.toList()
    }
}