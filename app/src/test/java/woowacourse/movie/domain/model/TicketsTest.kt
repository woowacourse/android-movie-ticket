package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `예매 개수가 총 가격을 계산한다`() {
        // given
        val tickets = Tickets(listOf(Ticket(Seat(Row(0), Column(0), SeatRate.S)), Ticket(Seat(Row(0), Column(0), SeatRate.B))))

        // when
        val actual = tickets.totalPrice()

        // then
        assertThat(actual).isEqualTo(2_5000)
    }
}
