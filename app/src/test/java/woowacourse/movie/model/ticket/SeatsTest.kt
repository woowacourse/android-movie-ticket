package woowacourse.movie.model.ticket

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatCol
import woowacourse.movie.model.ticket.seat.SeatRow
import woowacourse.movie.model.ticket.seat.SeatToggleResult
import woowacourse.movie.model.ticket.seat.Seats
import woowacourse.movie.model.ticket.seat.grade.SeatGrade
import woowacourse.movie.model.ticket.seat.grade.SeatGradePolicy

class MockSeatGradePolicy : SeatGradePolicy {
    override fun getGrade(seat: Seat): SeatGrade {
        // row 0: 10000원 (B석), row 1: 15000원 (S석), 나머지: 12000원 (A석)
        return when (seat.row.index) {
            0 -> SeatGrade.B
            1 -> SeatGrade.S
            else -> SeatGrade.A
        }
    }
}

class SeatsTest {
    private lateinit var seats: Seats
    private val seatGradePolicy = MockSeatGradePolicy()

    private val seatA = Seat(SeatRow(0), SeatCol(0))
    private val seatB = Seat(SeatRow(1), SeatCol(1))

    @BeforeEach
    fun setup() {
        seats = Seats(seatGradePolicy = seatGradePolicy)
    }

    @Test
    fun `선택되지 않은 좌석을 토글할 시 선택된 좌석들에 추가된다`() {
        // when
        val result = seats.toggleSeat(seatA)

        // then
        assertTrue(seats.isSelectedSeat(seatA))
        assertTrue(result is SeatToggleResult.Added)
        assertEquals(seatA, (result as SeatToggleResult.Added).seat)
    }

    @Test
    fun `선택된 좌석을 토글할 시 선택된 좌석들에서 삭제된다`() {
        // given
        seats.toggleSeat(seatA)

        // when
        val result = seats.toggleSeat(seatA)

        // then
        assertFalse(seats.isSelectedSeat(seatA))
        assertTrue(result is SeatToggleResult.Removed)
        assertEquals(seatA, (result as SeatToggleResult.Removed).seat)
    }

    @Test
    fun `좌석을 추가하거나 삭제할때 전체 티켓 가격이 계산된다`() {
        // A좌석(10000원) 추가
        seats.toggleSeat(seatA)
        assertEquals(10000, seats.totalTicketPrice.value)

        // B좌석(15000원) 추가
        seats.toggleSeat(seatB)
        assertEquals(25000, seats.totalTicketPrice.value)

        // A좌석(10000원) 제거
        seats.toggleSeat(seatA)
        assertEquals(15000, seats.totalTicketPrice.value)
    }

    @Test
    fun `좌석을 추가하거나 삭제할때 전체 티켓의 크기를 알 수 있다`() {
        assertEquals(0, seats.size())

        seats.toggleSeat(seatA)
        assertEquals(1, seats.size())

        seats.toggleSeat(seatB)
        assertEquals(2, seats.size())

        seats.toggleSeat(seatA)
        assertEquals(1, seats.size())
    }

    @Test
    fun `전체 좌석의 좌석 코드를 받아올 수 있다`() {
        seats.toggleSeat(seatA)
        seats.toggleSeat(seatB)

        val codes = seats.getSeatCodes()
        assertTrue(codes.contains(seatA.seatCode))
        assertTrue(codes.contains(seatB.seatCode))
        assertEquals(2, codes.size)
    }
}
