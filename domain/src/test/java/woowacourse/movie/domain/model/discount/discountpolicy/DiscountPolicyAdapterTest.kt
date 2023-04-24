package woowacourse.movie.domain.model.discount.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.discount.MovieDiscountPolicy
import woowacourse.movie.domain.model.tools.Money
import java.time.LocalDateTime

class DiscountPolicyAdapterTest {
    @Test
    fun `무비데이, 조조, 야간이 아니면 할인이 없다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 17, 0)
        val price = Money(13000)
        val expected = Money(13000)
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `조조 시간이면 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 9, 0)
        val price = Money(13000)
        val expected = price - 2000
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `야간 시간이면 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 20, 0)
        val price = Money(13000)
        val expected = price - 2000
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이면 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 10, 17, 0)
        val price = Money(13000)
        val expected = price * 0.9
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이 이면서 조조 시간이면 10프로 할인에 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 10, 10, 0)
        val price = Money(13000)
        val expected = price * 0.9 - 2000
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이 이면서 야간 시간이면 10프로 할인에 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 10, 10, 0)
        val price = Money(13000)
        val expected = price * 0.9 - 2000
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)

        // when
        val actual = discountPolicy.discount(price, dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
