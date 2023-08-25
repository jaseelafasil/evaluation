package com.example.domain.clients

import com.example.data.models.SchemeDetails
import com.example.data.models.SchemeDetailsById
import com.example.data.models.SearchRequestById
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SchemeClient (){
    val client = HttpClient(Apache5)

    suspend fun getResponse():Array<SchemeDetails>{
        val response = client.get("https://api.mfapi.in/mf").bodyAsText()
        return Gson().fromJson(response, Array<SchemeDetails>::class.java)

    }
    suspend fun getResponseById(sId:String):SchemeDetailsById{
        val response = client.get("https://api.mfapi.in/mf/${sId}").bodyAsText()
        return   Gson().fromJson(response, SchemeDetailsById::class.java)
    }
}