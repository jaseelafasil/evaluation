package com.example.services

import SchemeDaoImpl
import com.example.domain.clients.SchemeClient

class GetSchemaService(val dao:SchemeDaoImpl,val client:SchemeClient) {
    suspend fun getAndAddSchema(): String? {
        when (dao.checkWhetherTableEmpty()) {
            true -> {
                val schemaList = client.getResponse()
                val result: String? = dao.addSchemeDetails(schemaList)
                if (result != null) {
                    return "scheme details loaded"
                } else
                    return null
            }

            else ->
                return "scheme details loaded"
        }

    }
}