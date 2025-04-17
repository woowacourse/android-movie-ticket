package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieSchedulerTest {
    private val movieScheduler = MovieScheduler()

    @Test
    fun `현재 날짜가 상영 시작일보다 빠른 경우 상영 시작일 기준으로 상영 가능 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(MAY_FIRST, MAY_THIRD)
        val currentDate = APRIL_THIRTIETH
        val expected: List<LocalDate> = listOf(MAY_FIRST, MAY_SECOND, MAY_THIRD)

        // when
        val actual = movieScheduler.reservableDates(screeningDate, currentDate)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜가 상영 시작일보다 느린 경우 현재 날짜 기준으로 상영 가능 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(APRIL_THIRTIETH, MAY_THIRD)
        val currentDate = MAY_FIRST
        val expected: List<LocalDate> = listOf(MAY_FIRST, MAY_SECOND, MAY_THIRD)

        // when
        val actual = movieScheduler.reservableDates(screeningDate, currentDate)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜와 선택된 날짜가 같고 평일인데, 현재 시간이 빠른 경우 현재 시간 기준으로 짝수 시간을 반환한다`() {
        // given
        val selectedDate = APRIL_THIRTIETH
        val currentTime = LocalDateTime.of(APRIL_THIRTIETH, LocalTime.of(20, 0))
        val expected: List<LocalTime> =
            listOf(
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )

        // when
        val actual = movieScheduler.reservableTimes(selectedDate, currentTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜와 선택된 날짜가 같고 주말인데, 현재 시간이 빠른 경우 현재 시간 기준으로 홀수 시간을 반환한다`() {
        // given
        val selectedDate = MAY_THIRD
        val currentTime = LocalDateTime.of(MAY_THIRD, LocalTime.of(20, 0))
        val expected: List<LocalTime> =
            listOf(
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        // when
        val actual = movieScheduler.reservableTimes(selectedDate, currentTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜와 선택된 날짜가 다르고 평일이면 상영 시간 기준으로 짝수 시간을 반환한다`() {
        // given
        val selectedDate = MAY_FIRST
        val currentTime = LocalDateTime.of(APRIL_THIRTIETH, LocalTime.of(20, 0))
        val expected: List<LocalTime> =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )

        // when
        val actual = movieScheduler.reservableTimes(selectedDate, currentTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜와 선택된 날짜가 다르고 주말이면 상영 시간 기준으로 홀수 시간을 반환한다`() {
        // given
        val selectedDate = MAY_THIRD
        val currentTime = LocalDateTime.of(APRIL_THIRTIETH, LocalTime.of(20, 0))
        val expected: List<LocalTime> =
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

        // when
        val actual = movieScheduler.reservableTimes(selectedDate, currentTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜와 선택된 날짜가 같고 마지막 상영 시간을 초과했을 때 빈 리스트를 반환한다`() {
        // given
        val selectedDate = APRIL_THIRTIETH
        val currentTime = LocalDateTime.of(APRIL_THIRTIETH, LocalTime.of(23, 0))
        val expected: List<LocalTime> = listOf()

        // when
        val actual = movieScheduler.reservableTimes(selectedDate, currentTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
