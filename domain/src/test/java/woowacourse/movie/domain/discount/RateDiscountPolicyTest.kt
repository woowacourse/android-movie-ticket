package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class RateDiscountPolicyTest {

    @Test
    fun `비율 할인 정책은 할인 조건을 만족하면 원래 금액의 특정 비율만큼의 금액을 할인한다`() {
        val discountCondition = DayDiscountCondition(listOf(1))
        val discountPolicy = RateDiscountPolicy(listOf(discountCondition), 1, 20)

        val actual =
            discountPolicy.getDiscountedFee(LocalDateTime.of(2024, 3, 1, 0, 0), Money(10_000))

        assertThat(actual).isEqualTo(Money(8_000))
    }
}
