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
    fun `showReservation 호출 시 View의 bind 메서드를 호출한다`() {
        // given
        presenter.setUpReservation(reservation)

        // when
        presenter.showReservation()

        // then
        verify { view.bindReservation(reservation) }
        verify { view.bindTicket(reservation.seats.seats) }
        verify { view.bindTotalPrice(reservation.seats.totalPrice()) }
    }
}
