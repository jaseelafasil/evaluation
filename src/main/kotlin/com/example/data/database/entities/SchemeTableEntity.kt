package com.example.data.database.entities

import com.example.data.database.tables.SchemeTable
import com.example.data.models.SchemeDetails
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SchemeTableEntity(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<SchemeTableEntity>(SchemeTable)
    var schemeCode by SchemeTable.schemeCode
    var schemeName by SchemeTable.schemeName

}
