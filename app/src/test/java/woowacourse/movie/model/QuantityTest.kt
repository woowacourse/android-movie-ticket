package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class QuantityTest {
    @Test
    fun `예매 수량의 기본 값은 0이다`() {
        val quantity = Quantity()
        val result = quantity.value
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `영화의 예매 수량을 1장 추가할 수 있다`() {
        val quantity = Quantity(0)
        quantity.increase()
        val result = quantity.value
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `영화의 예매 수량을 1장 감소시킬 수 있다`() {
        val quantity = Quantity(1)
        quantity.decrease()
        val result = quantity.value
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `영화의 예매 수량이 0보다 작으면 더 감소하지 않는다`() {
        val quantity = Quantity(0)
        quantity.decrease()
        val result = quantity.value
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `영화의 예매 수량이 0보다 작으면 예외를 던진다`() {
        assertThrows(IllegalArgumentException::class.java) { Quantity(-1) }
    }
}
