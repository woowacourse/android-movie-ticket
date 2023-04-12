package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class BookingPriceTest {
    @Test
    fun `인원이 2명일 경우 예매 금액은 26000원이다`() {
        val count = PeopleCount(2)
        val price = BookingPrice.of(count.count)
        assertEquals(26000, price.price)
    }
}
