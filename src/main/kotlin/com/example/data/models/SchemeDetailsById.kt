package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class  SchemeDetailsById (val meta:MetaDetails?,var data:List<DateDetails>?,val status:String)