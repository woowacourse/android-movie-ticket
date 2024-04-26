package woowacourse.movie

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.movie.model.theater.Seat

class SeatTest {
    @Test
    fun `빈 좌석을 선택 하면 선택된 좌석으로 상태를 변경 한다`() {
        val seat = Seat('A', 1, "B")
        assertFalse(seat.chosen)
        seat.chooseSeat()
        assertTrue(seat.chosen)
    }

    @Test
    fun `선택된 좌석을 선택 하면 빈 좌석으로 상태를 변경 한다`() {
        val seat = Seat('A', 1, "B")
        seat.chooseSeat()
        assertTrue(seat.chosen)
        seat.chooseSeat()
        assertFalse(seat.chosen)
    }
}
