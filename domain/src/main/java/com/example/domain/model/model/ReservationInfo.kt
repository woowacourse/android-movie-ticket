package com.example.domain.model.model

import java.time.LocalDate
import java.time.LocalTime

data class ReservationInfo(
    val title: String,
    val playingDate: LocalDate,
    val playingTime: LocalTime,
    val count: Int,
    val payment: Payment
)
