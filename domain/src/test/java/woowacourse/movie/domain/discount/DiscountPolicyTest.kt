package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class DiscountPolicyTest {

    @Test
    fun `할인 조건을 만족하지 않는다면 할인 적용된 금액은 원래 금액과 같다`() {
        val discountCondition = DayDiscountCondition(listOf(1))
        val discountPolicy = FixDiscountPolicy(listOf(discountCondition), 1, Money(10_000))
        val initFee = Money(5_000)

        val actual =
            discountPolicy.getDiscountedFee(LocalDateTime.of(2024, 3, 31, 0, 0), initFee)

        assertThat(actual).isEqualTo(initFee)
    }

    @Test
    fun `할인 조건을 만족할 때 할인 금액이 원래 금액보다 크다면 할인이 적용된 금액은 0원이다`() {
        val discountCondition = DayDiscountCondition(listOf(1))
        val discountPolicy = FixDiscountPolicy(listOf(discountCondition), 1, Money(10_000))

        val actual =
            discountPolicy.getDiscountedFee(LocalDateTime.of(2024, 3, 1, 0, 0), Money(5_000))

        assertThat(actual).isEqualTo(Money(0))
    }

    @Test
    fun `할인 조건을 만족할 때 할인 금액이 원래 금액보다 작거나 같아면 할인이 적용된 금액은 원래 금액에서 할인 금액을 뺀 금액이다`() {
        val discountCondition = DayDiscountCondition(listOf(1))
        val discountPolicy = FixDiscountPolicy(listOf(discountCondition), 1, Money(5_000))

        val actual =
            discountPolicy.getDiscountedFee(LocalDateTime.of(2024, 3, 1, 0, 0), Money(10_000))

        assertThat(actual).isEqualTo(Money(5_000))
    }
}
