package woowacourse.movie.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class ScreeningRangeTest {

    @Test
    fun `상영 기간의 시작 날짜가 마지막 날짜 이후라면 에러가 발생한다`() {
        val startDate = LocalDate.of(2021, 3, 31)
        val endDate = LocalDate.of(2021, 3, 1)

        assertThatIllegalArgumentException()
            .isThrownBy { ScreeningRange(startDate, endDate) }
            .withMessage("시작 날짜는 마지막 날짜 이후일 수 없습니다.")
    }
}
