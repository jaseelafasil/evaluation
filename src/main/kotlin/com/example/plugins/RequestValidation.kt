package com.example.plugins

import com.example.data.models.SearchRequest
import com.example.data.models.SearchRequestById
import com.example.domain.exceptions.ParameterNotValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.validateRequest(){
    install(RequestValidation){

        validate<SearchRequest> {sRequest->
            if (sRequest.schemeName.isNullOrEmpty())
                throw ParameterNotValid("schemeName")
            else
                ValidationResult.Valid

        }
        validate<SearchRequestById> {idRequest->
            if (idRequest.schemeId.isNullOrEmpty())
                throw ParameterNotValid("schemeId")
            else if(!(idRequest.filter.toString() in listOf<String>("1M","5Y","1Y","1W"))){
                throw ParameterNotValid("filter")
            }
            else
                ValidationResult.Valid
        }
    }
}