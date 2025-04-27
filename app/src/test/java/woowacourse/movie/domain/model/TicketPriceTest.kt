package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketPriceTest {
    @Test
    fun `RANK_S는 15,000원 이다`() {
        // given
        val seatType = SeatType.RANK_S

        // when
        val ticketPrice = TicketPrice.from(seatType)

        // then
        assertThat(ticketPrice.value).isEqualTo(15_000)
    }

    @Test
    fun `RANK_A는 12,000원 이다`() {
        // given
        val seatType = SeatType.RANK_A

        // when
        val ticketPrice = TicketPrice.from(seatType)

        // then
        assertThat(ticketPrice.value).isEqualTo(12_000)
    }

    @Test
    fun `RANK_B는 10,000원 이다`() {
        // given
        val seatType = SeatType.RANK_B

        // when
        val ticketPrice = TicketPrice.from(seatType)

        // then
        assertThat(ticketPrice.value).isEqualTo(10_000)
    }
}
