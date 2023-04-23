package woowacourse.movie.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MovieHouseTest {

    @Test
    fun `영화관을 생성할 때 좌석의 위치가 같은 좌석을 입력하면 에러가 발생한다`() {
        val point = Point(1,1)
        val seats = setOf(Seat(point, SeatClass.S), Seat(point, SeatClass.B))

        assertThatIllegalArgumentException().isThrownBy {
            MovieHouse(1, seats)
        }.withMessage("영화관 내의 좌석들의 위치가 겹칠 수 없습니다.")
    }

    @Test
    fun `영화관 내의 특정 좌표에 좌석이 존재하는지 알 수 있다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)

        val actual = movieHouse.containsSeatOn(Point(1, 1))

        assertThat(actual).isTrue
    }

    @Test
    fun `영화관 내에 좌석이 존재하지 않는 위치의 좌석에 대한 요금을 요청하면 에러가 발생한다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)
        val notExistSeatPoint = Point(2, 1)

        assertThatIllegalArgumentException().isThrownBy {
            movieHouse.getFee(notExistSeatPoint)
        }.withMessage("${notExistSeatPoint.row}행 ${notExistSeatPoint.column}열에는 좌석이 존재하지 않습니다.")
    }

    @Test
    fun `영화관 내에 좌석이 존재하는 위치의 좌석에 대한 요금을 요청하면 그 좌석의 요금을 반환한다`() {
        val seats = setOf(Seat(Point(1, 1), SeatClass.S), Seat(Point(1, 2), SeatClass.B))
        val movieHouse = MovieHouse(1, seats)

        val actual = movieHouse.getFee(Point(1, 1))

        assertThat(actual).isEqualTo(SeatClass.S.seatFee)
    }

    @Test
    fun `영화관은 아이디로 구분된다`() {
        val movieHouseId = 1L
        val oneMovieHouse = MovieHouse(movieHouseId, setOf(Seat(1, 1, SeatClass.S)))
        val otherMovieHouse = MovieHouse(movieHouseId, setOf(Seat(2, 2, SeatClass.B)))

        assertAll(
            { assertThat(oneMovieHouse).isEqualTo(otherMovieHouse) },
            { assertThat(oneMovieHouse.hashCode()).isEqualTo(otherMovieHouse.hashCode()) }
        )
    }
}
