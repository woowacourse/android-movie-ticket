package com.example.domain.model

import java.time.LocalDate

interface Movie {
    val imgId: Int
    val title: String
    val startDate: LocalDate
    val endDate: LocalDate
    val runningTime: Int
    val description: String
}
