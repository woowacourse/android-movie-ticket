package woowacourse.movie.view.seat

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.HARRY_POTTER_RESERVATION
import woowacourse.movie.domain.model.reservation.PurchaseCount
import woowacourse.movie.ui.seat.SeatContract
import woowacourse.movie.ui.seat.SeatPresenter

class SeatPresenterTest {
    private val view = mockk<SeatContract.View>(relaxUnitFun = true)
    private val presenter = SeatPresenter(view)

    @BeforeEach
    fun setUp() {
        presenter.initData(
            HARRY_POTTER_RESERVATION,
            PurchaseCount(1),
        )
    }

    @Test
    fun `view 초기화시에 initView,예약 버튼 활성화에 정확한 값인지 확인`() {
        verify {
            view.initView("해리포터", 0)
            view.setReserveEnabled(false)
        }
    }

    @Test
    fun `티켓을 추가하고 티켓 가격과 티켓 개수가 구매 개수와 같은지 여부를 view에 전달`() {
        presenter.addTicket(1, 1, "s", {}, {})
        verify {
            view.updatePrice(15_000)
            view.setReserveEnabled(true)
        }
    }

    @Test
    fun `티켓을 제거하고 티켓 가격과 티켓 개수가 구매 개수와 같은지 여부를 view에 전달`() {
        presenter.addTicket(1, 1, "s", {}, {})
        presenter.removeTicket(1, 1, "s")
        verify {
            view.updatePrice(0)
            view.setReserveEnabled(false)
        }
    }

    @Test
    fun `현재 예약 정보 전달`() {
        presenter.reserve()
        verify {
            view.reserve(HARRY_POTTER_RESERVATION)
        }
    }
}
