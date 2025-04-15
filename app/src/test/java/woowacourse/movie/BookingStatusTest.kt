package woowacourse.movie

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.fixture.MovieFixture

class BookingStatusTest {
    @Test
    fun `영화가 예매가 안된 상태이면 예매할 수 있다`() {
        val movie = MovieFixture.movie

        val bookingStatus = BookingStatus(movie, false)

        val actual = bookingStatus.book()

        val expected = BookingStatus(movie, true)

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화가 예매가 된 상태이면 예매할 수 없다`() {
        val movie = MovieFixture.movie

        val bookingStatus = BookingStatus(movie, true)

        assertThrows<IllegalStateException> {
            bookingStatus.book()
        }
    }

    @Test
    fun `영화가 예매가 된 상태이면 취소할 수 있다`() {
        val movie = MovieFixture.movie

        val bookingStatus = BookingStatus(movie, true)

        val actual = bookingStatus.cancel()

        val expected = BookingStatus(movie, false)

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화가 예매가 안된 상태이면 취소할 수 없다`() {
        val movie = MovieFixture.movie
        val bookingStatus = BookingStatus(movie, false)

        assertThrows<IllegalStateException> {
            bookingStatus.cancel()
        }
    }
}
