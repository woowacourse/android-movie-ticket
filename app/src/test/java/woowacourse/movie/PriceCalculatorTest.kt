package woowacourse.movie

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.model.Price
import woowacourse.movie.model.PriceCalculator
import java.time.LocalDate
import java.time.LocalTime

class PriceCalculatorTest {
    @Test
    fun `무비데이와 조조가 겹치면 무비데이 할인이 선적용된 가격이 계산된다`() {
        val actual =
            PriceCalculator.calculate(Price(), LocalDate.of(2023, 3, 10), LocalTime.of(10, 0))
        val expected = Price(9700)
        assertEquals(actual, expected)
    }
}
