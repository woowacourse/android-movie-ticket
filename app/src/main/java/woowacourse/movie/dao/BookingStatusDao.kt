package woowacourse.movie.dao

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import java.io.File

class BookingStatusDao {
    fun bookingStatuses(movies: Map<String, Movie>): Set<BookingStatus> {
        return File(DIRECTORY).readLines()
            .drop(1)
            .map { bookingStatus(it, movies) }
            .toSet()
    }

    fun bookingStatus(
        input: String,
        movies: Map<String, Movie>,
    ): BookingStatus {
        val (title, isBooked) = input.split(",").map { it.trim() }
        val movie = movies[title] ?: throw IllegalStateException(ERR_MOVIE_NOT_FOUND)
        return BookingStatus(movie, isBooked.toBoolean())
    }

    companion object {
        private const val DIRECTORY =
            "../app/src/main/java/woowacourse/movie/data/booking_status.md"
        private const val ERR_MOVIE_NOT_FOUND = "영화가 존재하지 않습니다."
    }
}
