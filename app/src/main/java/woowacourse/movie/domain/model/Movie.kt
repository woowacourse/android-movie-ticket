package woowacourse.movie.domain.model

import woowacourse.movie.domain.TicketCount
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
    fun reserve(dateTime: LocalDateTime, ticketCount: TicketCount) =
        Ticket(id, dateTime, ticketCount.value)

    fun getScreeningDates(): List<LocalDate> {
        val numberOfDays: Int = screeningStartDate.until(screeningEndDate).days
        return (0..numberOfDays).map { (screeningStartDate.plusDays(it.toLong())) }
    }
}
