package woowacourse.movie.domain.discountpolicy

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.discount.discountpolicy.LateNightTimeDiscountPolicy
import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class LateNightTimeDiscountPolicyTest {
    @Test
    fun `20시면 야간 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 20, 0)
        val price = Money(13000)
        val expected = Money(11000)

        // when
        val actual = LateNightTimeDiscountPolicy(dateTime, 2000).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21시면 야간 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 21, 0)
        val price = Money(13000)
        val expected = Money(11000)
        // when
        val actual = LateNightTimeDiscountPolicy(dateTime, 2000).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `22시면 야간 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 22, 0)
        val price = Money(13000)
        val expected = Money(11000)
        // when
        val actual = LateNightTimeDiscountPolicy(dateTime, 2000).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `23시면 야간 할인을 적용하여 2000원 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 23, 0)
        val price = Money(13000)
        val expected = Money(11000)
        // when
        val actual = LateNightTimeDiscountPolicy(dateTime, 2000).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `20~23시가 아니면 야간 할인이 적용되지 않는다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 1, 19, 0)
        val price = Money(13000)
        val expected = Money(13000)
        // when
        val actual = LateNightTimeDiscountPolicy(dateTime, 2000).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
