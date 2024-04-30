package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TicketTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 9, 10])
    fun `티켓 개수가 1 ~ 10 이어야 한다`(count: Int) {
        assertDoesNotThrow { Ticket(count) }
    }

    @Test
    fun `티켓 개수를 1 증가시킨다`() {
        val ticket = Ticket(1)
        val increasedTicket = ticket.increase()

        assertThat(increasedTicket).isEqualTo(Ticket(2))
    }

    @Test
    fun `티켓 개수를 1 감소시킨다`() {
        val ticket = Ticket(3)
        val increasedTicket = ticket.decrease()

        assertThat(increasedTicket).isEqualTo(Ticket(2))
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 11])
    fun `티켓 개수가 1 ~ 10 이 아니면 예외를 던진다`(count: Int) {
        assertThrows<IllegalArgumentException> { Ticket(count) }
    }
}
