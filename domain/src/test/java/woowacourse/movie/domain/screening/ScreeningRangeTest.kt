package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

internal class ScreeningRangeTest {

    @Test
    fun `상영 기간의 시작 날짜가 마지막 날짜 이후라면 에러가 발생한다`() {
        val startDate = LocalDate.of(2021, 3, 31)
        val endDate = LocalDate.of(2021, 3, 1)

        assertThatIllegalArgumentException()
            .isThrownBy { ScreeningRange(startDate, endDate) }
            .withMessage("시작 날짜는 마지막 날짜 이후일 수 없습니다.")
    }

    @Test
    fun `상영 기간 내의 날짜 중 주중은 오전 10시부터 자정까지 두 시간 간격으로 상영한다`() {
        val startDate = LocalDate.of(2021, 3, 1)
        val endDate = LocalDate.of(2021, 3, 31)
        val screeningRange = ScreeningRange(startDate, endDate)
        val weekday = LocalDate.of(2021, 3, 2)

        val screeningTimes = screeningRange.screeningDateTimes[weekday]

        assertThat(screeningTimes).containsExactly(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            LocalTime.of(0, 0),
        )
    }

    @Test
    fun `상영 기간 내의 날짜 중 주말은 오전 9시부터 자정까지 두 시간 간격으로 상영한다`() {
        val startDate = LocalDate.of(2021, 3, 1)
        val endDate = LocalDate.of(2021, 3, 31)
        val screeningRange = ScreeningRange(startDate, endDate)
        val weekend = LocalDate.of(2021, 3, 7)

        val screeningTimes = screeningRange.screeningDateTimes[weekend]

        assertThat(screeningTimes).containsExactly(
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalTime.of(23, 0)
        )
    }

    @Test
    fun `특정 시각에 상영 하는지 알 수 있다`() {
        val startDate = LocalDate.of(2021, 3, 1)
        val endDate = LocalDate.of(2021, 3, 31)
        val screeningRange = ScreeningRange(startDate, endDate)
        val notScreeningDateTime = LocalDateTime.of(2021, 4, 1, 0, 0)
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 10, 0)

        assertAll(
            { assertThat(screeningRange.screenOn(notScreeningDateTime)).isFalse },
            { assertThat(screeningRange.screenOn(screeningDateTime)).isTrue }
        )
    }
}
