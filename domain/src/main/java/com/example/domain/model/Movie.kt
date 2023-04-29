package com.example.domain.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Movie(
    val imgId: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String
) {
    val runningDates: List<LocalDate>
        get() {
            val betweenDays = ChronoUnit.DAYS.between(startDate, endDate)
            return (0..betweenDays).map { startDate.plusDays(it) }
        }
}
