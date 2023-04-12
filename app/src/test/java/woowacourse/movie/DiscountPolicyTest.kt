package woowacourse.movie

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class DiscountPolicyTest {
    @Test
    fun `정상 요금이 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 9)
        val localTime = LocalTime.of(11, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 10000)
    }

    @Test
    fun `무비 데이 할인이 적용 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 10)
        val localTime = LocalTime.of(11, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 9000)
    }

    @Test
    fun `무비 데이랑 조조 할인이 적용 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 10)
        val localTime = LocalTime.of(10, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 7000)
    }

    @Test
    fun `무비 데이랑 야간 할인이 적용 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 10)
        val localTime = LocalTime.of(21, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 7000)
    }

    @Test
    fun `조조 할인만 적용 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 1)
        val localTime = LocalTime.of(10, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 8000)
    }

    @Test
    fun `야간 할인만 적용 된다`() {
        // given
        val localDate = LocalDate.of(2020, 1, 1)
        val localTime = LocalTime.of(10, 0)

        // when
        val discountPolicy = DiscountPolicy.of(localDate, localTime)

        // then
        assertEquals(discountPolicy(10000), 8000)
    }
}
