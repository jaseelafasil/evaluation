package com.example.plugins
import com.example.domain.clients.SchemeClient
import com.example.domain.interfaces.SchemeDao
import com.example.services.GetSchemaService

import SchemeDaoImpl
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.logger.slf4jLogger
val appModule= module{
    single <SchemeDaoImpl> { SchemeDaoImpl() }
    single { SchemeClient() }
    single { GetSchemaService(get(),get()) }
}
fun Application.configureKoin() {
install(Koin){
    slf4jLogger()
    modules(appModule)
}


}
