package domain.reservation

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Name
import woowacourse.movie.domain.movie.ScreeningPeriod
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.reservation.Reservation
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movie = Movie(
            name = Name("해리포터"),
            posterImage = null,
            screeningPeriod = ScreeningPeriod(
                LocalDate.of(2000, 10, 1),
                LocalDate.of(2000, 10, 30)
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
