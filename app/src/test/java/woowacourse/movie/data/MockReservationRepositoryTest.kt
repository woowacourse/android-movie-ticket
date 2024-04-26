package woowacourse.movie.data

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.TestFixture.DUMMY_MOVIE
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.screening.DailySchedule
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningSchedule
import java.time.LocalDate

class MockReservationRepositoryTest {
    @Test
    fun `id를 key로 예약을 불러올 수 있다`() {
        val movie = MockMovieRepository.find(0)
        val result = movie?.id
        Assertions.assertThat(result).isEqualTo(0)
    }

    @Test
    fun `상영과 수량 정보로 예약을 추가할 수 있다`() {
        val size = MockReservationRepository.findAll().size
        MockReservationRepository.save(
            Screening(
                DUMMY_MOVIE,
                ScreeningSchedule(listOf(DailySchedule(LocalDate.of(2024, 3, 1)))),
            ),
            Quantity(10),
        )

        val newSize = MockReservationRepository.findAll().size
        Assertions.assertThat(newSize).isEqualTo(size + 1)
    }
}
