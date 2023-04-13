package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class TicketTime(
    val date: LocalDate,
    val time: Time
) : Serializable {
    fun isMovieDay(): Boolean = date.dayOfMonth in MOVIE_DAYS

    fun isSaleTime(): Boolean {
        return time.hour <= SALE_TIME_STANDARD_MORNING || time.hour >= SALE_TIME_STANDARD_NIGHT
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val SALE_TIME_STANDARD_MORNING = 11
        private const val SALE_TIME_STANDARD_NIGHT = 20
    }
}
