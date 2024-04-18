package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieReservationModelTest {
    private lateinit var movieReservationModel: MovieReservationModel

    @BeforeEach
    fun setup() {
        movieReservationModel = MovieReservationModel()
    }

    @Test
    fun `수량의 기본 값은 1이다`() {
        assertThat(movieReservationModel.ticketCount).isEqualTo(1)
    }

    @Test
    fun `플러스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        movieReservationModel.plusTicketCount()

        // then
        assertThat(movieReservationModel.ticketCount).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        movieReservationModel.plusTicketCount()
        movieReservationModel.plusTicketCount()
        movieReservationModel.minusTicketCount()

        // then
        assertThat(movieReservationModel.ticketCount).isEqualTo(2)
    }

    @Test
    fun `티켓 개수가 1일 때 마이너스 버튼을 누르면 티켓개수가 감소하지 않아야 한다`() {
        // when
        movieReservationModel.minusTicketCount()

        // then
        assertThat(movieReservationModel.ticketCount).isEqualTo(1)
    }
}
