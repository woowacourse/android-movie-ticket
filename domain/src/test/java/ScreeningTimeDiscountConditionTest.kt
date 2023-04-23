package woowacourse.movie.domain

import org.junit.Test
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeDiscountConditionTest {

    @Test
    fun `만약 예매 시간이 할인 시간에 포함된다면 할인 조건을 만족한다`() {
        val screeningTimeDiscountCondition =
            ScreeningTimeDiscountCondition(listOf(TimeRange(LocalTime.MIN, LocalTime.NOON)))

        val selectedDateTime = LocalDateTime.of(2023, 3, 1, 0, 0)

        assert(screeningTimeDiscountCondition.isSatisfiedBy(selectedDateTime))
    }
}
