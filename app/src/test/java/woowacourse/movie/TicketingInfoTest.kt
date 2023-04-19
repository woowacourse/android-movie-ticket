package woowacourse.movie

import com.example.domain.model.Payment
import com.example.domain.model.TicketingInfo
import com.example.domain.model.price.Price
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class TicketingInfoTest {
    @Test
    fun `할인이 적용된 TicketingInfo를 얻을 수 있다`() {
        val actual = TicketingInfo(
            LocalDate.of(2023, 4, 30), LocalTime.of(9, 0),
            Price()
        ).price
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    private fun TicketingInfo(playingDate: LocalDate, playingTime: LocalTime, price: Price): TicketingInfo = TicketingInfo.of("해리포터와 마법사의 돌", playingDate, playingTime, 1, price, Payment.ON_SITE)
}
