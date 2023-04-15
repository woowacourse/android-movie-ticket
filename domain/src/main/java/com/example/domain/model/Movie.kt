package com.example.domain.model

import java.time.LocalDate

data class Movie(
    val imgId: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String
)
