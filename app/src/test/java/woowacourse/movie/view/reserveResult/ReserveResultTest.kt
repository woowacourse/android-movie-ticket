package woowacourse.movie.view.reserveResult

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.HARRY_POTTER_RESERVATION
import woowacourse.movie.ui.reservationResult.ReservationResultContract
import woowacourse.movie.ui.reservationResult.ReservationResultPresenter

class ReserveResultTest {
    private val view = mockk<ReservationResultContract.View>(relaxed = true)
    private val presenter = ReservationResultPresenter(view)

    @BeforeEach
    fun setUp() {
        presenter.initReservation(HARRY_POTTER_RESERVATION)
    }

    @Test
    fun `초기화한 예약 결과를 화면에 보여준다`() {
        verify { view.initScreen(HARRY_POTTER_RESERVATION) }
    }
}
