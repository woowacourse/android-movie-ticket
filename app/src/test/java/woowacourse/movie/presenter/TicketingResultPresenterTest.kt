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
            view.assignInitialView(1, "해리 포터와 마법사의 돌", "2024-01-01", "11:00", 10000, listOf("A1"))
        }
    }

    @Test
    fun `유효하지_않은_상영_아이디가_주어지면_예매_결과를_표출하지_못하고_토스트_메시지를_출력한다`() {
        // given
        every { view.showToastMessage(any()) } just runs
        // when
        presenter.initializeTicketingResult(-1, 1, 10000, "2024-01-01", "11:00", arrayOf("A1"))
        // then
        verify {
            view.showToastMessage("존재하지 않는 상영 정보입니다.")
        }
    }
}
