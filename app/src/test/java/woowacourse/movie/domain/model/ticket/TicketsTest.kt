package woowacourse.movie.domain.model.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatRate

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
