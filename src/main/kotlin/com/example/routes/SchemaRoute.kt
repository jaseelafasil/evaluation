package com.example.routes

import SchemeDaoImpl
import com.example.data.appConstants.ApiEndPoints.GET_ALL_SCHEME_DETAILS
import com.example.data.appConstants.ApiEndPoints.GET_SCHEME_BY_ID
import com.example.data.appConstants.ApiEndPoints.GET_SCHEME_BY_NAME

import com.example.data.database.models.myResponse
import com.example.data.models.*
import com.example.domain.utils.filterWithDates
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.schemaRouting() {
    val dao = SchemeDaoImpl()
    routing {
        get(GET_ALL_SCHEME_DETAILS) {
            val client = HttpClient(Apache5)
            val response = client.get("https://api.mfapi.in/mf").bodyAsText()
            val schemeList = Gson().fromJson(response, Array<SchemeDetails>::class.java)
            val result = dao.addSchemeDetails(schemeList)
            if (result != null) {
                val res = myResponse("success", "", "data is available")
                call.respond(res)
            }

        }
        post(GET_SCHEME_BY_NAME) {
            val sNameReq = call.receive<SearchRequest>()
            val result = dao.searchSchemeByName(sNameReq.schemeName.toString())
            val resp = myResponse("success", result, "")
            call.respond(resp)

        }
        post(GET_SCHEME_BY_ID) {
            val sIdReq = call.receive<SearchRequestById>()
            dao.validateSchemeId(sIdReq.schemeId.toString())?.let {
                val client = HttpClient(Apache5)
                val response = client.get("https://api.mfapi.in/mf/${sIdReq.schemeId.toString()}").bodyAsText()
                val infoById: SchemeDetailsById = Gson().fromJson(response, SchemeDetailsById::class.java)
                val filter = sIdReq.filter.toString()
                filterWithDates(filter, infoById).takeIf { it.data.isNotEmpty() }?.let {
                    call.respond(myResponse("success", infoById, ""))
                } ?: call.respond(myResponse("success", "", "no data available"))
            }?:call.respond(myResponse("success", "", "invalid id"))
        }


    }
}

