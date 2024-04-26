package woowacourse.movie.model.ticketing

import java.time.LocalDate
import java.time.LocalTime

class BookingDateTime(
    val date: LocalDate,
    val time: LocalTime,
) {
    companion object {
        fun of(
            date: String,
            time: String,
        ): BookingDateTime {
            return BookingDateTime(
                LocalDate.parse(date),
                LocalTime.parse(time),
            )
        }
    }
}
