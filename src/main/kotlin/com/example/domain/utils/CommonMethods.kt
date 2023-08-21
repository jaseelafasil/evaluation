package com.example.domain.utils

import com.example.data.models.SchemeDetailsById
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun filterWithDates(filter:String,infoById:SchemeDetailsById):SchemeDetailsById {
    val startDate = when (filter) {
        "1W" -> LocalDate.now().minus(1, ChronoUnit.WEEKS)
        "1M" -> LocalDate.now().minus(1, ChronoUnit.MONTHS)
        "1Y" -> LocalDate.now().minus(1, ChronoUnit.YEARS)

        else
        -> LocalDate.now().minus(5, ChronoUnit.YEARS)
    }
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val formattedDate = startDate.format(dateFormatter)
    infoById.data?.filter {
        LocalDate.parse(it.date, dateFormatter).isAfter(LocalDate.parse(formattedDate, dateFormatter))
    }?.let { infoById.data = it }
    return  infoById

}