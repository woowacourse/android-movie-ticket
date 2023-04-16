package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.movie.domain.model.Money

class MoneyTest {

    @Test(expected = IllegalArgumentException::class)
    fun `돈은 음수일 수 없다`() {
        // given
        val amount = -1

        // then
        Money(amount)
    }

    @Test
    fun `돈 10000원을 10프로 줄이면 9000원이다`() {
        // given
        val amount = 10000
        val expected = Money(9000)

        // when
        val actual = Money(amount).reduceMoneyWithRate(0.1)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈 10000원을 2000원 줄이면 8000원이다`() {
        // given
        val amount = 10000
        val expected = Money(8000)

        // when
        val actual = Money(amount).reduceMoneyWithAmount(2000)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈 10000원을 3배 하면 30000원이다`() {
        // given
        val amount = 10000
        val expected = Money(30000)

        // when
        val actual = Money(amount).multiplyMoneyWithCount(3)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
