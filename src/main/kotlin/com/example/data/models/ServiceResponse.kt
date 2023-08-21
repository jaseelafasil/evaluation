package com.example.data.database.models

import kotlinx.serialization.Serializable

@Serializable
data class myResponse<T>(val status:String,val result:T,val message:String)