package com.siradze.data

import com.siradze.data.socket.SocketHelper
import org.junit.Test
import kotlin.test.assertNotEquals

class SocketHelperTest {
    @Test
    fun getPort() {
        val port = SocketHelper.getAvailablePort()
        val port2 = SocketHelper.getAvailablePort()
        assertNotEquals(
            port,
            port2,
            "Ports should be different"
        )
    }
}