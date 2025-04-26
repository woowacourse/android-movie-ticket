package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.domain.APRIL_THIRTIETH
import woowacourse.movie.domain.HARRY_POTTER_MOVIE
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Points
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.result.ReservationResultContract
import woowacourse.movie.result.ReservationResultPresenter
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.Test

class ReservationResultPresenterTest {
    private lateinit var view: ReservationResultContract.View
    private lateinit var presenter: ReservationResultPresenter
    private lateinit var reservation: Reservation

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationResultContract.View>(relaxed = true)
        presenter = ReservationResultPresenter(view)
        reservation =
            Reservation(
                movie = HARRY_POTTER_MOVIE,
                _count = TicketCount(3),
                reservedTime =
                    LocalDateTime.of(
                        APRIL_THIRTIETH,
                        LocalTime.of(12, 0),
                    ),
                points =
                    Points(
                        setOf(
                            Point(0, 0),
                            Point(2, 0),
                            Point(4, 0),
                        ),
                    ),
            )
    }

    @Test
    fun `showReservation 호출 시 View의 bind 메서드를 호출한다`() {
        // given
        presenter.setUpReservation(reservation)

        // when
        presenter.showReservation()

        // then
        verify { view.bindReservation(reservation) }
        verify { view.bindTicket(reservation.points.points) }
        verify { view.bindTotalPrice(reservation.points.totalPrice()) }
    }
}
