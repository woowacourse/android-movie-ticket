package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import woowacourse.movie.domain.TestFixture.WEEKDAY_TIMES
import woowacourse.movie.domain.TestFixture.WEEKEND_TIMES
import java.time.LocalDate

class BasicScreeningScheduleSystemTest {
    @Test
    fun `시작일과 종료일로 상영 일정을 얻을 수 있다`() {
        val result =
            BasicScreeningScheduleSystem().getSchedules(
                LocalDate.of(2024, 3, 31),
                LocalDate.of(2024, 4, 3),
            )

        val expected =
            ScreeningSchedule(
                listOf(
                    DailySchedule(
                        // 일요일
                        LocalDate.of(2024, 3, 31),
                        WEEKEND_TIMES,
                    ),
                    DailySchedule(
                        LocalDate.of(2024, 4, 1),
                        WEEKDAY_TIMES,
                    ),
                    DailySchedule(
                        LocalDate.of(2024, 4, 2),
                        WEEKDAY_TIMES,
                    ),
                    DailySchedule(
                        LocalDate.of(2024, 4, 3),
                        WEEKDAY_TIMES,
                    ),
                ),
            )
        assertThat(result).isEqualTo(expected)
    }

    @Nested
    @DisplayName("주말에는 오전 9시부터 23시까지 두 시간 간격으로 상영한다")
    inner class WeedEndScreeningHourTest {
        @ParameterizedTest
        @CsvSource(
            SATURDAY,
            SUNDAY,
        )
        fun `주말의 첫 상영 시간은 9시다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).first().hour

            assertThat(result).isEqualTo(9)
        }

        @ParameterizedTest
        @CsvSource(
            SATURDAY,
            SUNDAY,
        )
        fun `주말의 마지막 상영 시간은 23시다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).last().hour

            assertThat(result).isEqualTo(23)
        }

        @ParameterizedTest
        @CsvSource(
            SATURDAY,
            SUNDAY,
        )
        fun `주말에는 총 8번 상영한다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).size

            assertThat(result).isEqualTo(8)
        }
    }

    @Nested
    @DisplayName("평일에는 오전 10시부터 자정까지 두 시간 간격으로 상영한다")
    inner class WeedDayScreeningHourTest {
        @ParameterizedTest
        @CsvSource(
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
        )
        fun `평일의 첫 상영 시간은 10시다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).first().hour

            assertThat(result).isEqualTo(10)
        }

        @ParameterizedTest
        @CsvSource(
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
        )
        fun `평일의 마지막 상영 시간은 0시다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).last().hour

            assertThat(result).isEqualTo(0)
        }

        @ParameterizedTest
        @CsvSource(
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
        )
        fun `평일에는 총 8번 상영한다`(date: String) {
            val testDay = LocalDate.parse(date)
            val dailySchedule = BasicScreeningScheduleSystem()
            val result = dailySchedule.getScreeningTimes(testDay).size

            assertThat(result).isEqualTo(8)
        }
    }

    companion object {
        const val MONDAY = "2024-04-22"
        const val TUESDAY = "2024-04-23"
        const val WEDNESDAY = "2024-04-24"
        const val THURSDAY = "2024-04-25"
        const val FRIDAY = "2024-04-26"
        const val SATURDAY = "2024-04-27"
        const val SUNDAY = "2024-04-28"
    }
}
