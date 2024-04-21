package woowacourse.movie.model.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieReservationData

class MovieReservationDataTest {
    private lateinit var movieReservationData: MovieReservationData

    @BeforeEach
    fun setup() {
        movieReservationData = MovieReservationData()
    }

    @Test
    fun `수량의 기본 값은 1이다`() {
        assertThat(movieReservationData.ticketCount).isEqualTo(1)
    }

    @Test
    fun `플러스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        movieReservationData.plusTicketCount()

        // then
        assertThat(movieReservationData.ticketCount).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        movieReservationData.plusTicketCount()
        movieReservationData.plusTicketCount()
        movieReservationData.minusTicketCount()

        // then
        assertThat(movieReservationData.ticketCount).isEqualTo(2)
    }

    @Test
    fun `티켓 개수가 1일 때 마이너스 버튼을 누르면 티켓개수가 감소하지 않아야 한다`() {
        // when
        movieReservationData.minusTicketCount()

        // then
        assertThat(movieReservationData.ticketCount).isEqualTo(1)
    }
}
