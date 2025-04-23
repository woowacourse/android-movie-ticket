package woowacourse.movie.domain.model.booking

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.movie.Movie
import java.time.LocalDate
import java.time.LocalTime

class BookingTest {
    private lateinit var movie: Movie

    @BeforeEach
    fun setUp() {
        movie =
            Movie(
                id = 1L,
                title = "해리 포터와 마법사의 돌",
                runningTime = 152,
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
            )
    }

    @Test
    fun `상영일자에 맞는 상영일들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 20), LocalTime.of(8, 0))
        val expected =
            listOf(
                LocalDate.of(2025, 4, 20),
                LocalDate.of(2025, 4, 21),
                LocalDate.of(2025, 4, 22),
                LocalDate.of(2025, 4, 23),
                LocalDate.of(2025, 4, 24),
                LocalDate.of(2025, 4, 25),
            )

        val actual = booking.screeningPeriods()

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 시간을 기준으로 예매 가능한 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 10), LocalTime.of(8, 0))
        val expected =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )
        val actual = booking.screeningTimes(LocalDate.of(2025, 4, 10))

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말인 경우에 해당하는 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 20), LocalTime.of(8, 0))
        val expected =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
                LocalTime.of(0, 0),
            )

        val actual = booking.screeningTimes(LocalDate.of(2025, 4, 20))

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일인 경우에 해당하는 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 18), LocalTime.of(8, 0))
        val expected =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        val actual = booking.screeningTimes(LocalDate.of(2025, 4, 18))

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
