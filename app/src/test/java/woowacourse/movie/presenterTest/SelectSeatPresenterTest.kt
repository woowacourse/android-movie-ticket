package woowacourse.movie.presenterTest

import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.selectSeat.SeatPrice
import woowacourse.movie.selectSeat.SelectSeatContract
import woowacourse.movie.selectSeat.SelectSeatPresenter
import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatPresenterTest {
    private lateinit var view: SelectSeatContract.View
    private lateinit var presenter: SelectSeatPresenter

    private val ticketUI =
        TicketUIModel(
            title = "테스트 영화",
            date = "2025.4.1",
            time = "09:00",
            seats = emptyList(),
            count = 0,
            money = 0,
        )

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = SelectSeatPresenter(view)
        presenter.init(ticketUI)
        clearMocks(view)
    }

    @Test
    fun `viewCreated 하면 뷰 초기 설정 호출 순서대로 일어난다`() {
        presenter.init(ticketUI)
        verifySequence {
            view.showPrice(0)
            view.unableButton()
        }
    }

    @Test
    fun `좌석 클릭 시 미선택 상태이면 선택 처리하고 금액 갱신`() {
        val priceA1 = SeatPrice.getPrice("A1")

        presenter.toggleSeat(tag = "A1", fullCount = 2)

        verifySequence {
            view.highlightSeat("A1")
            view.showPrice(priceA1)
            view.unableButton()
        }
    }

    @Test
    fun `같은 좌석을 다시 클릭하면 선택 해제 처리하고 금액 갱신`() {
        // 먼저 선택
        presenter.toggleSeat("A1", fullCount = 2)
        clearMocks(view)

        presenter.toggleSeat("A1", fullCount = 2)

        verifySequence {
            view.unHighlightSeat("A1")
            view.showPrice(0)
            view.unableButton()
        }
    }

    @Test
    fun `fullCount 에 도달하면 활성화 버튼 호출`() {
        val priceA1 = SeatPrice.getPrice("A1")

        presenter.toggleSeat("A1", fullCount = 1)

        verifySequence {
            view.highlightSeat("A1")
            view.showPrice(priceA1)
            view.enableButton()
        }
    }

    @Test
    fun `이미 full 상태에서 다른 좌석 클릭 시 좌석이 꽉 찼다는걸 표시하고 선택은 무효`() {
        presenter.toggleSeat("B2", fullCount = 0)

        verifySequence {
            view.showFullSeat()
            view.enableButton()
        }
    }

    @Test
    fun `예매 버튼을 누르면 확인 화면을 보여준다`() {
        presenter.askConfirm()

        verify { view.askToConfirmBook() }
    }

    @Test
    fun `예를 누르면 최종적으로 티켓을 UI모델로 변환 후 액티비티 이동`() {
        presenter.toggleSeat("A1", fullCount = 1)
        clearMocks(view)

        presenter.completeBooking()

        val expected =
            ticketUI.copy(
                seats = listOf("A1"),
                count = 1,
                money = SeatPrice.getPrice("A1"),
            )
        verify { view.navigateToBookingResult(expected) }
    }
}
