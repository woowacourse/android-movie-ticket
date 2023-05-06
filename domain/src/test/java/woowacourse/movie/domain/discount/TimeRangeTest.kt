package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.time.LocalTime

internal class TimeRangeTest {

    @Test
    fun `시간 범위를 생성할 때 시작 시각이 끝 시각 이후라면 에러가 발생한다`() {
        val startTime = LocalTime.of(12, 0, 0)
        val endTime = LocalTime.of(9, 0, 0)

        assertThatIllegalArgumentException()
            .isThrownBy { TimeRange(startTime, endTime) }
            .withMessage("시작 시간은 마지막 시간보다 빠르거나 같아야 합니다.")
    }

    @Test
    fun `특정 시각이 시간 범위의 시작 시각과 끝 시각 사이라면 시간 범위는 그 시각을 포함한다`() {
        val startTime = LocalTime.of(10, 0, 0)
        val endTime = LocalTime.of(16, 0, 0)
        val timeRange = TimeRange(startTime, endTime)
        val middleTime = LocalTime.of(16, 0, 0)

        val actual = middleTime in timeRange

        assertThat(actual).isTrue
    }
}
