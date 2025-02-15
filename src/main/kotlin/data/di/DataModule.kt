package com.siradze.data.di

import com.siradze.data.ServerRepository
import com.siradze.data.ServerRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<ServerRepository> { ServerRepositoryImpl() }
}