package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieSeatsTest {
    @Test
    fun `좌석을 추가하면 좌석 목록에 해당 좌석이 추가된다`() {
        // given
        val movieSeats = MovieSeats()
        val seat = MovieSeat(0, 0)

        // when
        movieSeats.add(seat)

        // then
        assertThat(movieSeats.seats).contains(seat)
    }

    @Test
    fun `좌석을 제거하면 좌석 목록에서 해당 좌석이 제거된다`() {
        // given
        val seat = MovieSeat(0, 0)
        val movieSeats = MovieSeats(listOf(seat))

        // when
        movieSeats.remove(seat)

        // then
        assertThat(movieSeats.seats).doesNotContain(seat)
    }

    @Test
    fun `좌석 목록의 총 가격을 계산한다`() {
        // given
        val movieSeats =
            MovieSeats(
                listOf(
                    MovieSeat(1, 1),
                    MovieSeat(3, 1),
                    MovieSeat(5, 1),
                ),
            )

        // when
        val totalPrice = movieSeats.totalPrice

        // then
        assertThat(totalPrice.value).isEqualTo(37_000)
    }
}
