package woowacourse.movie

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

    @Test
    fun `B grade seats should cost 10000`() {
        val seat = Seat('A', 1, "B")
        assertEquals(10000, seat.price)
    }

    @Test
    fun `S grade seats should cost 15000`() {
        val seat = Seat('A', 1, "S")
        assertEquals(15000, seat.price)
    }

    @Test
    fun `A grade seats should cost 12000`() {
        val seat = Seat('A', 1, "A")
        assertEquals(12000, seat.price)
    }

    @Test
    fun `Invalid seat grade should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            Seat('A', 1, "X")
        }
    }

    @Test
    fun `Choosing a seat toggles its chosen state`() {
        val seat = Seat('A', 1, "A")
        assertEquals(false, seat.chosen)
        seat.chooseSeat()
        assertEquals(true, seat.chosen)
        seat.chooseSeat()
        assertEquals(false, seat.chosen)
    }
}
