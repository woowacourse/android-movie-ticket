package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class DayDiscountConditionTest {

    @Test
    fun `만약 예매 날짜가 할인 날짜에 포함된다면 할인 조건을 만족한다`() {
        val dayDiscountCondition = DayDiscountCondition(listOf(1, 2, 3))
        val screeningDateTime: LocalDateTime = LocalDate.of(2024, 3, 1).atStartOfDay()

        assertThat(dayDiscountCondition.isSatisfiedBy(screeningDateTime)).isTrue
    }

    @Test
    fun `만약 예매 날짜가 할인 날짜에 포함되지 않는다면 할인 조건을 만족하지 않는다`() {
        val dayDiscountCondition = DayDiscountCondition(listOf(1, 2, 3))
        val screeningDateTime: LocalDateTime = LocalDate.of(2024, 3, 4).atStartOfDay()

        assertThat(dayDiscountCondition.isSatisfiedBy(screeningDateTime)).isFalse
    }
}
