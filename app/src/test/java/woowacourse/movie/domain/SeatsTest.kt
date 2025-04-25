package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats

class SeatsTest {
    @Test
    fun `새로운 좌석이 추가된다`() {
        // when
        val seats = Seats()

        // given
        seats.addSeat(Seat(1, 1))

        // then
        assertEquals(seats.item.contains(Seat(1, 1)), true)
    }

    @Test
    fun `선택한 좌석이 제거된다`() {
        // when
        val seats = Seats()

        // given
        seats.addSeat(Seat(1, 1))
        seats.removeSeat(Seat(1, 1))

        // then
        assertEquals(seats.item.isEmpty(), true)
    }
}
