package domain.reservation

import domain.movie.Movie
import domain.movie.MovieName
import domain.movie.RunningTime
import domain.movie.ScreeningDate
import domain.movie.ScreeningPeriod
import domain.payment.PaymentAmount
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

// todo: 테스트 코드 수정하기..
class ReservationTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movie = Movie(
            movieName = MovieName("해리포터"),
            screeningPeriod = ScreeningPeriod(
                ScreeningDate(LocalDate.of(2000, 10, 1)),
                ScreeningDate(LocalDate.of(2050, 10, 30))
            ),
            runningTime = RunningTime(120),
            description = "마법영화"
        )

        val selectedSeat = ScreeningSeat(SeatRow.A, SeatColumn.FIRST)
        val reservation = Reservation.of(
            movieName = movie.movieName.value,
            ticketCount = 1,
            screeningDateTime = LocalDateTime.of(2000, 10, 10, 13, 0),
            paymentAmount = selectedSeat.payment,
            seats = listOf(selectedSeat)
        )
        val result = Reservation(
            movieName = movie.movieName,
            screeningDateTime = LocalDateTime.of(2000, 10, 10, 13, 0),
            ticketCount = 1,
            paymentAmount = PaymentAmount(9000),
            seats = listOf(selectedSeat)
        )

        assertEquals(reservation, result)
    }
}
