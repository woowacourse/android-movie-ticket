package domain.reservation

import domain.movie.Movie
import domain.movie.Name
import domain.movie.ScreeningDate
import domain.movie.ScreeningPeriod
import domain.payment.PaymentAmount
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movie = Movie(
            name = Name("해리포터"),
            posterImage = null,
            screeningPeriod = ScreeningPeriod(
                ScreeningDate(LocalDate.of(2000, 10, 1)),
                ScreeningDate(LocalDate.of(2000, 10, 30))
            ),
            runningTime = 120,
            description = "마법영화"
        )
        val reservation = Reservation.from(movie, 3, LocalDateTime.of(2000, 10, 1, 13, 0))
        val result = Reservation(
            movie = movie,
            screeningDateTime = LocalDateTime.of(2000, 10, 1, 13, 0),
            ticketCount = 3,
            paymentAmount = PaymentAmount(39000),
        )

        assertEquals(reservation, result)
    }
}
