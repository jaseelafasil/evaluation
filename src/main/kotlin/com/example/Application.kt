package com.example

import SchemeDaoImpl
import com.example.data.database.DatabaseFactory
import com.example.domain.interfaces.SchemeDao
import com.example.plugins.*
import com.example.routes.schemaRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.Koin
import org.koin.core.context.loadKoinModules

import org.koin.dsl.module



fun main(args: Array<String>): Unit {

    return EngineMain.main(args)}


fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSerialization()
    validateRequest()
    configureStatus()
    schemaRouting()
    configureKoin()





}
