package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.policy.DiscountPolicy
import woowacourse.movie.domain.policy.MorningPolicy
import woowacourse.movie.domain.policy.MovieDayPolicy
import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.price.PriceCalculator
import woowacourse.movie.domain.system.PriceSystem
import woowacourse.movie.domain.system.SelectResult
import java.time.LocalDateTime

class PriceSystemTest {
    private lateinit var policies: List<DiscountPolicy>
    private lateinit var calculator: PriceCalculator

    @Before
    fun setUp() {
        policies = listOf(
            MovieDayPolicy(),
            MorningPolicy(),
        )
        calculator = PriceCalculator(policies)
    }

    @Test
    fun `선택 성공 시 더한 가격을 반환한다`() {
        val priceSystem = PriceSystem(calculator, LocalDateTime.of(2023, 4, 30, 9, 0))
        val actual =
            priceSystem.getCurrentPrice(Price(10000), SelectResult.Success.Selection(Price(13000), false))
        val expected = Price(19700)
        assertEquals(actual, expected)
    }

    @Test
    fun `선택 해제 시 뺀 가격을 반환한다`() {
        val priceSystem = PriceSystem(calculator, LocalDateTime.of(2023, 4, 30, 9, 0))
        val actual =
            priceSystem.getCurrentPrice(Price(9700), SelectResult.Success.Deselection(Price(13000)))
        val expected = Price(0)
        assertEquals(actual, expected)
    }

    @Test
    fun `잘못된 영역 입력 시 기존 가격 그대로를 반환한다`() {
        val priceSystem = PriceSystem(calculator, LocalDateTime.of(2023, 4, 30, 9, 0))
        val actual =
            priceSystem.getCurrentPrice(Price(9700), SelectResult.WrongInput)
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    @Test
    fun `이미 최대 좌석일 시 기존 가격 그대로를 반환한다`() {
        val priceSystem = PriceSystem(calculator, LocalDateTime.of(2023, 4, 30, 9, 0))
        val actual =
            priceSystem.getCurrentPrice(Price(9700), SelectResult.MaxSelection)
        val expected = Price(9700)
        assertEquals(actual, expected)
    }
}
