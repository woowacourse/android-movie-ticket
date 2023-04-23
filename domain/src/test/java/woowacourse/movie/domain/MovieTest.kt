package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class MovieTest {

    @Test
    fun `영화가 가지고 있지 않는 상영 정보에 대해 예매하면 에러가 발생한다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        movie.addScreening(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse))

        assertThatIllegalArgumentException().isThrownBy {
            movie.reserve(ScreeningInfoOfMovie(LocalDateTime.MIN, movieHouse), setOf(Point(1, 1)))
        }.withMessage("상영이 존재하지 않습니다.")
    }

    @Test
    fun `예매할 때 영화가 상영되는 영화관에 입력 받은 위치 중 좌석이 없는 위치가 있다면 에러가 발생한다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        movie.addScreening(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse))

        assertThatIllegalArgumentException().isThrownBy {
            movie.reserve(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse), setOf(Point(1, 3)))
        }.withMessage("좌석이 존재하지 않습니다.")
    }

    @Test
    fun `예매할 때 영화가 입력 받은 상영 정보를 가지고 있고 영화관에 입력 받은 위치 모두 좌석이 있다면 그 상영에 대해 예매할 수 있다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        movie.addScreening(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse))

        assertDoesNotThrow {
            movie.reserve(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse), setOf(Point(1, 2)))
        }
    }

    @Test
    fun `영화가 가지고 있지 않는 상영 정보에 대해 예매 금액을 계산하면 에러가 발생한다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        movie.addScreening(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse))

        assertThatIllegalArgumentException().isThrownBy {
            movie.calculateReservationFee(ScreeningInfoOfMovie(LocalDateTime.MIN, movieHouse), setOf(Point(1, 1)))
        }.withMessage("상영이 존재하지 않습니다.")
    }

    @Test
    fun `예매 금액을 계산할 때 영화가 상영되는 영화관에 입력 받은 위치 중 좌석이 없는 위치가 있다면 에러가 발생한다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        movie.addScreening(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse))

        assertThatIllegalArgumentException().isThrownBy {
            movie.calculateReservationFee(ScreeningInfoOfMovie(LocalDateTime.MAX, movieHouse), setOf(Point(1, 3)))
        }.withMessage("좌석이 존재하지 않습니다.")
    }

    @Test
    fun `예매할 수 있다면 예매 금액은 모든 좌석 위치에 할인이 적용된 금액의 합과 같다`() {
        val movie = Movie(1, "제목", Minute(152), "요약")
        val movieHouse = MovieHouse(1, setOf(Seat(1, 1, SeatClass.S), Seat(1, 2, SeatClass.B)))
        val screeningInfoOfMovie =
            ScreeningInfoOfMovie(LocalDateTime.of(2024, 3, 10, 9, 0), movieHouse)
        movie.addScreening(screeningInfoOfMovie)

        val actual = movie.calculateReservationFee(
            screeningInfoOfMovie, setOf(Point(1, 1), Point(1, 2))
        )

        assertThat(actual).isEqualTo(Money(18_500))
    }
}
