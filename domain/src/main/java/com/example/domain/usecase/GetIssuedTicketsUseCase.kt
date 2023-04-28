package com.example.domain.usecase

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Reservation
import com.example.domain.model.Tickets
import com.example.domain.model.seat.SeatPosition

class GetIssuedTicketsUseCase(private val discountPolicy: DiscountPolicy = DefaultDiscountPolicy()) {
    operator fun invoke(reservation: Reservation, seats: List<SeatPosition>): Tickets =
        Tickets.from(reservation, seats)
}
