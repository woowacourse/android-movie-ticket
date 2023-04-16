package com.woowacourse.movie.domain

import java.time.LocalDateTime

typealias ReservationDomain = Reservation

data class Reservation(
    val movie: Movie,
    val dateTime: LocalDateTime,
    val ticket: Ticket
)
