package woowacourse.movie.domain.price

import org.junit.Assert
import org.junit.Test

class TicketCountTest {
    @Test
    fun `티켓수가 1이상이라면 입력된 숫자로 상태값이 설정된다`() {
        Assert.assertEquals(woowacourse.movie.domain.price.TicketCount(3).value, 3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `티켓 수를 1이하로 초기화할수 없다`() {
        woowacourse.movie.domain.price.TicketCount(0)
    }

    @Test
    fun `티켓수를 1이하로 변경하려하면 1로 상태값이 설정된다`() {
        val initialTicketCount = woowacourse.movie.domain.price.TicketCount(1)
        initialTicketCount.value = -3
        Assert.assertEquals(initialTicketCount.value, 1)
    }
}
