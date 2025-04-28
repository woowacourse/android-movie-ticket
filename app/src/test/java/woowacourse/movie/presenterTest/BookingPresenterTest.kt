package woowacourse.movie.presenterTest

import io.mockk.called
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.BookingContract
import woowacourse.movie.booking.BookingPresenter
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.MovieScheduleGenerator
import woowacourse.movie.model.TicketCount
import java.time.Duration
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
            view.setupPage()
            view.setupDateChangeListener()
            view.countButtonHandler()
            view.confirmButtonHandler()
        }
    }

    @Test
    fun `예매_버튼을 클릭하면_카운트가_0이_아닐_경우_화면이_이동한다`() {
        presenter.onBookButtonClick(TicketCount(5))
        verifySequence {
            view.moveActivity()
        }
    }

    @Test
    fun `예매_버튼을 클릭하면_카운트가_0이_아닐_경우_화면이_이동하지_않는다`() {
        presenter.onBookButtonClick(TicketCount(0))
        verifySequence { view wasNot called }
    }

    @Test
    fun `+버튼을 클릭하면_카운트가_증가한다`() {
        val ticketCount = TicketCount(5)
        presenter.onUpButtonClick(ticketCount)
        assertEquals(ticketCount.count, 6)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `-버튼을 클릭하면_카운트가_감소한다`() {
        val ticketCount = TicketCount(5)
        presenter.onDownButtonClick(ticketCount)
        assertEquals(ticketCount.count, 4)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `카운트는_0_이하로_떨어지지_않는다`() {
        val ticketCount = TicketCount(0)
        presenter.onDownButtonClick(ticketCount)
        assertEquals(ticketCount.count, 0)
        verifySequence {
            view.changeTicketCount(ticketCount)
        }
    }

    @Test
    fun `선택한_포지션의_날짜의_주말여부에_따라_시간이_변화한다`() {
        // given
        val movieInfo =
            MovieInfo(
                posterKey = "test",
                title = "테스트",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 3),
                runningTime = Duration.ofMinutes(140),
            )
        val allDates =
            MovieScheduleGenerator
                .generateScreeningDates(movieInfo.startDate, movieInfo.endDate)
        val position = 1
        val expectedTimes = MovieScheduleGenerator.generateScreeningTimesFor(allDates[position])

        presenter.dateSpinnerSelect(movieInfo, position)

        verifySequence { view.timeSpinnerSet(expectedTimes) }
    }
}
