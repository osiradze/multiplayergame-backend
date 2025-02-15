package com.siradze.route.models.host

import kotlinx.serialization.Serializable

@Serializable
data class HostResponse(
    val serverId: Int,
)