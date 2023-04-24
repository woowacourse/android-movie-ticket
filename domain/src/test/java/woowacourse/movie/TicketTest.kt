package woowacourse.movie

import com.woowacourse.domain.ticket.Ticket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class TicketTest {
    @ParameterizedTest
    @CsvSource("10000, 10000", "12000, 12000", "15000, 15000")
    fun `할인이 없을시 티켓의 정가를 반환한다`(ticketPrice: Int, discountedPrice: Int) {
        val actual = Ticket(ticketPrice).getCalculatedTicketPrice("2023.04.09", "19:00")
        assertThat(actual).isEqualTo(discountedPrice)
    }

    @ParameterizedTest
    @ValueSource(strings = ["2023.04.10", "2023.04.20", "2023.04.30"])
    fun `다른 할인 없이 무비데이라면 10퍼센트 할인한다`(date: String) {
        val actual = Ticket(12000).getCalculatedTicketPrice(date, "14:00")
        assertThat(actual).isEqualTo(10800)
    }

    @Test
    fun `다른 할인 없이 조조 시간이라면 2000원 할인한다`() {
        val actual = Ticket(10000).getCalculatedTicketPrice("2023.04.09", "10:00")
        assertThat(actual).isEqualTo(8000)
    }

    @Test
    fun `다른 할인 없이 심야 시간이라면 2000원 할인한다`() {
        val actual = Ticket(10000).getCalculatedTicketPrice("2023.04.09", "20:00")
        assertThat(actual).isEqualTo(8000)
    }

    @ParameterizedTest
    @CsvSource("10000, 7000", "12000, 8800", "15000, 11500")
    fun `무비데이 할인과 시간대 할인이 중복된다면 무비데이 할인이 먼저 계산된다`(ticketPrice: Int, discountedPrice: Int) {
        val actual = Ticket(ticketPrice).getCalculatedTicketPrice("2023.04.10", "10:00")
        assertThat(actual).isEqualTo(discountedPrice)
    }
}
