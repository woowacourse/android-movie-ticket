package com.example.domain

import com.example.domain.seat.SeatPosition
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
    val seats: List<SeatPosition>
)
