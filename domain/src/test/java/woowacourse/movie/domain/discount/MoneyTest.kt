package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `금액은 음수일 수 없다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Money(-5)
        }.withMessage("[ERROR] 금액은 음수일 수 없습니다.")
    }

    @Test
    fun `돈을 다른 돈으로 더하면 두 돈의 금액의 합의 금액을 가진 돈을 반환한다`() {
        val oneMoney = Money(13_000)
        val otherMoney = Money(2_000)

        val actual = oneMoney + otherMoney

        val expected = Money(15_000)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `기존 금액보다 큰 금액의 돈을 빼면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Money(5000) - Money(10000)
        }.withMessage("[ERROR] 금액은 음수일 수 없습니다.")
    }

    @Test
    fun `돈을 다른 돈으로 빼면 돈의 금액 차이만큼의 금액의 돈을 반환한다`() {
        val oneMoney = Money(13_000)
        val otherMoney = Money(2_000)

        val actual = oneMoney - otherMoney

        val expected = Money(11_000)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈을 0으로 나누면 에러가 발생한다`() {
        assertThatThrownBy { Money(5000) / 0 }.isInstanceOf(ArithmeticException::class.java)
    }

    @Test
    fun `돈을 숫자로 나누면 돈의 금액에서 숫자를 나눈 금액의 돈을 반환한다`() {
        val money = Money(13_000)
        val number = 10

        val actual = money / number

        val expected = Money(1_300)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈을 숫자로 곱하면 돈의 금액에 숫자를 곱한 금액의 돈을 반환한다`() {
        val money = Money(13_000)
        val number = 10

        val actual = money * number

        val expected = Money(130_000)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈은 금액으로 비교된다`() {
        val oneMoney = Money(10_000)
        val otherMoney = Money(20_000)

        assertThat(oneMoney.compareTo(otherMoney)).isEqualTo(oneMoney.amount.compareTo(otherMoney.amount))
    }
}
