package woowacourse.movie.domain.model.reservation.date

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalTime

class ScreeningTimeTest {
    @Test
    fun `주말에는 상영 시작 시간이 오전 9시부터이다`() {
        // when
        val isWeekend = true
        val screeningTime = ScreeningTime(100, isWeekend)

        // then
        assertThat(screeningTime.startTime.hour).isEqualTo(9)
        assertThat(screeningTime.startTime.minute).isEqualTo(0)
    }

    @Test
    fun `평일에는 상영 시작 시간이 오전 10시부터이다`() {
        // when
        val isWeekend = false
        val screeningTime = ScreeningTime(100, isWeekend)

        // then
        assertThat(screeningTime.startTime.hour).isEqualTo(10)
        assertThat(screeningTime.startTime.minute).isEqualTo(0)
    }

    @ParameterizedTest
    @CsvSource("9,30,true", "13,20,true", "23,00,false")
    fun `상영 시간을 변경할 수 있다`(
        changingHour: Int,
        changingMinute: Int,
        isWeekend: Boolean,
    ) {
        // given
        val screeningTime = ScreeningTime(100, isWeekend)

        // when
        screeningTime.changeStartTime(changingHour, changingMinute, isWeekend)

        // then
        assertThat(screeningTime.startTime).isEqualTo(LocalTime.of(changingHour, changingMinute))
    }

    @ParameterizedTest
    @CsvSource("0,0", "0,30", "9,0", "9,59")
    fun `평일의 상영 시간 범위를 벗어난 시간으로는 변경할 수 없다`(
        changingHour: Int,
        changingMinute: Int,
    ) {
        // given
        val isWeekend: Boolean = false
        val screeningTime = ScreeningTime(100, isWeekend)

        // when
        screeningTime.changeStartTime(changingHour, changingMinute, isWeekend)

        // then
        assertThat(screeningTime.startTime).isNotEqualTo(LocalTime.of(changingHour, changingMinute))
    }

    @ParameterizedTest
    @CsvSource("0,0", "0,30", "8,0", "8,59")
    fun `주말의 상영 시간 범위를 벗어난 시간으로는 변경할 수 없다`(
        changingHour: Int,
        changingMinute: Int,
    ) {
        // given
        val isWeekend: Boolean = true
        val screeningTime = ScreeningTime(100, isWeekend)

        // when
        screeningTime.changeStartTime(changingHour, changingMinute, isWeekend)

        // then
        assertThat(screeningTime.startTime).isNotEqualTo(LocalTime.of(changingHour, changingMinute))
    }

    @ParameterizedTest
    @CsvSource("100,10,40", "120,11,0", "158,11,38")
    fun `상영 종료 시간을 알 수 있다`(
        runningTime: Int,
        endHour: Int,
        endMinute: Int,
    ) {
        // given
        val screeningTime = ScreeningTime(runningTime, true)

        // when
        val actualEndTime = screeningTime.getEndTime()

        // then
        assertThat(actualEndTime).isEqualTo(LocalTime.of(endHour, endMinute))
    }
}
