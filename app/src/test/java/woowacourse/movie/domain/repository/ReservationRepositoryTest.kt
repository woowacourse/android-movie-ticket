package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.utils.getDummyMovie
import woowacourse.movie.utils.getDummyReservation
import woowacourse.movie.utils.getDummySeats
import java.time.LocalDateTime

class ReservationRepositoryTest {
    private lateinit var repository: ReservationRepository

    @BeforeEach
    fun setUp() {
        repository = DummyReservation
    }

    @Test
    fun `스크린 정보와 티켓 개수를 통해 예약 정보를 저장하고 예약 ID를 반환한다`() {
        // given
        val movie = getDummyMovie()
        val seats = getDummySeats()

        // when
        val result = repository.saveReservation(movie, 3, seats, LocalDateTime.now())
        val id = result.getOrThrow()

        // then
        assertThat(id).isEqualTo(1)

        // when
        val reservation = repository.findByReservationId(id).getOrThrow()
        val actual = getDummyReservation()

        // then
        assertThat(reservation.id).isEqualTo(actual.id)
    }
}
