package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class MovieTicketTest {
    @Test
    fun `인원이 2명일 경우 예매 금액은 26000원이다`() {
        val ticket = MovieTicket(
            "title",
            MovieTime(
                LocalDate.of(2023, 4, 13),
                Time(11)
            ),
            PeopleCount(2)
        )
        assertEquals(26000, ticket.getPrice())
    }
}
