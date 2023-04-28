package com.example.domain.model

import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val dateTime: LocalDateTime,
    val count: Int
)
