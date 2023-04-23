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
