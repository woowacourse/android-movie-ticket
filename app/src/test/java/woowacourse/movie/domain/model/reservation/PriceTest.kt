package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PriceTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -1000, -123413413])
    fun `가격은 0원 이상이다`(value: Int) {
        assertThatThrownBy { Price(value) }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
