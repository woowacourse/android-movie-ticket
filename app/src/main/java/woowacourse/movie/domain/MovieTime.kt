package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class MovieTime(
    val date: LocalDate,
    val time: Time
) : Serializable {
    fun isMovieDay(): Boolean = date.dayOfMonth in MOVIE_DAYS

    fun isSaleTime(): Boolean = time.hour <= 11 || time.hour >= 20

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}
