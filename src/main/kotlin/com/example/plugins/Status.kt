package com.example.plugins

import com.example.data.database.models.myResponse
import com.example.domain.exceptions.ParameterNotValid

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.configureStatus(){
    install(StatusPages){

        exception <ExposedSQLException>{ call: ApplicationCall, cause ->
            val res=myResponse("failed","","db operation failed")
            call.respond(res)
        }
        exception <ParameterNotValid>{ call: ApplicationCall, cause ->
            val res=myResponse("failed","",cause.message.toString())
            call.respond(res)
        }
    }
}