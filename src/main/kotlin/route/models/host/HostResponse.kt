package com.siradze.route.models.host

import com.siradze.doman.Server
import kotlinx.serialization.Serializable

@Serializable
data class HostResponse(
    val server: Server,
)