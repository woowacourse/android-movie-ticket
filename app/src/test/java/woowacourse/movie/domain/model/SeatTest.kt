package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `S 등급 좌석의 가격을 구한다`() {
        val seat = Seat(Position(0, 0), Grade.S)
        assertThat(seat.price()).isEqualTo(15_000)
    }

    @Test
    fun `A 등급 좌석의 가격을 구한다`() {
        val seat = Seat(Position(0, 0), Grade.A)
        assertThat(seat.price()).isEqualTo(12_000)
    }

    @Test
    fun `B 등급 좌석의 가격을 구한다`() {
        val seat = Seat(Position(0, 0), Grade.B)
        assertThat(seat.price()).isEqualTo(10_000)
    }
}
