package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.contract.TicketingContract
import woowacourse.movie.view.utils.ErrorMessage

class TicketingPresenterTest {
    private lateinit var view: TicketingContract.View
    private lateinit var presenter: TicketingContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<TicketingContract.View>()
        presenter = TicketingPresenter(view)
    }

    @Test
    fun `예매를_위한_영화_정보를_불러온다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        // when
        presenter.initializeTicketingData(0L, 1)
        // then
        verify { view.assignInitialView(any(), 1, any(), any()) }
    }

    @Test
    fun `존재하지_않는_상영_정보_아이디가_주어지면_정보_초기화가_이뤄지지_않고_토스트_메시지가_출력된다`() {
        // given
        every { view.showToastMessage(any()) } just runs
        // when
        presenter.initializeTicketingData(-1, 1)
        // then
        verify {
            view.showToastMessage(ErrorMessage.ERROR_INVALID_SCREENING_ID.value)
        }
    }

    @Test
    fun `감소_버튼을_클릭하면_티켓_수가_줄어든다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        every { view.updateCount(any()) } just runs
        // when
        presenter.initializeTicketingData(0, 2)
        presenter.decreaseCount()
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any())
            view.updateCount(1)
        }
    }

    @Test
    fun `최소_티켓_개수인_상태에서_감소_버튼을_클릭하면_티켓_수가_줄어들지_않고_토스트_메시지가_출력된다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        every { view.showToastMessage(any()) } just runs
        // when
        presenter.initializeTicketingData(0, 1)
        presenter.decreaseCount()
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any())
            view.showToastMessage(ErrorMessage.ERROR_NON_POSITIVE_NUMBER.value)
        }
    }

    @Test
    fun `증가_버튼을_클릭하면_티켓_수가_증가한다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        every { view.updateCount(any()) } just runs
        // when
        presenter.initializeTicketingData(0, 1)
        presenter.increaseCount()
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any())
            view.updateCount(2)
        }
    }

    @Test
    fun `좌석_선택_버튼을_누르면_좌석_선택_화면으로_이동한다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        every { view.navigateToSeatSelection(any()) } just runs
        // when
        presenter.initializeTicketingData(0, 1)
        presenter.reserveTickets()
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any())
            view.navigateToSeatSelection(any())
        }
    }

    @Test
    fun `예약_날짜를_변경하면_스피너의_현재_날짜에_반영되며_예약_가능한_시간도_업데이트된다`() {
        // given
        every { view.assignInitialView(any(), any(), any(), any()) } just runs
        every { view.updateAvailableTimes(any()) } just runs
        // when
        presenter.initializeTicketingData(0, 1)
        presenter.updateDate("2024-03-08")
        // then
        verify {
            view.assignInitialView(any(), any(), any(), any())
            view.updateAvailableTimes(any())
        }
    }
}
