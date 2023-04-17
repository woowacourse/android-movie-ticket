package woowacourse.movie.domain.discountpolicy

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithDays
import woowacourse.movie.domain.discount.discountpolicy.RateDiscountPolicy
import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class RateDiscountPolicyTest {

    private val discountDays = listOf(10, 20, 30)

    @Test
    fun `10일이면 할인 날짜라서 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 10, 0, 0)
        val price = Money(13000)
        val expected = Money(11700)

        val rateDiscountPolicy = RateDiscountPolicy(
            listOf(DiscountConditionWithDays(discountDays)),
            0.1,
        )

        // when
        val actual = rateDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `15일이면 할인 날짜가 아니라서 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 15, 0, 0)
        val price = Money(13000)
        val expected = Money(13000)
        val movieDays = listOf(10, 20, 30)

        val rateDiscountPolicy = RateDiscountPolicy(
            listOf(DiscountConditionWithDays(discountDays)),
            0.1,
        )

        // when
        val actual = rateDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
