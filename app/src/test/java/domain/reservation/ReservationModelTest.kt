package domain.reservation

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.movie.Name
import woowacourse.movie.domain.movie.ScreeningPeriod
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.ReservationModel
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationModelTest {
    @Test
    fun `영화와 티켓 개수를 받아서 예매 정보를 반환한다`() {
        val movieModel = MovieModel(
            name = Name("해리포터"),
            posterImage = null,
            screeningPeriod = ScreeningPeriod(
                LocalDate.of(2000, 10, 1),
                LocalDate.of(2000, 10, 30)
            ),
            runningTime = 120,
            description = "마법영화"
        )
        val reservationModel = ReservationModel.from(movieModel, 3, LocalDateTime.of(2000, 10, 1, 13, 0))
        val result = ReservationModel(
            movieModel = movieModel,
            screeningDateTime = LocalDateTime.of(2000, 10, 1, 13, 0),
            ticketCount = 3,
            paymentAmount = PaymentAmount(39000),
        )

        assertEquals(reservationModel, result)
    }
}
