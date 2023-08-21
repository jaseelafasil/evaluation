package com.example

import com.example.data.database.DatabaseFactory
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatus
import com.example.plugins.validateRequest
import com.example.routes.schemaRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSerialization()
    validateRequest()
    configureStatus()
    schemaRouting()

}
