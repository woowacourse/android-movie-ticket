package woowacourse.movie.reservation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class MovieReservationTicketCountDataTest {
    private lateinit var movieReservationTicketCountData: MovieReservationTicketCountData

    @BeforeEach
    fun setup() {
        movieReservationTicketCountData = MovieReservationTicketCountData
    }

    @AfterEach
    fun tearDown() {
        movieReservationTicketCountData.initTicketCount()
    }

    @Test
    fun `수량의 기본 값은 1이다`() {
        assertThat(movieReservationTicketCountData.ticketCount.number).isEqualTo(1)
    }

    @Test
    fun `플러스 버튼을 누르면 티켓개수가 증가해야한다`() {
        // when
        movieReservationTicketCountData.plusTicketCount()

        // then
        assertThat(movieReservationTicketCountData.ticketCount.number).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        movieReservationTicketCountData.plusTicketCount()
        movieReservationTicketCountData.plusTicketCount()
        movieReservationTicketCountData.minusTicketCount()

        // then
        assertThat(movieReservationTicketCountData.ticketCount.number).isEqualTo(2)
    }

    @Test
    fun `티켓 개수가 1일 때 마이너스 버튼을 누르면 예외를 던져야 한다`() {
        // then
        assertThrows<IllegalArgumentException> {
            movieReservationTicketCountData.minusTicketCount()
        }
    }
}
