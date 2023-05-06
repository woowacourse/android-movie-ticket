package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class FixDiscountPolicyTest {

    @Test
    fun `고정 할인 정책은 할인 조건을 만족하면 원래 금액에서 특정 금액을 할인한다`() {
        val discountCondition = DayDiscountCondition(listOf(1))
        val discountPolicy = FixDiscountPolicy(listOf(discountCondition), 1, Money(5_000))

        val actual =
            discountPolicy.getDiscountedFee(LocalDateTime.of(2024, 3, 1, 0, 0), Money(10_000))

        Assertions.assertThat(actual).isEqualTo(Money(5_000))
    }
}
