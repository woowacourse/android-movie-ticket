package domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class ReservationTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movie = Movie(
            name = "해리포터",
            posterImage = null,
            screeningPeriod = LocalDate.of(2000, 10, 1),
            runningTime = 120,
            description = "마법영화"
        )
        val reservation = Reservation.from(movie, 3)
        val result = Reservation(
            movie = movie,
            ticketCount = 3,
            paymentAmount = PaymentAmount(39000),
        )

        assertEquals(reservation, result)
    }
}
