package com.example.domain.model

import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.seat.SeatPosition

class Tickets(tickets: List<Ticket>) {
    private val _tickets: List<Ticket> = tickets.toList()
    val tickets: List<Ticket>
        get() = _tickets.toList()

    init {
        val movie = tickets[0].movie
        val dateTime = tickets[0].dateTime
        require(tickets.all { it.movie == movie && it.dateTime == dateTime }) { ERROR_NO_MATCH_MOVIE_AND_DATETIME }
    }

    fun getTotalOriginMoney(): Money = Money(tickets.sumOf { it.originMoney.value })

    fun getTotalDiscountApplyMoney(discountPolicy: DiscountPolicy): Money =
        Money(tickets.sumOf { it.getDiscountMoney(discountPolicy).value })

    companion object {
        fun from(reservation: Reservation, seats: List<SeatPosition>): Tickets {
            require(reservation.count == seats.size) {
                ERROR_NO_MATCH_SEAT_SIZE
            }
            return Tickets(
                seats.map { Ticket(reservation.movie, reservation.dateTime, it) }
            )
        }

        private const val ERROR_NO_MATCH_SEAT_SIZE = "[ERROR] 좌석의 개수를 모두 골라주세요"
        private const val ERROR_NO_MATCH_MOVIE_AND_DATETIME = "[ERROR] 좌석의 개수와 일치하지 않습니다"
    }
}
