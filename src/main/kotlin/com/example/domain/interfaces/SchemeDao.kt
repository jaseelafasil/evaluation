package com.example.domain.interfaces

import com.example.data.database.entities.SchemeTableEntity
import com.example.data.models.SchemeDetails

interface SchemeDao {
    suspend fun checkWhetherTableEmpty():Boolean
    suspend fun addSchemeDetails(schemeDetails:Array<SchemeDetails>):String?
    suspend fun searchSchemeByName(sName:String):List<SchemeDetails>
    suspend fun validateSchemeId(schemeId:String): SchemeTableEntity?
}