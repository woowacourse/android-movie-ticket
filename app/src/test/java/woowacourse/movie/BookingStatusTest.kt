package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.fixture.MovieFixture
import java.time.LocalDateTime

class BookingStatusTest {
    @Test
    fun `영화가_예매가_안된_상태이면_예매할_수_있다`() {
        val movie = MovieFixture.movie
        val memberCount = MemberCount(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, false, memberCount, bookedTime)

        val newBookingStatus = bookingStatus.book()

        val actual = newBookingStatus.isBooked

        assertThat(actual).isTrue
    }

    @Test
    fun `영화가_예매가_된_상태이면_예매할_수_없다`() {
        val movie = MovieFixture.movie
        val memberCount = MemberCount(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, true, memberCount, bookedTime)

        assertThrows<IllegalStateException> {
            bookingStatus.book()
        }
    }

    @Test
    fun `영화가_예매가_된_상태이면_취소할_수_있다`() {
        val movie = MovieFixture.movie
        val memberCount = MemberCount(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, true, memberCount, bookedTime)

        val actual = bookingStatus.cancel()

        assertThat(actual.isBooked).isFalse
    }

    @Test
    fun `영화가_예매가_안된_상태이면_취소할_수_없다`() {
        val movie = MovieFixture.movie
        val memberCount = MemberCount(1)
        val bookedTime = LocalDateTime.of(2025, 4, 1, 9, 0, 0)
        val bookingStatus = BookingStatus(movie, false, memberCount, bookedTime)

        assertThrows<IllegalStateException> {
            bookingStatus.cancel()
        }
    }
}
