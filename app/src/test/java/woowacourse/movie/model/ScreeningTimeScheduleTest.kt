package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class ScreeningTimeScheduleTest {
    @Test
    fun `주말에는_오전_9시부터_두_시간_간격으로_상영한다`() {
        val weekend = LocalDate.of(2024, 4, 21)
        val actual = ScreeningTimeSchedule.generateAvailableTimeSlots(weekend)
        val expected = timeSlots(9, 11, 13, 15, 17, 19, 21, 23)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일에는 오전 10시부터 두 시간 간격으로 상영한다`() {
        val weekDay = LocalDate.of(2024, 4, 24)
        val actual = ScreeningTimeSchedule.generateAvailableTimeSlots(weekDay)
        val expected = timeSlots(10, 12, 14, 16, 18, 20, 22, 0)
        assertThat(actual).isEqualTo(expected)
    }

    private fun timeSlots(vararg hour: Int): List<LocalTime> = hour.map { LocalTime.of(it, 0) }
}
