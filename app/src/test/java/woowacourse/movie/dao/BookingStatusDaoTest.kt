package woowacourse.movie.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.fixture.MovieFixture

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
                    MovieFixture.movie,
                    false,
                ),
            )

        assertThat(actual).isEqualTo(expected)
    }
}
