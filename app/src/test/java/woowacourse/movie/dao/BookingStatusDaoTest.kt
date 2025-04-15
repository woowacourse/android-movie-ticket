package woowacourse.movie.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class BookingStatusDaoTest {
    private lateinit var bookingStatusDao: BookingStatusDao

    @BeforeEach
    fun setUp() {
        bookingStatusDao = BookingStatusDao()
    }

    @Test
    fun `booking_status의 데이터를 불러올 수 있다`() {
        val movieDao = MovieDao()
        val movies = movieDao.movies()
        val actual = bookingStatusDao.bookingStatuses(movies)
        val expected =
            setOf(
                BookingStatus(
                    Movie(
                        "해리포터와 마법사의 돌",
                        "https://tinyurl.com/mjn9ntrz",
                        LocalDateTime.of(2025, 4, 1, 0, 0, 0),
                        LocalDateTime.of(2025, 4, 25, 23, 59, 59),
                        152.minutes,
                    ),
                    false,
                ),
            )

        assertThat(actual).isEqualTo(expected)
    }
}
