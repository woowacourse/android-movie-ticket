package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class ScreeningsTest {

    @Test
    fun `상영 모음에 상영을 추가하면 상영이 추가된다`() {
        val screenings = Screenings()
        val screening = Screening(LocalDateTime.of(2021, 3, 1, 5, 1))
        screenings.addScreening(screening)

        val actual = screenings.screenings

        val expected = mapOf<Screening, ReservationResult?>(screening to null)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `상영 모음에 존재하지 않는 상영을 예매하면 에러가 발생한다`() {
        val screenings = Screenings()

        assertThatIllegalArgumentException().isThrownBy {
            screenings.reserve(
                Screening(LocalDateTime.now()),
                ReservationResult(LocalDateTime.now(), 1)
            )
        }.withMessage("존재하지 않는 상영을 예매할 수 없습니다.")
    }

    @Test
    fun `상영 모음에 상영을 예매하면 해당 상영과 예매가 매핑된다`() {
        val screenings = Screenings()
        val screeningDateTime = LocalDateTime.of(2021,3,1,10,0)
        val screening = Screening(screeningDateTime)
        screenings.addScreening(screening)
        val reservationResult = ReservationResult(screeningDateTime, 1)

        screenings.reserve(screening, reservationResult)
        val actual = screenings.screenings[screening]

        assertThat(actual).isEqualTo(reservationResult)
    }
}
