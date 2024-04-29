package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `좌석 금액 합산`() {
        // given
        val seats = Seats(
            listOf(
                Seat("A", 1, SeatGrade.S),
                Seat("B", 2, SeatGrade.A),
                Seat("C", 3, SeatGrade.B),
            )
        )

        // when
        val totalPrice = seats.totalPrice()

        // then
        assertEquals(45000, totalPrice)
    }
    
    @Test
    fun `좌석 이름 생성`() {
        // given
        val seats = Seats(
            listOf(
                Seat("A", 1, SeatGrade.S),
                Seat("B", 2, SeatGrade.A),
                Seat("C", 3, SeatGrade.B),
            )
        )

        // when
        val seatNames = seats.toString()

        // then
        assertEquals("A1, B2, C3", seatNames)
    }
}
