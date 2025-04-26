package woowacourse.movie.domain.model.cinema.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.cinema.screen.Seat
import woowacourse.movie.domain.model.cinema.screen.SeatType
import java.time.LocalDateTime

class TicketBundleTest {
    private fun createTicket(
        title: String = "해리 포터",
        reservationDateTime: LocalDateTime = LocalDateTime.of(2025, 4, 27, 20, 0),
        seat: Seat,
        price: Int = 15_000,
    ): Ticket = Ticket(title, reservationDateTime, seat, price)

    @Test
    fun `TicketBundle 생성 시 정보들이 올바르게 설정된다`() {
        val tickets =
            listOf(
                createTicket(seat = Seat(1, 1, SeatType.S_CLASS)),
                createTicket(seat = Seat(1, 2, SeatType.S_CLASS)),
            )

        val ticketBundle = TicketBundle(tickets)

        assertAll(
            { assertThat(ticketBundle.title).isEqualTo("해리 포터") },
            { assertThat(ticketBundle.size).isEqualTo(2) },
            { assertThat(ticketBundle.dateTime).isEqualTo(LocalDateTime.of(2025, 4, 27, 20, 0)) },
            { assertThat(ticketBundle.totalPrice).isEqualTo(30_000) },
            {
                assertThat(ticketBundle.labels)
                    .containsExactly(
                        Seat(1, 1, SeatType.S_CLASS),
                        Seat(1, 2, SeatType.S_CLASS),
                    )
            },
        )
    }

    @Test
    fun `티켓이 없는 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            TicketBundle(emptyList())
        }
    }

    @Test
    fun `영화 제목이 다른 티켓이 섞여있으면 예외가 발생한다`() {
        val tickets =
            listOf(
                createTicket(title = "해리 포터", seat = Seat(1, 1, SeatType.S_CLASS)),
                createTicket(title = "인셉션", seat = Seat(1, 2, SeatType.S_CLASS)),
            )

        assertThrows<IllegalArgumentException> {
            TicketBundle(tickets)
        }
    }
}
