package woowacourse.movie.domain

import java.time.LocalDateTime

class TicketTime(
    val dateTime: LocalDateTime
) {
    fun isMovieDay(): Boolean = dateTime.dayOfMonth in MOVIE_DAYS

    fun isSaleTime(): Boolean {
        return dateTime.hour <= SALE_TIME_STANDARD_MORNING || dateTime.hour >= SALE_TIME_STANDARD_NIGHT
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val SALE_TIME_STANDARD_MORNING = 11
        private const val SALE_TIME_STANDARD_NIGHT = 20
    }
}
