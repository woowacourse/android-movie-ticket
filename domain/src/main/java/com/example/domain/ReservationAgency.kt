package com.example.domain

import java.time.LocalDateTime

class ReservationAgency(
    private val movie: Movie,
    private val peopleCount: Int,
    private val selectedDateTime: LocalDateTime
) {

    fun canReserve(seats: List<Seat>): Boolean {
        if (seats.distinct().size != peopleCount) return false
        return true
    }

}
