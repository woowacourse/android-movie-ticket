package woowacourse.movie.dao

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movies
import java.io.File

class BookingStatusDao {
    fun bookingStatuses(movies: Movies): Set<BookingStatus> {
        return File(DIRECTORY).readLines()
            .drop(1)
            .map { bookingStatus(it, movies) }
            .toSet()
    }

    fun bookingStatus(
        input: String,
        movies: Movies,
    ): BookingStatus {
        val (title, isBooked) = input.split(",").map { it.trim() }
        val movie = movies.find(title)
        return BookingStatus(movie, isBooked.toBoolean())
    }

    companion object {
        private const val DIRECTORY =
            "../app/src/main/java/woowacourse/movie/data/booking_status.md"
    }
}
