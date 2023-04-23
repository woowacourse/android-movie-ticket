package woowacourse.movie.domain

import org.junit.Test
import java.time.LocalDateTime

class DayDiscountConditionTest {

    @Test
    fun `만약 예매 날짜가 할인 날짜에 포함된다면 할인 조건을 만족한다`() {
        val dayDiscountCondition = DayDiscountCondition(listOf(1, 2, 3))
        val selectedDateTime = LocalDateTime.of(2023, 3, 1, 0, 0)

        assert(dayDiscountCondition.isSatisfiedBy(selectedDateTime))
    }
}
