package com.siradze

import com.siradze.data.di.dataModule
import com.siradze.route.di.routeModule
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.event.Level

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(dataModule)
        modules(routeModule)
    }
    install(CallLogging) {
        level = Level.INFO
    }
}
