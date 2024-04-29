package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.presenter.contract.TicketingResultContract
import woowacourse.movie.view.state.TicketingResult
import woowacourse.movie.view.utils.ErrorMessage
import java.time.LocalDate
import java.time.LocalTime

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
        every { view.assignInitialView(any()) } just runs
        // when
        val ticketingResult =
            TicketingResult(
                movieTitle = "해리 포터와 마법사의 돌",
                numberOfTickets = 1,
                date = LocalDate.of(2024, 1, 1),
                time = LocalTime.of(11, 0),
                seats = listOf(BookingSeat(0, 0, SeatClass.B)),
                price = 10000,
            )
        presenter.initializeTicketingResult(ticketingResult)
        // then
        verify {
            view.assignInitialView(ticketingResult)
        }
    }

    @Test
    fun `상영_결과가_존재하지_않으면_예매_결과를_표출하지_못하고_토스트_메시지를_출력한다`() {
        // given
        every { view.showToastMessage(any()) } just runs
        // when
        presenter.initializeTicketingResult(null)
        // then
        verify {
            view.showToastMessage(ErrorMessage.ERROR_INVALID_SCREENING_ID)
        }
    }
}
