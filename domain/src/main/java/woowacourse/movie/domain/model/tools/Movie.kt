package woowacourse.movie.domain.model.tools

import woowacourse.movie.domain.model.tools.seat.Seat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.SortedSet

data class Movie(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    fun reserve(dateTime: LocalDateTime, ticketCount: TicketCount, ticketSeats: SortedSet<Seat>): Ticket {
        if (dateTime.toLocalDate() !in getScreeningDates()) throw IllegalArgumentException()
        return Ticket(id, dateTime, ticketCount.value, ticketSeats)
    }

    fun getScreeningDates(): List<LocalDate> {
        val numberOfDays: Int = screeningStartDate.until(screeningEndDate).days
        return (FIRST_DAY_INDEX..numberOfDays).map { (screeningStartDate.plusDays(it.toLong())) }
    }

    companion object {
        private const val FIRST_DAY_INDEX = 0
    }
}
