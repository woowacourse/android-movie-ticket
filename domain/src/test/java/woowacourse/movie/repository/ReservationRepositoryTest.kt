package woowacourse.movie.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Theater
import java.time.LocalDateTime

class ReservationRepositoryTest {

    @Test
    fun `아이디가 없는 상영을 저장하면 자동으로 아이디가 부여된다`() {
        val theater = Theater(5, 4)
        val reservation = Reservation(LocalDateTime.now(), theater, listOf(Point(1, 1)))

        ReservationRepository.save(reservation)

        assertThat(reservation.id).isNotNull
    }
}
