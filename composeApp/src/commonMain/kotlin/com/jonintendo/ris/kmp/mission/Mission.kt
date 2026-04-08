package com.jonintendo.ris.kmp.mission

import androidx.compose.runtime.mutableStateListOf
import com.connection.http.client.ClientHTTP
import systems.untangle.karta.data.Coordinates

class Mission {


    private val missionWaypoints = mutableStateListOf(
        Coordinates(-20.265507, -40.296735),
        Coordinates(-20.272795, -40.284709),
        Coordinates(-20.271891, -40.283167),
        Coordinates(-20.273302, -40.280667),
        Coordinates(-20.269135, -40.274689),
        Coordinates(-20.244144, -40.278206),
        Coordinates(-20.242743, -40.280783)
    )


    fun moveWayPoint(index: Int) {
        missionWaypoints[index] = Coordinates(
            missionWaypoints[index].latitude + 0.0001,
            missionWaypoints[index].longitude + 0.0001
        )
    }

    fun clearWaypoints() {
        missionWaypoints.clear()
    }

    fun addWaypoint(coordinates: Coordinates) {
        missionWaypoints.add(coordinates)
    }

    fun waypoints(): List<Coordinates> {
        return missionWaypoints.toList()
    }

    suspend fun sendWayPoints() {
        try {
            ClientHTTP.post(
                missionWaypoints.joinToString(
                    separator = ",",
                    prefix = "{",
                    postfix = "}",
                    transform = { "Item ${it.latitude}" }),
                "http://172.28.14.246:7733/command",
                listOf()
            )
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}