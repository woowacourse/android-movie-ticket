package woowacourse.movie

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.TicketingInfo
import java.time.LocalDate
import java.time.LocalTime

class TicketingInfoTest {
    @Test
    fun `조조에 해당하지만 무비데이면 무비데이 할인이 선적용된다`() {
        val actual = TicketingInfo(LocalDate.of(2023, 4, 30), LocalTime.of(9, 0), Price()).price
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    private fun TicketingInfo(playingDate: LocalDate, playingTime: LocalTime, price: Price): TicketingInfo = TicketingInfo.of("해리포터와 마법사의 돌", playingDate, playingTime, 1, price, "현장")
}
