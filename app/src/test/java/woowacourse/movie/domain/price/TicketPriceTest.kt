package woowacourse.movie.domain.price

import org.junit.Test

class TicketPriceTest {
    @Test(expected = IllegalArgumentException::class)
    fun `티켓 가격은 음수가 될수 없다`() {
        woowacourse.movie.domain.price.TicketCount(-1)
    }
}
