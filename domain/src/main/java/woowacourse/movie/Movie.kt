package woowacourse.movie

import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import java.time.LocalDate
import java.time.LocalDateTime

data class Movie(
    val id: Long,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    val screeningDates: List<LocalDate>
        get() {
            val numberOfDays: Int = startDate.until(endDate).days
            return (0..numberOfDays).map { (startDate.plusDays(it.toLong())) }
        }

    fun reserve(dateTime: LocalDateTime, seat: Seat): Ticket {
        val date = dateTime.toLocalDate()
        if (date !in screeningDates) throw IllegalArgumentException("상영 기간이 아닙니다.")
        return Ticket(id, dateTime, seat)
    }
}
