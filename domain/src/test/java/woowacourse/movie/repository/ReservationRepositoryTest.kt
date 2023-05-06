package woowacourse.movie.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.screening.Minute
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Reservation
import woowacourse.movie.domain.theater.Point
import woowacourse.movie.domain.theater.Theater
import java.time.LocalDateTime

class ReservationRepositoryTest {

    @Test
    fun `아이디가 없는 상영을 저장하면 자동으로 아이디가 부여된다`() {
        val movie = Movie("제목", Minute(152), "요약")
        val theater = Theater(5, 4)
        val reservation = Reservation(movie, LocalDateTime.now(), theater, listOf(Point(1, 1)))

        ReservationRepository.save(reservation)

        assertThat(reservation.id).isNotNull
    }
}
