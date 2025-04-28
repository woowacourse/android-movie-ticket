package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.RESERVATION_WITH_POINTS
import woowacourse.movie.domain.Reservation
import woowacourse.movie.result.ReservationResultContract
import woowacourse.movie.result.ReservationResultPresenter
import kotlin.test.Test

class ReservationResultPresenterTest {
    private lateinit var view: ReservationResultContract.View
    private lateinit var presenter: ReservationResultPresenter
    private lateinit var reservation: Reservation

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationResultContract.View>(relaxed = true)
        presenter = ReservationResultPresenter(view)
        reservation = RESERVATION_WITH_POINTS
    }

    @Test
    fun `initReservation 호출 시 예매 내역을 보여준다`() {
        // given & when
        presenter.initReservation(reservation)

        // then
        verify { view.showReservation(reservation) }
        verify { view.showTicket(reservation.seats.seats) }
        verify { view.showTotalPrice(reservation.seats.totalPrice()) }
    }
}
