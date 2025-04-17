package woowacourse.movie.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalTime

class BookingTest {
    private lateinit var movie: Movie

    @BeforeEach
    fun setUp() {
        movie =
            Movie(
                imageSource = R.drawable.harry_potter,
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
                "2025-04-20",
                "2025-04-21",
                "2025-04-22",
                "2025-04-23",
                "2025-04-24",
                "2025-04-25",
            )

        val actual = booking.screeningPeriods()

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 시간을 기준으로 예매 가능한 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 10), LocalTime.of(8, 0))
        val expected =
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")

        val actual = booking.screeningTimes("2025-04-10")

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말인 경우에 해당하는 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 20), LocalTime.of(8, 0))
        val expected =
            listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00", "24:00")

        val actual = booking.screeningTimes("2025-04-20")

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일인 경우에 해당하는 시간들을 가져온다`() {
        val booking = Booking(movie, LocalDate.of(2025, 4, 18), LocalTime.of(8, 0))
        val expected =
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")

        val actual = booking.screeningTimes("2025-04-18")

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
