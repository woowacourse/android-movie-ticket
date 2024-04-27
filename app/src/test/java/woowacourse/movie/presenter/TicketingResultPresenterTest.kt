package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenterTest {
    private lateinit var view: TicketingResultContract.View
    private lateinit var presenter: TicketingResultContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<TicketingResultContract.View>()
        presenter = TicketingResultPresenter(view)
    }

    @Test
    fun `예매_결과를_표시한다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any(), any(), any()) } just runs
        // when
        presenter.initializeTicketingResult(0, 1, 10000, "2024-01-01", "11:00", arrayOf("A1"))
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any(), any(), any())
        }
    }
}
