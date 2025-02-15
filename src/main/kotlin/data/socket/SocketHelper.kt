package com.siradze.data.socket

import co.touchlab.stately.concurrency.AtomicInt

object SocketHelper {
    private var lastUsedPort: AtomicInt = AtomicInt(50001)

    @Synchronized fun getAvailablePort(): Int {
        return lastUsedPort.addAndGet(1)
    }
}