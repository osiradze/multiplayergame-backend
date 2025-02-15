package com.siradze.data.socket

import com.siradze.EchoApp.selectorManager
import com.siradze.doman.Server
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.network.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.coroutineContext

class ServerSocket(
    private val port: Int,
    private val onDestroy: () -> Unit
) {
    private val players = Collections.synchronizedMap(HashMap<Int, Player>())
    private val addresses = Collections.synchronizedMap(HashMap<Int, SocketAddress>())

    private var server: BoundDatagramSocket? = null


    fun start() = GlobalScope.launch {
        server = aSocket(selectorManager).udp().bind(
            localAddress = InetSocketAddress("127.0.0.1", port)
        )
        println("UDP server is listening on port $port")

        launch {
            startListening()
        }

        launch {
            startEmitting()
        }
    }

    private suspend fun startListening() {
        val server = server ?: return

        while (coroutineContext.isActive) {
            delay(5)
            val datagram = server.receive()
            val clientAddress = datagram.address
            val player = getOrAddPlayer(clientAddress)
        }
    }

    private suspend fun startEmitting() {
        val server = server ?: return

        while (coroutineContext.isActive) {
            delay(5)
            for(player in players.values) {
                val message = "Game"
                val packet = buildPacket { writeText(message) }
                server.send(Datagram(packet, player.socket))
            }
        }
    }

    private fun getOrAddPlayer(clientAddress: SocketAddress): Player? {
        val port = clientAddress.toJavaAddress().port
        if(addresses[port] == null) {
            addresses[port] = clientAddress
            players[port] = Player(
                id = players.size + 1,
                name = "Player ${players.size + 1}",
                socket = clientAddress
            )
            println(players.size)
        }
        return players[port]
    }
}


object SocketTest {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            // Create a UDP socket
            val client = aSocket(ActorSelectorManager(Dispatchers.IO)).udp().connect(InetSocketAddress("127.0.0.1", 50002))
            // Message to send
            val message = "Hello, UDP Server!"
            val packet = buildPacket { writeText(message) }

            // Send the message to the server
            client.send(Datagram(packet, client.remoteAddress))
            println("Sent to server: $message")

            // Wait for a response from the server
            while (true){
                val responseDatagram = client.receive()
                val response = responseDatagram.packet.readText()
                println("Received from server: $response")
            }


            // Close the client socket
           // client.close()
            //println("UDP client closed")
        }
    }
}