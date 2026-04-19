package com.jonintendo.ris.kmp.avisos

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jonintendo.ris.kmp.rotas.RotasItem
import com.jonintendo.ris.kmp.rotas.toCoordinates
import kotlinx.serialization.json.Json
import systems.untangle.karta.data.Coordinates

class Aviso {
    private val avisosMarkerPoints = mutableStateListOf(
        Coordinates(-20.265507, -40.296735),
    )


    private val avisosPolygons = SnapshotStateList<List<Coordinates>>()


    fun latlongToCoord(latlong: String): Coordinates {
        val regexCoord = """([+-]\d+.\d+)""".toRegex()
        val matchResultCoord = regexCoord.findAll(latlong)
        val lat = matchResultCoord.elementAt(0).value.toDouble()
        val long = matchResultCoord.elementAt(1).value.toDouble()
        return Coordinates(lat, long)
    }

    fun findMarker(geometry: String) {
        val regex = """LatLng\([+-]+[\d\.,]+[+-]+[\d\.,]+\)""".toRegex()
        val matchResult = regex.find(geometry)

        if (matchResult != null) {
            println(matchResult.value)
            avisosMarkerPoints.add(latlongToCoord(matchResult.value))
        }
    }

    fun findMarkers(geometry: String) {
        val regex = """LatLng\([+-]+[\d\.,]+[+-]+[\d\.,]+\)""".toRegex()
        val matchResult = regex.findAll(geometry)

        if (matchResult.count() > 0) {
            matchResult.forEach {
                println(it.value)
                avisosMarkerPoints.add(latlongToCoord(it.value))
            }
        }
    }

    fun findPolygons(geometry: String) {
        val regex = """LatLng\([+-]+[\d\.,]+[+-]+[\d\.,]+\)""".toRegex()
        val matchResult = regex.findAll(geometry)

        if (matchResult.count() > 0) {
            val avisosPolygonPoints = mutableStateListOf(
                Coordinates(-20.265507, -40.296735),
            )
            avisosPolygonPoints.clear()

            matchResult.forEach {
                println(it.value)
                avisosPolygonPoints.add(latlongToCoord(it.value))
            }

            avisosPolygons.add(avisosPolygonPoints)
        }
    }


    fun geometryToJson(geometry: String) {
        val regexMarkers = """\|new""".toRegex()
        val regexPolygons = """Polygon""".toRegex()

        println(geometry)
        if (regexMarkers.containsMatchIn(geometry)) {
            findMarkers(geometry)
        } else {
            if (regexPolygons.containsMatchIn(geometry)) {
                findPolygons(geometry)
            } else {
                findMarker(geometry)
            }
        }

    }

    fun detectAvisos(avRadioString: String) {
        // println(avRadioString)
        try {
            val avRadio = Json.Default.decodeFromString<AvRadio>(avRadioString)

            avisosMarkerPoints.clear()
            avisosPolygons.clear()

            avRadio.avisos.forEach { aviso ->
//                rota.pernadas.forEach { pernada ->
//                    pernada.canal.coordenadas.mapTo(rotasWayPoints) { it.toCoordinates() }
//                }
                geometryToJson(aviso.geometry)
            }


        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun avMarkerPoints(): List<Coordinates> {
        return avisosMarkerPoints.toList()
    }

    fun avPolygonsPoints(): List<List<Coordinates>> {
        return avisosPolygons.toList()
    }
}