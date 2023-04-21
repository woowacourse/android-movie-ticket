package com.example.domain.model.model

import com.example.domain.model.price.Price

data class Ticket(val reservationInfo: ReservationInfo, val price: Price, val seats: List<Seat>)
