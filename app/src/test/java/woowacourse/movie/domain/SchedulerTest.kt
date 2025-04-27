package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.schedule.Scheduler
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerTest {
    private val scheduler = Scheduler()

    @Test
    fun `상영 시작일이 오늘보다 빠르면 오늘부터 종료일까지 반환한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 1)
        val endDate = LocalDate.of(2025, 4, 10)
        val now = LocalDateTime.of(2025, 4, 5, 0, 0)

        // when
        val actual = scheduler.getScreeningDates(startDate, endDate, now)
        val expected =
            listOf(
                LocalDate.of(2025, 4, 5),
                LocalDate.of(2025, 4, 6),
                LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 8),
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 10),
            )

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `상영 시작일이 오늘이거나 오늘보다 늦으면 상영 기간 전체를 반환한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 2)
        val endDate = LocalDate.of(2025, 4, 10)
        val now = LocalDateTime.of(2025, 4, 1, 0, 0)

        // when
        val actual = scheduler.getScreeningDates(startDate, endDate, now)
        val expected =
            listOf(
                LocalDate.of(2025, 4, 2),
                LocalDate.of(2025, 4, 3),
                LocalDate.of(2025, 4, 4),
                LocalDate.of(2025, 4, 5),
                LocalDate.of(2025, 4, 6),
                LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 8),
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 10),
            )

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘 예매 가능한 시간이 없으면 오늘을 제외한 상영 기간을 반환한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 5)
        val endDate = LocalDate.of(2025, 4, 10)
        val now = LocalDateTime.of(2025, 4, 5, 23, 0)

        // when
        val actual = scheduler.getScreeningDates(startDate, endDate, now)
        val expected =
            listOf(
                LocalDate.of(2025, 4, 6),
                LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 8),
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 10),
            )

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선택한 상영일이 오늘이 아니고 주중이면 10시부터 24시까지 2시간 간격으로 상영 시간을 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 14)
        val now = LocalDateTime.of(2025, 4, 12, 0, 0, 0)

        // when
        val actual = scheduler.getShowtimes(selectedDate, now)
        val expected = (10 until 24 step 2).map { hour -> LocalTime.of(hour, 0) }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선택한 상영일이 오늘이 아니고 주말이면 9시부터 24시까지 2시간 간격으로 상영 시간을 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 13)
        val now = LocalDateTime.of(2025, 4, 12, 0, 0, 0)

        // when
        val actual = scheduler.getShowtimes(selectedDate, now)
        val expected = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0) }

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선택한 상영일이 오늘이고 주중이면 현재 시간과 10시 중 더 늦은 시간부터 24시까지 2시간 간격으로 상영 시간을 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 14)
        val now = LocalDateTime.of(2025, 4, 14, 13, 30, 0)

        // when
        val actual = scheduler.getShowtimes(selectedDate, now)
        val expected = (14 until 24 step 2).map { hour -> LocalTime.of(hour, 0) }

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선택한 상영일이 오늘이고 주말이면 현재 시간과 9시 중 더 늦은 시간부터 24시까지 2시간 간격으로 상영 시간을 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 13)
        val now = LocalDateTime.of(2025, 4, 13, 13, 30, 0)

        // when
        val actual = scheduler.getShowtimes(selectedDate, now)
        val expected = (15 until 24 step 2).map { hour -> LocalTime.of(hour, 0) }

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
