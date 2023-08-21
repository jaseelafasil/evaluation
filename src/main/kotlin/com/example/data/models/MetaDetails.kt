package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MetaDetails(val fund_house:String?,
                       val scheme_type:String?,
                       val scheme_category:String?,
                       val scheme_code:String?,
                       val scheme_name:String?)