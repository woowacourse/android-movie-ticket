package woowacourse.movie

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import woowacourse.movie.domain.DiscountCalculator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DiscountCalculatorTest {
    @Test
    fun `무비데이면 10% 할인을 받는다`() {
        val count = 1
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(12, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(count, dateTime)

        val expected = 11700

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 시간대가 조조면 2000원 할인을 받는다`() {
        val count = 1
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(count, dateTime)

        val expected = 11000

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 시간대가 야간이면 2000원 할인을 받는다`() {
        val count = 1
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(22, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(count, dateTime)

        val expected = 11000

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이이고 조조면 10%를 할인 받고 추가로 2000원 할인을 더 받는다`() {
        val count = 1
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(count, dateTime)

        val expected = 9700

        assertThat(actual).isEqualTo(expected)
    }
}
