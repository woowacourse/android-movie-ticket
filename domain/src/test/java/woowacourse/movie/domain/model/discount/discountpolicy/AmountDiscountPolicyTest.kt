package woowacourse.movie.domain.model.discount.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.discount.discountcondition.DiscountConditionWithTimes
import woowacourse.movie.domain.model.tools.Money
import java.time.LocalDateTime

class AmountDiscountPolicyTest {
    private val earlyDiscountTimes = listOf(9, 10, 11)
    private val lateDiscountTimes = listOf(20, 21, 22, 23)

    @Test
    fun `9시면 조조 할인 시간이므로 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 9, 0)
        val price = Money(13000)
        val expected = price - 2000

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(DiscountConditionWithTimes(earlyDiscountTimes)),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `22시면 심야 할인 시간이므로 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 22, 0)
        val price = Money(13000)
        val expected = price - 2000

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(DiscountConditionWithTimes(lateDiscountTimes)),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `15시면 조조, 심야 시간이 아니므로 할인되지 않는다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 15, 0)
        val price = Money(13000)
        val expected = Money(13000)

        val amountDiscountPolicy = AmountDiscountPolicy(
            listOf(
                DiscountConditionWithTimes(earlyDiscountTimes),
                DiscountConditionWithTimes(lateDiscountTimes),
            ),
            2000,
        )

        // when
        val actual = amountDiscountPolicy.getDiscountMoney(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
