package woowacourse.movie.model

import woowacourse.movie.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime

data class Movie(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    fun reserve(dateTime: LocalDateTime, ticketCount: TicketCount): Ticket {
        if (dateTime.toLocalDate() !in getScreeningDates()) throw IllegalArgumentException()
        return Ticket(id, dateTime, ticketCount.value)
    }

    fun getScreeningDates(): List<LocalDate> {
        val numberOfDays: Int = screeningStartDate.until(screeningEndDate).days
        return (FIRST_DAY_INDEX..numberOfDays).map { (screeningStartDate.plusDays(it.toLong())) }
    }

    companion object {
        private const val FIRST_DAY_INDEX = 0
    }
}
