package com.siradze.route.models.host

import kotlinx.serialization.Serializable

@Serializable
data class HostRequest (
    val name: String,
)