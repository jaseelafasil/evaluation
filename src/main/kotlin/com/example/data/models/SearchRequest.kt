package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(var schemeName:String?)
@Serializable
data class SearchRequestById(var schemeId:String?,var filter:String?)