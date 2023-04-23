package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class ScreeningInfoOfMovieTest {

    @Test
    fun `만약 영화관 안에 입력받은 위치 모두 좌석이 존재한다면 예매될 수 있다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)
        val screeningInfoOfMovie = ScreeningInfoOfMovie(LocalDateTime.now(), movieHouse)

        val actual = screeningInfoOfMovie.canBeReserved(setOf(Point(1, 1), Point(1, 2)))

        assertThat(actual).isTrue
    }

    @Test
    fun `만약 영화관 안에 입력받은 위치 중 어떤 위치에 좌석이 존재하지 않는다면 예매될 수 없다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)
        val screeningInfoOfMovie = ScreeningInfoOfMovie(LocalDateTime.now(), movieHouse)

        val actual =
            screeningInfoOfMovie.canBeReserved(setOf(Point(1, 1), Point(1, 2), Point(1, 3)))

        assertThat(actual).isFalse
    }

    @Test
    fun `어떤 좌석의 위치의 원래 요금을 요청하면 영화관 안의 그 위치의 좌석의 요금을 반환한다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)
        val screeningInfoOfMovie = ScreeningInfoOfMovie(LocalDateTime.now(), movieHouse)

        val actual = screeningInfoOfMovie.getInitFee(Point(1, 1))

        assertThat(actual).isEqualTo(SeatClass.S.seatFee)
    }
}
