package com.example.services

import SchemeDaoImpl
import com.example.data.database.entities.SchemeTableEntity
import com.example.data.models.SchemeDetails
import com.example.data.models.SchemeDetailsById
import com.example.data.models.SearchRequestById
import com.example.domain.clients.SchemeClient
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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
    suspend fun getSchemeByName(sName: String):List<SchemeDetails>{
        return dao.searchSchemeByName(sName)
    }
    suspend fun validateSchemeId(sId:String):SchemeTableEntity?{
        return dao.validateSchemeId(sId)
    }
    suspend fun getSchemeById(sIdReq:SearchRequestById):SchemeDetailsById?{
        dao.validateSchemeId(sIdReq.schemeId.toString())?.let {
            val infoById=client.getResponseById(sIdReq.schemeId.toString())
            val filter=sIdReq.filter.toString()
            return filterWithDates(filter,infoById)
        }?:return null
    }
    fun filterWithDates(filter:String,infoById: SchemeDetailsById): SchemeDetailsById {
        val startDate = when (filter) {
            "1W" -> LocalDate.now().minus(1, ChronoUnit.WEEKS)
            "1M" -> LocalDate.now().minus(1, ChronoUnit.MONTHS)
            "1Y" -> LocalDate.now().minus(1, ChronoUnit.YEARS)

            else
            -> LocalDate.now().minus(5, ChronoUnit.YEARS)
        }
        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = startDate.format(dateFormatter)
        infoById.data?.filter {
            LocalDate.parse(it.date, dateFormatter).isAfter(LocalDate.parse(formattedDate, dateFormatter))
        }?.let { infoById.data = it }
        return  infoById

    }
}