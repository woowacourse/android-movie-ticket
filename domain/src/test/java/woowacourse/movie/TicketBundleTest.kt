package woowacourse.movie

import com.woowacourse.domain.ticket.Ticket
import com.woowacourse.domain.ticket.TicketBundle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketBundleTest {
    @Test
    fun `티켓 가격의 총 합을 구한다`() {
        val actual = TicketBundle(
            3,
            listOf(Ticket(10000), Ticket(12000), Ticket(15000))
        ).calculateTotalPrice(
            "2023.04.10",
            "10:00"
        )
        assertThat(actual).isEqualTo(27300)
    }
}
