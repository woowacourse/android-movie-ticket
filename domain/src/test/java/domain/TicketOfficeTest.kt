package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketOfficeTest {

    @Test
    internal fun `티켓을 추가할 수 있다`() {
        // given
        val ticketOffice = TicketOffice(tickets = Tickets(listOf()), peopleCount = 3)
        // when
        ticketOffice.addTicket(makeTestTicket(3, 3))
        ticketOffice.addTicket(makeTestTicket(3, 4))
        val actual = ticketOffice.tickets.list.size
        // then
        val expected = 2
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `티켓을 삭제할 수 있다`() {
        // given
        val ticketOffice = TicketOffice(
            tickets = Tickets(
                listOf(
                    makeTestTicket(3, 3), makeTestTicket(3, 4)
                ),
            ), peopleCount = 3
        )
        // when
        ticketOffice.deleteTicket(makeTestTicket(3, 4))
        val actual = ticketOffice.tickets.list.size
        // then
        val expected = 1
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `티켓 개수가 사람 수보다 적으면 true 이다`() {
        // given
        val ticketOffice = TicketOffice(
            tickets = Tickets(
                listOf(
                    makeTestTicket(3, 3),
                    makeTestTicket(3, 4)
                )
            ), peopleCount = 3
        )
        // when
        val actual = ticketOffice.isAvailableAddTicket()
        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `티켓 개수가 사람 수와 같으면 false 이다`() {
        // given
        val ticketOffice = TicketOffice(
            tickets = Tickets(
                listOf(
                    makeTestTicket(3, 3),
                    makeTestTicket(3, 4),
                    makeTestTicket(4, 4)
                )
            ), peopleCount = 3
        )
        // when
        val actual = ticketOffice.isAvailableAddTicket()
        // then
        assertThat(actual).isFalse
    }

    @Test
    internal fun `티켓 개수가 사람 수보다 많으면 false 이다`() {
        // given
        val ticketOffice = TicketOffice(
            tickets = Tickets(
                listOf(
                    makeTestTicket(3, 3),
                    makeTestTicket(3, 4),
                    makeTestTicket(4, 4),
                    makeTestTicket(2, 4)
                )
            ), peopleCount = 3
        )
        // when
        val actual = ticketOffice.isAvailableAddTicket()
        // then
        assertThat(actual).isFalse
    }

    private fun makeTestTicket(row: Int, col: Int): Ticket {
        val ticketOffice = TicketOffice(tickets = Tickets(listOf()), peopleCount = 3)
        val date = LocalDate.of(2023, 4, 20)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        return ticketOffice.generateTicket(dateTime, row, col)
    }
}
