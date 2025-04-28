package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseCountTest {
    private var purchaseCount: PurchaseCount = PurchaseCount(2)

    @BeforeEach
    fun setUp() {
        purchaseCount = PurchaseCount(2)
    }

    @Test
    fun `구매 개수가 증가한다`() {
        // given & when
        val expected = 3
        val actual = purchaseCount.increase().value

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `구매 개수가 감소한다`() {
        // given & when
        val expected = 1
        val actual = purchaseCount.decrease().value

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100])
    fun `구매 개수는 1보다 크거나 같아야 한다`(count: Int) {
        assertThatThrownBy { PurchaseCount(count) }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(ints = [1])
    fun `구매 개수가 2개 보다 작을 경우 감소 할 수 없다`(count: Int) {
        purchaseCount = PurchaseCount(count)
        // given & when
        val actual = purchaseCount.canDecrease()

        // then
        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [100, 2])
    fun `구매 개수가 2개 같거나 클 경우 감소 할 수 있다`(count: Int) {
        purchaseCount = PurchaseCount(count)
        // given & when
        val actual = purchaseCount.canDecrease()

        // then
        assertThat(actual).isTrue()
    }
}
