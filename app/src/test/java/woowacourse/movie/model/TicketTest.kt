package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalTime

class TicketTest {
    @Test
    fun `예매한 영화 제목은 비어있을 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Ticket(
                "",
                HeadCount(0),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )
        }

        assertDoesNotThrow {
            Ticket(
                "해리포터",
                HeadCount(0),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )
        }
    }

    @Test
    fun `예매 인원에 맞는 금액을 계산한다`() {
        val ticket =
            Ticket(
                "해리포터",
                HeadCount(0),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )
        val expected = 0

        val actual = ticket.amount

        assertEquals(expected, actual)
    }

    @Test
    fun `예매 인원과  좌석별 등급에 맞는 금액을 계산한다`() {
        val seats = Seats(listOf(Seat("A1", true), Seat("A2", true)))
        val ticket =
            Ticket("해리포터", HeadCount(2), LocalDate.of(2025, 4, 17), LocalTime.of(11, 0), seats)
        val expected = 20000

        val actual = ticket.amount

        assertEquals(expected, actual)
    }

    @Test
    fun `예매 인원과 좌석별 등급에 맞는 금액을 계산한다`() {
        val seats = Seats(listOf(Seat("A1", true), Seat("A2", true)))
        val ticket =
            Ticket("해리포터", HeadCount(2), LocalDate.of(2025, 4, 17), LocalTime.of(11, 0), seats)
        val expected = 20000

        val actual = ticket.amount

        assertEquals(expected, actual)
    }

    @Test
    fun `예매 인원과 좌석별 등급에 맞는 금액을 계산한다2`() {
        val seats = Seats(listOf(Seat("C1", true), Seat("A2", true)))
        val ticket =
            Ticket("해리포터", HeadCount(2), LocalDate.of(2025, 4, 17), LocalTime.of(11, 0), seats)
        val expected = 25000

        val actual = ticket.amount

        assertEquals(expected, actual)
    }

    @Test
    fun `예매 인원이 0보다 큰 지 비교한다`() {
        val ticket =
            Ticket(
                "해리포터",
                HeadCount(1),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )

        val actual = ticket.isHeadCountValid()
        assertTrue(actual)

        val ticket2 =
            Ticket(
                "해리포터",
                HeadCount(0),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )
        val actual2 = ticket2.isHeadCountValid()
        assertFalse(actual2)
    }

    @Test
    fun `좌석이 선택되면, 해당하는 좌석이 이전에 선택되어있지 않은 경우 티켓의 좌석에 추가된다`() {
        val seat = Seat("A1")
        val ticket =
            Ticket(
                "해리포터",
                HeadCount(2),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(emptyList()),
            )

        val newTicket = ticket.toggleSeat(seat)

        val expectedSeat = seat.copy(isSelected = true)
        val expectedTicket = ticket.copy(seats = Seats(listOf(expectedSeat)))

        assertEquals(expectedTicket, newTicket)
    }

    @Test
    fun `선택되어있던 좌석을 다시 선택하는 경우 티켓의 좌석에서 제거된다`() {
        val seat = Seat("A1")
        val ticket =
            Ticket(
                "해리포터",
                HeadCount(2),
                LocalDate.of(2025, 4, 17),
                LocalTime.of(11, 0),
                Seats(listOf(seat.copy(isSelected = true))),
            )

        val newTicket = ticket.toggleSeat(seat)

        val expectedTicket = ticket.copy(seats = Seats(emptyList()))

        assertEquals(expectedTicket, newTicket)
    }
}
