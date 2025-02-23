package com.siradze.doman

import io.ktor.network.sockets.*

data class Player(
    val id: Int,
    val name: String,
    val socket: SocketAddress,

) {
    var data : ByteArray = byteArrayOf()
}