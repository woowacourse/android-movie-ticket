package woowacourse.movie.domain.discountpolicy

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.discount.discountpolicy.MovieDayTimeDiscountPolicy
import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class MovieDayTimeDiscountPolicyTest {
    @Test
    fun `10일이면 무비데이 할인을 적용하여 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 10, 0, 0)
        val price = Money(13000)
        val expected = Money(11700)
        // when
        val actual = MovieDayTimeDiscountPolicy(dateTime, 0.1).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `20일이면 무비데이 할인을 적용하여 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 20, 0, 0)
        val price = Money(13000)
        val expected = Money(11700)
        // when
        val actual = MovieDayTimeDiscountPolicy(dateTime, 0.1).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `30일이면 무비데이 할인을 적용하여 10프로 할인된다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 30, 0, 0)
        val price = Money(13000)
        val expected = Money(11700)
        // when
        val actual = MovieDayTimeDiscountPolicy(dateTime, 0.1).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `10,20,30일이 아니면 무비데이 할인이 적용되지 않는다`() {
        // given
        val dateTime = LocalDateTime.of(2023, 4, 11, 0, 0)
        val price = Money(13000)
        val expected = Money(13000)
        // when
        val actual = MovieDayTimeDiscountPolicy(dateTime, 0.1).discount(price)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
