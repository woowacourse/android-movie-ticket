package woowacourse.movie

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class BookingStatusTest {

    @Test
    fun `영화가 예매가 안된 상태이면 예매할 수 있다`() {
        val movie = Movie(
            "해리포터와 마법사의 돌",
            "해리포터 이미지",
            LocalDateTime.of(2025, 4, 1, 0, 0, 0),
            LocalDateTime.of(2025, 4, 25, 0, 0, 0),
            152.minutes
        )

        val bookingStatus = BookingStatus(movie, false)

        val actual = bookingStatus.book()

        val expected = BookingStatus(movie, true)

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화가 예매가 된 상태이면 예매할 수 없다`() {
        val movie = Movie(
            "해리포터와 마법사의 돌",
            "해리포터 이미지",
            LocalDateTime.of(2025, 4, 1, 0, 0, 0),
            LocalDateTime.of(2025, 4, 25, 0, 0, 0),
            152.minutes
        )

        val bookingStatus = BookingStatus(movie, true)

        assertThrows<IllegalStateException> {
            bookingStatus.book()
        }
    }

    @Test
    fun `영화가 예매가 된 상태이면 취소할 수 있다`() {
        val movie = Movie(
            "해리포터와 마법사의 돌",
            "해리포터 이미지",
            LocalDateTime.of(2025, 4, 1, 0, 0, 0),
            LocalDateTime.of(2025, 4, 25, 0, 0, 0),
            152.minutes
        )

        val bookingStatus = BookingStatus(movie, true)

        val actual = bookingStatus.cancel()

        val expected = BookingStatus(movie, false)

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화가 예매가 안된 상태이면 취소할 수 없다`() {
        val movie = Movie(
            "해리포터와 마법사의 돌",
            "해리포터 이미지",
            LocalDateTime.of(2025, 4, 1, 0, 0, 0),
            LocalDateTime.of(2025, 4, 25, 0, 0, 0),
            152.minutes
        )

        val bookingStatus = BookingStatus(movie, false)

        assertThrows<IllegalStateException> {
            bookingStatus.cancel()
        }
    }
}