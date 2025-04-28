package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.rules.RunningTimeRule
import woowacourse.movie.domain.rules.RunningTimeRuleImpl
import woowacourse.movie.fixture.DomainFixture
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RunningTimeRuleTest {
    private lateinit var runningTimeRule: RunningTimeRule

    @BeforeEach
    fun setUp() {
        runningTimeRule = RunningTimeRuleImpl()
    }

    @Test
    fun `주말에는 오전 9시부터 두 시간 간격으로 상영한다`() {
        val runningTimes =
            runningTimeRule.whenTargetDay(
                LocalDate.of(2025, 4, 5),
                LocalDateTime.of(2025, 4, 3, 9, 0, 0),
            )
        assertThat(runningTimes).isEqualTo(
            DomainFixture.weekEndRule,
        )
    }

    @Test
    fun `평일에는 오전 10시부터 두 시간 간격으로 상영한다`() {
        val runningTimes =
            runningTimeRule.whenTargetDay(
                LocalDate.of(2025, 4, 4),
                LocalDateTime.of(2025, 4, 3, 9, 0, 0),
            )
        assertThat(runningTimes).isEqualTo(
            DomainFixture.weekDayRule,
        )
    }

    @Test
    fun `현재 날짜와 예약 날짜가 같은 경우 현재 날짜 이후의 시간만 상영한다`() {
        val runningTimes =
            runningTimeRule.whenTargetDay(
                LocalDate.of(2025, 4, 3),
                LocalDateTime.of(2025, 4, 3, 15, 0, 0),
            )
        assertThat(runningTimes).isEqualTo(
            listOf(
                LocalTime.of(16, 0, 0),
                LocalTime.of(18, 0, 0),
                LocalTime.of(20, 0, 0),
                LocalTime.of(22, 0, 0),
                LocalTime.of(0, 0, 0),
            ),
        )
    }
}
