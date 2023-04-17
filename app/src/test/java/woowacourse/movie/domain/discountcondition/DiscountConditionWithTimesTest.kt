package woowacourse.movie.domain.discountcondition

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithTimes
import java.time.LocalDateTime

class DiscountConditionWithTimesTest {
    @Test
    fun `할인 조건이 9, 10, 11시 일때 10시를 넣으면 True 를 반환 한다 `() {
        // given
        val days = listOf(9, 10, 11)
        val discountCondition = DiscountConditionWithTimes(days)

        // when
        val actual = discountCondition.isDiscount(LocalDateTime.of(2023, 4, 1, 10, 0))

        // then
        Assertions.assertThat(actual).isTrue
    }

    @Test
    fun `할인 조건이 9, 10, 11시 일떄 16시를 넣으면 False 를 반환 한다 `() {
        // given
        val days = listOf(9, 10, 11)
        val discountCondition = DiscountConditionWithTimes(days)

        // when
        val actual =
            discountCondition.isDiscount(LocalDateTime.of(2023, 4, 1, 16, 0))

        // then
        Assertions.assertThat(actual).isFalse
    }
}
