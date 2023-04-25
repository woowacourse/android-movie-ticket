package com.example.domain.model.model

import com.example.domain.model.price.MoviePrice

data class Ticket(val reservationInfo: ReservationInfo, val price: MoviePrice, val seats: List<Seat>)
