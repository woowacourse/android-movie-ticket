package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test

class MoneyTest {
    @Test
    fun `금액은 음수일 수 없다`() {
        assertThrows("[ERROR] 금액은 음수일 수 없습니다.", IllegalArgumentException::class.java) {
            Money(-5)
        }
    }

    @Test
    fun `기존 금액보다 큰 금액의 돈을 빼면 에러가 발생한다`() {
        assertThrows("[ERROR] 금액은 음수일 수 없습니다.", IllegalArgumentException::class.java) {
            Money(5000) - Money(10000)
        }
    }

    @Test
    fun `돈을 0으로 나누면 에러가 발생한다`() {
        assertThrows(ArithmeticException::class.java) {
            Money(5000) / 0
        }
    }
}
