package woowacourse.movie.domain.model.tools

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.tools.Money

class MoneyTest {

    @Test
    fun `돈은 음수일 수 없다`() {
        // given
        val amount = -1

        // then
        assertThrows<IllegalArgumentException> { Money(amount) }
    }

    @Test
    fun `돈 10000원을 10프로 줄이면 9000원이다`() {
        // given
        val amount = 10000
        val expected = Money(9000)

        // when
        val actual = Money(amount) * 0.9

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈 10000원을 2000원 줄이면 8000원이다`() {
        // given
        val amount = 10000
        val expected = Money(8000)

        // when
        val actual = Money(amount) - 2000

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈 10000원을 3배 하면 30000원이다`() {
        // given
        val amount = 10000
        val expected = Money(30000)

        // when
        val actual = Money(amount) * 3

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
