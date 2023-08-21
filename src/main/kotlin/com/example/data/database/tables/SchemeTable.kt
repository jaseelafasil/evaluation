package com.example.data.database.tables


import com.example.data.models.SchemeDetails
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object SchemeTable:IntIdTable("schemes"){
    var schemeCode=varchar("schemecode",200)
    var schemeName=varchar("schemename",200)
}
class SchemeTableEntity(id:EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<SchemeTableEntity>(SchemeTable)
    var schemaCode by SchemeTable.schemeCode
    var schemaName by SchemeTable.schemeName

}
fun SchemeTableEntity.toSchemeDetails()=SchemeDetails(schemaCode,schemaName)