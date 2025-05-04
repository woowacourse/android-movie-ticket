package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.completedbooking.CompletedBookingContract
import woowacourse.movie.completedbooking.CompletedBookingPresenter
import woowacourse.movie.domain.Ticket
import woowacourse.movie.fixture.RESERVATION_INFO

class CompletedBookingPresenterTest {
    private lateinit var view: CompletedBookingContract.View
    private lateinit var presenter: CompletedBookingContract.Presenter
    private lateinit var ticket: Ticket

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = CompletedBookingPresenter(view)

        every { view.showCancelDeadLine(any()) } just Runs
        every { view.showMovieTitle(any()) } just Runs
        every { view.showMovieDateTime(any()) } just Runs
        every { view.showPersonnel(any(), any()) } just Runs
        every { view.showTicketTotalPrice(any()) } just Runs


        ticket = Ticket(RESERVATION_INFO, setOf("A1", "A2"), 20_000)

    }

    @Test
    fun `set 호출 시 취소 가능 안내을 보여준다`() {
        presenter.set(ticket)

        verify {
            view.showCancelDeadLine(15)
        }
    }

    @Test
    fun `set 호출 시 영화 제목을 보여준다`() {
        presenter.set(ticket)

        verify {
            view.showMovieTitle("너와 나")
        }
    }

    @Test
    fun `set 호출 시 상영 날짜와 시간을 보여준다`() {
        presenter.set(ticket)

        verify {
            view.showMovieDateTime("2025.4.30. 14:00")
        }
    }

    @Test
    fun `set 호출 시 예매 인원을 보여준다`() {
        presenter.set(ticket)

        verify {
            view.showPersonnel(2, "A1, A2")
        }
    }

    @Test
    fun `set 호출 시 총 가격을 보여준다`() {
       presenter.set(ticket)

        verify {
            view.showTicketTotalPrice("20,000")
        }
    }
}