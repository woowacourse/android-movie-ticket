package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class MovieTicketTest {
    @Test
    fun `인원이 2명일 경우 예매 금액은 26000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(
                LocalDateTime.of(2023, 4, 13, 12, 0)
            ),
            PeopleCount(2)
        )
        assertEquals(26000, ticket.getPrice())
    }

    @Test
    fun `인원이 2명이고 무비데이인 경우 예매 금액은 23400원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(
                LocalDateTime.of(2023, 4, 10, 12, 0)
            ),
            PeopleCount(2)
        )
        assertEquals(23400, ticket.getPrice())
    }

    @Test
    fun `인원이 2명이고 조조 영화인 경우 예매 금액은 24000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(
                LocalDateTime.of(2023, 4, 11, 10, 0)
            ),
            PeopleCount(2)
        )
        assertEquals(24000, ticket.getPrice())
    }

    @Test
    fun `인원이 2명이고 심야 영화인 경우 예매 금액은 24000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(
                LocalDateTime.of(2023, 4, 11, 23, 0)
            ),
            PeopleCount(2)
        )
        assertEquals(24000, ticket.getPrice())
    }

    @Test
    fun `인원이 2명이고 무비 데이고 조조 영화인 경우 예매 금액은 21400원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(
                LocalDateTime.of(2023, 4, 20, 9, 0)
            ),
            PeopleCount(2)
        )
        assertEquals(21400, ticket.getPrice())
    }
}
