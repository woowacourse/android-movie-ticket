package woowacourse.movie.dao

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.Movies
import java.io.File
import java.time.LocalDateTime

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
        val (title, isBooked, memberCount, dateTime) = input.split(",").map { it.trim() }
        val movie = movies.find(title)
        val bookedTime = LocalDateTime.parse(dateTime)
        return BookingStatus(movie, isBooked.toBoolean(), MemberCount(memberCount.toInt()), bookedTime)
    }

    companion object {
        private const val DIRECTORY =
            "../app/src/main/java/woowacourse/movie/data/booking_status.md"
    }
}
