package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate

class DailyScheduleTest {
    @Nested
    @DisplayName("주말에는 상영 시간이 9시부터 시작한다")
    inner class WeedEndScreeningHourTest {

        @Test
        fun `토요일의 첫 상영 시간은 9시다`() {
            val testDay = LocalDate.of(2024, 4, 27)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.SATURDAY)
            assertThat(result).isEqualTo(9)
        }

        @Test
        fun `일요일의 첫 상영 시간은 9시다`() {
            val testDay = LocalDate.of(2024, 4, 28)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.SUNDAY)
            assertThat(result).isEqualTo(9)
        }
    }

    @Nested
    @DisplayName("평일에는 상영 시간이 10시부터 시작한다")
    inner class WeedDayScreeningHourTest {

        @Test
        fun `월요일의 첫 상영 시간은 10시다`() {
            val testDay = LocalDate.of(2024, 4, 22)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.MONDAY)
            assertThat(result).isEqualTo(10)
        }

        @Test
        fun `화요일의 첫 상영 시간은 10시다`() {
            val testDay = LocalDate.of(2024, 4, 23)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.TUESDAY)
            assertThat(result).isEqualTo(10)
        }

        @Test
        fun `수요일의 첫 상영 시간은 10시다`() {
            val testDay = LocalDate.of(2024, 4, 24)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.WEDNESDAY)
            assertThat(result).isEqualTo(10)
        }

        @Test
        fun `목요일의 첫 상영 시간은 10시다`() {
            val testDay = LocalDate.of(2024, 4, 25)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.THURSDAY)
            assertThat(result).isEqualTo(10)
        }

        @Test
        fun `금요일의 첫 상영 시간은 10시다`() {
            val testDay = LocalDate.of(2024, 4, 26)
            val dailySchedule = DailySchedule(testDay)
            val result = dailySchedule.times.first().hour

            assertThat(testDay.dayOfWeek).isEqualTo(DayOfWeek.FRIDAY)
            assertThat(result).isEqualTo(10)
        }
    }
}
