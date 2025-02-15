package com.siradze.doman

import kotlinx.serialization.Serializable

@Serializable
data class Server(
    val id: Int,
    val name: String,
    val port: Int,
    val players: Int,
)