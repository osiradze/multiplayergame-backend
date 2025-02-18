package com.siradze.data

import com.siradze.data.socket.ServerSocket
import com.siradze.data.socket.SocketHelper
import com.siradze.doman.Server
import java.util.*
import kotlin.collections.HashMap

class ServerRepositoryImpl: ServerRepository {

    private val servers = Collections.synchronizedMap(HashMap<Int, Server>())

    override fun host(name: String): Server {
        val server = Server(
            id = servers.size + 1,
            name = name,
            port = SocketHelper.getAvailablePort(),
            players = 0
        )
        val serverSocket = ServerSocket(
            server.port,
            onDestroy = {
                servers.remove(server.id)
            }
        )
        serverSocket.start()
        servers[server.id] = server
        return server
    }

    override fun servers(): List<Server> {
        return servers.values.toList()
    }
}