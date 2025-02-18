package com.siradze.data

import com.siradze.doman.Server

interface ServerRepository {
    fun host(name: String): Server
    fun servers(): List<Server>
}