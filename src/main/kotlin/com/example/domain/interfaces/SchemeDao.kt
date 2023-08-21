package com.example.domain.interfaces


import com.example.data.models.SchemeDetails

interface SchemeDao {
    suspend fun addSchemeDetails(schemeDetails:Array<SchemeDetails>):String?
    suspend fun searchSchemeByName(sName:String):List<SchemeDetails>
}