package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ReservationScheduleTest {
    private lateinit var reservationSchedule: ReservationSchedule

    @BeforeEach
    fun setUp() {
        reservationSchedule = ReservationSchedule()
    }

    @Test
    fun `주말이면 9 ~ 23을 반환`() {
        val weekend = LocalDate.of(2024, 4, 7)

        val actual = reservationSchedule.obtainScreeningTimes(weekend)
        val expected = (9..23 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일이면 10 ~ 24를 반환`() {
        val weekday = LocalDate.of(2024, 4, 8)

        val actual = reservationSchedule.obtainScreeningTimes(weekday)
        val expected = (10..24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `시작 날짜, 끝 날짜에 해당하는 만큼 날짜 반환`() {
        val firstScreeningDate = LocalDate.of(2023, 1, 1)
        val lastScreeningDate = LocalDate.of(2023, 1, 3)

        val actual = reservationSchedule.obtainScreeningDates(firstScreeningDate, lastScreeningDate)
        val expected =
            listOf(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 3))

        assertThat(actual).isEqualTo(expected)
    }
}
