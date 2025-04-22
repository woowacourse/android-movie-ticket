package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BookedTicketTest {
    @Test
    fun `티켓의 총 가격을 구할 수 있다`() {
        val bookedTicket =
            BookedTicket(
                "해리 포터",
                Headcount(5, TicketType.GENERAL),
                "2025.4.22 10:00",
            )
        val actual = bookedTicket.totalPrice()

        val expected = 5 * 13000

        assertThat(actual).isEqualTo(expected)
    }
}
