package woowacourse.movie.presenterTest

import io.mockk.called
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.BookingContract
import woowacourse.movie.booking.BookingPresenter
import woowacourse.movie.model.TicketCount
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private lateinit var presenter: BookingContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingPresenter(view)
    }

    @Test
    fun `뷰를_생성하면_페이지_리스너_핸들러_등을_설정한다`() {
        presenter.onCreateView(null)
        verifySequence {
            view.setupDateChangeListener()
            view.confirmButtonHandler()
            view.countButtonHandler()
        }
    }

    @Test
    fun `예매_버튼을 클릭하면_카운트가_0이_아닐_경우_화면이_이동한다`() {
        val title = "샘플"
        val date = "2024.1.1"
        val time = "12:00"
        val ticketCount = TicketCount(5)
        presenter.onBookButtonClick(title, date, time, ticketCount)
        val exceptedUIModel =
            TicketUIModel(
                title,
                date,
                time,
                listOf(),
                ticketCount.count,
                0,
            )
        verifySequence {
            view.navigateToResult(exceptedUIModel)
        }
    }

    @Test
    fun `예매_버튼을 클릭하면_카운트가_0이_아닐_경우_화면이_이동하지_않는다`() {
        val title = "샘플"
        val date = "2024.1.1"
        val time = "12:00"
        val ticketCount = TicketCount(0)
        presenter.onBookButtonClick(title, date, time, ticketCount)
        val exceptedUIModel =
            TicketUIModel(
                title,
                date,
                time,
                listOf(),
                ticketCount.count,
                0,
            )
        verifySequence { view wasNot called }
    }

    @Test
    fun `+버튼을 클릭하면_카운트가_증가한다`() {
        val ticketCount = TicketCount(5)
        presenter.upTicketCount(ticketCount)
        assertEquals(ticketCount.count, 6)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `-버튼을 클릭하면_카운트가_감소한다`() {
        val ticketCount = TicketCount(5)
        presenter.downTicketCount(ticketCount)
        assertEquals(ticketCount.count, 4)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `카운트는_0_이하로_떨어지지_않는다`() {
        val ticketCount = TicketCount(0)
        presenter.downTicketCount(ticketCount)
        assertEquals(ticketCount.count, 0)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `선택한_포지션의_날짜의_주말여부에_따라_시간이_변화한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 5, 5)
        // when
        presenter.changeTimesByDate(selectedDate)
        // then
        verifySequence {
            view.showAvailableTime(selectedDate)
        }
    }
}
