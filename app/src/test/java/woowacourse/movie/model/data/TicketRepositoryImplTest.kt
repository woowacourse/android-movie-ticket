package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.equalTicket
import woowacourse.movie.model.ticket1
import woowacourse.movie.model.ticket2
import woowacourse.movie.model.ticket3

class TicketRepositoryImplTest {
    @BeforeEach
    fun setUp() {
        TicketRepositoryImpl.deleteAll()
    }

    @Test
    fun `티켓을 저장한다`() {
        val id = TicketRepositoryImpl.save(ticket1)
        val actual = TicketRepositoryImpl.find(id)
        equalTicket(actual, ticket1)
    }

    @Test
    fun `특정 id의 티켓을 가져온다`() {
        // given
        TicketRepositoryImpl.save(ticket1)
        TicketRepositoryImpl.save(ticket2)
        val id = TicketRepositoryImpl.save(ticket3)

        // when
        val actual = TicketRepositoryImpl.find(id)

        // then
        equalTicket(actual, ticket3)
    }

    @Test
    fun `유효하지 않은 id의 티켓을 찾으려는 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            TicketRepositoryImpl.find(-1L)
        }
    }

    @Test
    fun `모든 티켓을 가져온다`() {
        // given
        TicketRepositoryImpl.save(ticket1)
        TicketRepositoryImpl.save(ticket2)
        TicketRepositoryImpl.save(ticket3)

        // when
        val actual = TicketRepositoryImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        equalTicket(actual[0], ticket1)
        equalTicket(actual[1], ticket2)
        equalTicket(actual[2], ticket3)
    }

    @Test
    fun `모든 티켓을 삭제한다`() {
        // given
        TicketRepositoryImpl.save(ticket1)
        TicketRepositoryImpl.save(ticket2)
        TicketRepositoryImpl.save(ticket3)

        // when
        TicketRepositoryImpl.deleteAll()
        val actual = TicketRepositoryImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
