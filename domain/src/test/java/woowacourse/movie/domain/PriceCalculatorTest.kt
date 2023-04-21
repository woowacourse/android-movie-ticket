package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.domain.policy.MorningPolicy
import woowacourse.movie.domain.policy.MovieDayPolicy
import woowacourse.movie.domain.policy.NightPolicy
import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.price.PriceCalculator
import java.time.LocalDateTime

class PriceCalculatorTest {
    @Test
    fun `조조에 해당하지만 무비데이면 무비데이 할인이 선적용된다`() {
        val policies = listOf(
            MovieDayPolicy(),
            MorningPolicy(),
        )
        val calculator = PriceCalculator(policies)
        val actual = calculator.calculate(Price(13000), LocalDateTime.of(2023, 4, 30, 9, 0))
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    @Test
    fun `야간에 해당하지만 무비데이면 무비데이 할인이 선적용된다`() {
        val policies = listOf(
            MovieDayPolicy(),
            NightPolicy(),
        )
        val calculator = PriceCalculator(policies)
        val actual = calculator.calculate(Price(13000), LocalDateTime.of(2023, 4, 30, 21, 0))
        val expected = Price(9700)
        assertEquals(actual, expected)
    }
}
