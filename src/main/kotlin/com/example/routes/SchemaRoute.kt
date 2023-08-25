package com.example.routes

import SchemeDaoImpl
import com.example.data.appConstants.ApiEndPoints.GET_ALL_SCHEME_DETAILS
import com.example.data.appConstants.ApiEndPoints.GET_SCHEME_BY_ID
import com.example.data.appConstants.ApiEndPoints.GET_SCHEME_BY_NAME

import com.example.data.database.models.myResponse
import com.example.data.models.*
import com.example.*

import com.example.services.GetSchemaService
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.schemaRouting() {
        routing {
            val getAndAddService by inject<GetSchemaService>()
            get(GET_ALL_SCHEME_DETAILS) {
                val result=getAndAddService.getAndAddSchema()
                if (result != null) {
                    val res = myResponse("success", "", "data is available")
                    call.respond(res)
                }

            }
            post(GET_SCHEME_BY_NAME) {
                val sNameReq = call.receive<SearchRequest>()
                val result = getAndAddService.getSchemeByName(sNameReq.schemeName.toString())
                val resp = myResponse("success", result, "")
                call.respond(resp)

            }
            post(GET_SCHEME_BY_ID) {
                val sIdReq = call.receive<SearchRequestById>()
                getAndAddService.getSchemeById(sIdReq)?.let { it->call.respond(myResponse("success", it, "")) }?:
                call.respond(myResponse("success", "", "invalid id"))
            }


        }

}

