package com.siradze.route

import com.siradze.data.ServerRepository
import com.siradze.doman.Server
import com.siradze.route.models.host.HostRequest
import com.siradze.route.models.host.HostResponse

class RouteController(
    private val serverRepository: ServerRepository
) {
    fun host(request: HostRequest): HostResponse {
        val id = serverRepository.host(request.name)
        return HostResponse(serverId = id)
    }

    fun servers(): List<Server> {
        return serverRepository.servers()
    }
}