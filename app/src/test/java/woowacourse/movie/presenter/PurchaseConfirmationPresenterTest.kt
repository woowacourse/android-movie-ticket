package woowacourse.movie.presenter

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.presentation.purchase_confirmation.PurchaseConfirmationContract
import woowacourse.movie.repository.reservation.ReservationRepository
import woowacourse.movie.presentation.purchase_confirmation.PurchaseConfirmationPresenter

@ExtendWith(MockKExtension::class)
class PurchaseConfirmationPresenterTest {
    @RelaxedMockK
    lateinit var view: PurchaseConfirmationContract.View

    lateinit var presenter: PurchaseConfirmationPresenter

    @RelaxedMockK
    lateinit var repository: ReservationRepository

    @BeforeEach
    fun setUp() {
        presenter = PurchaseConfirmationPresenter(view, repository)
    }

    @Test
    fun `영화 예매 정보를 표기해야 한다`() {
        presenter.loadReservation()
        verify { view.displayReservation(any()) }
    }
}
