package com.siradze.route.di

import com.siradze.route.RouteController
import org.koin.dsl.module

val routeModule = module {
    single { RouteController(get()) }
}