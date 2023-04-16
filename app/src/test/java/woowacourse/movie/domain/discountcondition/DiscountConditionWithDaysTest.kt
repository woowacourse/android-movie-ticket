package woowacourse.movie.domain.discountcondition

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithDays
import java.time.LocalDateTime

class DiscountConditionWithDaysTest {

    @Test
    fun `할인 조건이 5, 15, 25일 일때 5일을 넣으면 True 를 반환 한다 `() {
        // given
        val days = listOf(5, 15, 25)
        val discountCondition = DiscountConditionWithDays(days)

        // when
        val actual = discountCondition.isDiscount(LocalDateTime.of(2023, 10, 5, 0, 0))

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `할인 조건이 5, 15, 25일 일때 7일을 넣으면 False 를 반환 한다 `() {
        // given
        val days = listOf(5, 15, 25)
        val discountCondition = DiscountConditionWithDays(days)

        // when
        val actual =
            discountCondition.isDiscount(LocalDateTime.of(2023, 4, 7, 0, 0))

        // then
        assertThat(actual).isFalse
    }
}
