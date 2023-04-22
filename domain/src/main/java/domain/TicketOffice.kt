package domain

import domain.discountPolicy.DisCountPolicies
import domain.seatPolicy.SeatPolicies
import java.time.LocalDateTime

class TicketOffice(
    val tickets: Tickets = Tickets(listOf()),
    private val disCountPolicies: DisCountPolicies = DisCountPolicies(),
    private val seatPolicies: SeatPolicies = SeatPolicies(),
    val peopleCount: Int
) {

    fun generateTicket(date: LocalDateTime, seatRow: Int, seatCol: Int): Ticket {
        val seat = Seat(seatRow, seatCol, seatPolicies)
        return Ticket(date, seat, disCountPolicies)
    }

    fun isAvailableAddTicket(): Boolean = tickets.list.size < peopleCount

    fun addTicket(ticket: Ticket) {
        tickets.addTicket(ticket)
    }

    fun deleteTicket(ticket: Ticket) {
        tickets.deleteTicket(ticket)
    }
}
