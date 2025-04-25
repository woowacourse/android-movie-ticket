package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.movie.ScreeningPeriod
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningPeriodTest {
    private lateinit var screeningPeriod: ScreeningPeriod

    @BeforeEach
    fun setUp() {
        screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 3))
    }

    @Test
    fun `상영 기간은 지정한 시작 날짜와 종료 날짜로 생성된다`() {
        val startDate = LocalDate.of(2025, 4, 1)
        val endDate = LocalDate.of(2025, 4, 3)
        val screeningPeriod = ScreeningPeriod(startDate, endDate)

        val startDateExpected = LocalDate.of(2025, 4, 1)
        val endDateExpected = LocalDate.of(2025, 4, 3)

        assertAll(
            { assertThat(screeningPeriod.startDate).isEqualTo(startDateExpected) },
            { assertThat(screeningPeriod.endDate).isEqualTo(endDateExpected) },
        )
    }

    @Test
    fun `상영 기간은 종료 날짜가 시작 날짜보다 이전이면 예외를 던진다`() {
        val invalidStart = LocalDate.of(2025, 5, 1)
        val invalidEnd = LocalDate.of(2025, 4, 1)

        assertThrows<IllegalArgumentException> {
            ScreeningPeriod(invalidStart, invalidEnd)
        }
    }

    @Test
    fun `상영 기간내의 날짜 목록을 반환한다`() {
        val now = LocalDate.of(2025, 4, 2)
        val availableDates = screeningPeriod.getAvailableDates(now)

        assertThat(availableDates).containsExactly(
            LocalDate.of(2025, 4, 2),
            LocalDate.of(2025, 4, 3),
        )
    }

    @Test
    fun `주어진 날짜에 대해 예매 가능한 상영 시간을 반환한다`() {
        val targetDate = LocalDate.of(2025, 4, 1)
        val now = LocalDateTime.of(2025, 4, 1, 19, 30)
        val availableTimes = screeningPeriod.getAvailableTimesFor(now, targetDate)

        assertThat(availableTimes).containsExactly(
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
        )
    }

    @Test
    fun `평일은 10시부터 상영 시간이 시작된다`() {
        val targetDate = LocalDate.of(2025, 4, 3)
        val now = LocalDateTime.of(2025, 4, 2, 0, 0)

        val availableTimes = screeningPeriod.getAvailableTimesFor(now, targetDate)

        assertThat(availableTimes).containsExactlyElementsOf(
            everyTwoHoursFrom(LocalTime.of(10, 0), 7),
        )
    }

    @Test
    fun `토요일은 9시부터 상영 시간이 시작된다`() {
        val targetDate = LocalDate.of(2025, 4, 5)
        val now = LocalDateTime.of(2025, 4, 4, 0, 0)
        val availableTimes = screeningPeriod.getAvailableTimesFor(now, targetDate)

        assertThat(availableTimes).containsExactlyElementsOf(
            everyTwoHoursFrom(LocalTime.of(9, 0), 8),
        )
    }

    @Test
    fun `일요일은 9시부터 상영 시간이 시작된다`() {
        val targetDate = LocalDate.of(2025, 4, 6)
        val now = LocalDateTime.of(2025, 4, 4, 0, 0)
        val availableTimes = screeningPeriod.getAvailableTimesFor(now, targetDate)

        assertThat(availableTimes).containsExactlyElementsOf(
            everyTwoHoursFrom(LocalTime.of(9, 0), 8),
        )
    }

    private fun everyTwoHoursFrom(
        start: LocalTime,
        count: Int,
    ): List<LocalTime> = (0 until count).map { start.plusHours(it * 2L) }
}
