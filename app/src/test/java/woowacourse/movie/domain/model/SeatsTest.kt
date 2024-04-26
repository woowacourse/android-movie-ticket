package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `좌석들의 총 가격을 구한다`() {
        val seats = Seats(
            Seat(Position(0, 0), Grade.S),
            Seat(Position(0, 1), Grade.A)
        )

        assertThat(seats.totalPrice()).isEqualTo(27_000)
    }
}