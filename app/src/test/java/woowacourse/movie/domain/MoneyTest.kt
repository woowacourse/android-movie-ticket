package woowacourse.movie.domain

import org.junit.Assert
import org.junit.Test

class MoneyTest {
    @Test
    fun `금액은 음수일 수 없다`() {
        Assert.assertThrows("[ERROR] 금액은 음수일 수 없습니다.", IllegalArgumentException::class.java) {
            Money(-5)
        }
    }
}
