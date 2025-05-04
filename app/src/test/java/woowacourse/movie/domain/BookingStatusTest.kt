package woowacourse.movie.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.fixture.MovieFixture
import java.time.LocalDateTime

class BookingStatusTest {
    @Test
    fun `영화가_예매가_안된_상태이면_예매할_수_있다`() {
        val movie = MovieFixture.movie
        val seat = Seat(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, false, seat, bookedTime)

        val newBookingStatus = bookingStatus.book()

        val actual = newBookingStatus.isBooked

        Assertions.assertThat(actual).isTrue
    }

    @Test
    fun `영화가_예매가_된_상태이면_예매할_수_없다`() {
        val movie = MovieFixture.movie
        val seat = Seat(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, true, seat, bookedTime)

        assertThrows<IllegalStateException> {
            bookingStatus.book()
        }
    }

    @Test
    fun `영화가_예매가_된_상태이면_취소할_수_있다`() {
        val movie = MovieFixture.movie
        val seat = Seat(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, true, seat, bookedTime)

        val actual = bookingStatus.cancel()

        Assertions.assertThat(actual.isBooked).isFalse
    }

    @Test
    fun `영화가_예매가_안된_상태이면_취소할_수_없다`() {
        val movie = MovieFixture.movie
        val seat = Seat(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, false, seat, bookedTime)

        assertThrows<IllegalStateException> {
            bookingStatus.cancel()
        }
    }
}
