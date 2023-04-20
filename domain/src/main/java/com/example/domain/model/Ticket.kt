package com.example.domain.model

import com.example.domain.model.price.Price
import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val title: String,
    val playingDate: LocalDate,
    val playingTime: LocalTime,
    val count: Int,
    val price: Price,
    val payment: Payment
)
