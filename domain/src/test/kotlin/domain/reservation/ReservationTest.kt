package domain.reservation

import domain.payment.PaymentAmount
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

// todo: 테스트 코드 수정하기..
class ReservationTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movie = MockMovie()
        val selectedSeat = ScreeningSeat(SeatRow.A, SeatColumn.FIRST)

        val reservation = Reservation.of(
            movieName = movie.movieName,
            ticketCount = TicketCount(1),
            screeningDateTime = LocalDateTime.of(2000, 10, 10, 13, 0),
            paymentAmount = selectedSeat.payment,
            seats = listOf(selectedSeat)
        )
        val result = Reservation(
            movieName = movie.movieName,
            screeningDateTime = LocalDateTime.of(2000, 10, 10, 13, 0),
            ticketCount = TicketCount(1),
            paymentAmount = PaymentAmount(9000),
            seats = listOf(selectedSeat)
        )

        assertEquals(reservation, result)
    }
}
