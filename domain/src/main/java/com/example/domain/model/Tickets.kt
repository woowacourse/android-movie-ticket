package com.example.domain.model

import com.example.domain.model.seat.SeatPosition

class Tickets private constructor(tickets: List<Ticket>) {
    private val _tickets: List<Ticket> = tickets.toList()
    val tickets: List<Ticket>
        get() = _tickets.toList()

    companion object {
        fun from(reservation: Reservation, seats: List<SeatPosition>): Tickets {
            require(reservation.count == seats.size) { ERROR_NO_MATCH_RESERVATION_SIZE }
            return Tickets(seats.map { Ticket(reservation.movie, reservation.dateTime, it) })
        }

        private const val ERROR_NO_MATCH_RESERVATION_SIZE = "[ERROR] 예약 정보와 선택하신 좌석의 개수가 일치하지 않습니다"
    }
}
