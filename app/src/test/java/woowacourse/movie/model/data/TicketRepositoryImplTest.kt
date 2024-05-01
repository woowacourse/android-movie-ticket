package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.equalTicket
import woowacourse.movie.model.movieId
import woowacourse.movie.model.screeningDateTime
import woowacourse.movie.model.selectedSeats
import woowacourse.movie.model.ticket

class TicketRepositoryImplTest {
    @BeforeEach
    fun setUp() {
        TicketRepositoryImpl.deleteAll()
    }

    @Test
    fun `티켓을 저장한다`() {
        val id = TicketRepositoryImpl.save(0L, screeningDateTime, selectedSeats)
        val actual = TicketRepositoryImpl.find(id)
        equalTicket(actual, ticket)
    }

    @Test
    fun `특정 id의 티켓을 가져온다`() {
        // given
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        val id = TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)

        // when
        val actual = TicketRepositoryImpl.find(id)

        // then
        equalTicket(actual, ticket.copy(id = id))
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
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)

        // when
        val actual = TicketRepositoryImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
    }

    @Test
    fun `모든 티켓을 삭제한다`() {
        // given
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)
        TicketRepositoryImpl.save(movieId, screeningDateTime, selectedSeats)

        // when
        TicketRepositoryImpl.deleteAll()
        val actual = TicketRepositoryImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
