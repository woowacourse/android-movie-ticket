package woowacourse.movie.domain.discountpolicy

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.discount.discountpolicy.EarlyBirdTimeDiscountPolicy
import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class EarlyBirdTimeDiscountPolicyTest {
    @Test
    fun `9시면 조조 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 9, 0)
        val price = 13000
        val expected = 11000
        // when
        val actual = EarlyBirdTimeDiscountPolicy(dateTime, 2000).discount(Money(price))

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `10시면 조조 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 10, 0)
        val price = 13000
        val expected = 11000
        // when
        val actual = EarlyBirdTimeDiscountPolicy(dateTime, 2000).discount(Money(price))

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `11시면 조조 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 11, 0)
        val price = 13000
        val expected = 11000
        // when
        val actual = EarlyBirdTimeDiscountPolicy(dateTime, 2000).discount(Money(price))

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `9~11시가 아니면 조조 할인이 적용되지 않는다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 12, 0)
        val price = 13000
        val expected = 13000
        // when
        val actual = EarlyBirdTimeDiscountPolicy(dateTime, 2000).discount(Money(price))

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
