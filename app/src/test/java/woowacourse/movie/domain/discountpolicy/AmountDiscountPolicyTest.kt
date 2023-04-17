package woowacourse.movie.domain.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithTimes
import woowacourse.movie.domain.discount.discountpolicy.AmountDiscountPolicy
import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class AmountDiscountPolicyTest {
    private val discountTimes = listOf(9, 10, 11, 20, 21, 22, 23)

    @Test
    fun `9시면 할인 시간이라서 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 9, 0)
        val price = Money(13000)
        val expected = Money(11000)

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(DiscountConditionWithTimes(discountTimes)),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `22시면 할인 시간이라서 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 22, 0)
        val price = Money(13000)
        val expected = Money(11000)

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(DiscountConditionWithTimes(discountTimes)),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `15시면 할인 시간이 아니라서 할인되지 않는다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 15, 0)
        val price = Money(13000)
        val expected = Money(13000)

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(
                DiscountConditionWithTimes(discountTimes),
            ),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
