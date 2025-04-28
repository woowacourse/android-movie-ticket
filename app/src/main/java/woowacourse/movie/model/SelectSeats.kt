package woowacourse.movie.model

import woowacourse.movie.selectSeat.SeatPrice
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SelectSeats {
    lateinit var ticket: Ticket

    fun setTicket(ticketUIModelDTO: woowacourse.movie.uiModel.TicketUIModel) {
        ticket =
            Ticket(
                ticketUIModelDTO.title,
                parserDate(ticketUIModelDTO.date),
                parserTime(ticketUIModelDTO.time),
                mutableListOf(),
                0,
            )
    }

    fun isSeatSelected(seatTag: String): Boolean {
        if (seatTag in ticket.seats) return true
        return false
    }

    fun unSelectSeat(seatTag: String) {
        minusMoney(seatTag)
        ticket.seats.remove(seatTag)
    }

    fun selectSeat(seatTag: String) {
        plusMoney(seatTag)
        ticket.seats.add(seatTag)
    }

    fun minusMoney(seatTag: String) {
        ticket.money -= SeatPrice.getPrice(seatTag)
    }

    fun plusMoney(seatTag: String) {
        ticket.money += SeatPrice.getPrice(seatTag)
    }

    fun parserTime(time: String): LocalTime {
        val formater = DateTimeFormatter.ofPattern("HH:mm")
        return LocalTime.parse(time, formater)
    }

    fun parserDate(date: String): LocalDate {
        val formater = DateTimeFormatter.ofPattern("yyyy.M.d")
        return LocalDate.parse(date, formater)
    }

    fun isFullSeat(count: Int) = (ticket.seats.size == count)
}
