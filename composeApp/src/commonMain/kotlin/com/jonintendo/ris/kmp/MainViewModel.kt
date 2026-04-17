package com.jonintendo.ris.kmp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonintendo.ris.kmp.mission.Mission
import com.jonintendo.ris.kmp.rotas.Rota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.*
import systems.untangle.karta.data.Coordinates


class MainViewModel : ViewModel() {
    val rota = Rota()
    val mission = Mission()

    private val frame_ = MutableStateFlow("sdf")
    val frame: StateFlow<String> = frame_.asStateFlow()
    fun changeFrame() {
        frame_.value = "nossaaaa!!!!"
    }


    val acompanhamentos_ = mutableStateListOf<Coordinates>()
    //val acompanhamentos: StateFlow<List<Coordinates>> = acompanhamentos_.asStateFlow()

    private val simulatedCoor_ = MutableStateFlow(Coordinates(0.0, 0.0))
    val simulatedCoord: StateFlow<Coordinates> = simulatedCoor_.asStateFlow()

    private var myJob: Job? = null
    val customScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    var serverSocket: BoundDatagramSocket? = null
    fun startReceivePort() {
        myJob = customScope.launch {
            val selectorManager = SelectorManager(Dispatchers.IO)
            serverSocket = aSocket(selectorManager).udp().bind("172.28.14.246", 50100)

            println("Server is listening at ${serverSocket!!.localAddress}")

            for (datagram in serverSocket!!.incoming) {
                val message = datagram.packet.readText()

                // Extracting IP and Port from the datagram address
                val address = datagram.address as? io.ktor.network.sockets.InetSocketAddress
                val senderIp = address?.hostname
                val senderPort = address?.port

                println("Received '$message' from $senderIp:$senderPort")
                val coord = nmeaParser(message)
                if (coord != null) {
                    simulatedCoor_.value = coord

                    acompanhamentos_.clear()
                    acompanhamentos_.add(coord)
                }
            }
        }
        // myJob?.start()
    }

    fun stopReceivePort() {
        serverSocket?.close()
        myJob?.cancel()
    }

    fun sendToPort(message: String) = customScope.launch {
        val selectorManager = SelectorManager(Dispatchers.IO)
        val socket = aSocket(selectorManager).udp().connect(InetSocketAddress("172.28.14.246", 50100))

        socket.outgoing.send(
            Datagram(
                ByteReadPacket(message.toByteArray()),
                InetSocketAddress("172.28.14.246", 50100)
            )
        )

        println("Message sent.")
    }


    fun sendNMEA() {
        sendToPort("\$GPRMC,140927.000,V,2251.633,S,04311.040,W,5.8,336.3,170426,000.0,W,*48")
    }



    private val aviso_ = MutableStateFlow(false)
    val aviso: StateFlow<Boolean> = aviso_.asStateFlow()
    fun showAviso() {
        aviso_.value = !aviso_.value
    }

}