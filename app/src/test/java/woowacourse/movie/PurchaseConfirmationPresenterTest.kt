package woowacourse.movie

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.presenter.PurchaseConfirmationPresenter
import woowacourse.movie.repository.ReservationRepository

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
        presenter.loadReservation(0)
        verify { view.displayReservation(any()) }
    }
}
