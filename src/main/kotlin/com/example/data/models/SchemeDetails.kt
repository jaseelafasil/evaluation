package com.example.data.models



import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SchemeDetails(
    @SerializedName("schemeCode") var schemeCode:String,
    @SerializedName("schemeName") var schemeName:String
)