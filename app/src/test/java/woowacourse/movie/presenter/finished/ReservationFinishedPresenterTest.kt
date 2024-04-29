package woowacourse.movie.presenter.finished

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.model.seats.Seats
import woowacourse.movie.model.ticket.Ticket

@ExtendWith(MockKExtension::class)
class ReservationFinishedPresenterTest {
    @MockK
    private lateinit var view: ReservationFinishedContract.View
    private lateinit var presenter: ReservationFinishedContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = ReservationFinishedPresenter(view, ScreeningDao())
    }

    @Test
    fun `예매한 영화의 제목을 보여준다`() {
        every { view.showMovieInformation(any()) } just runs
        presenter.loadMovie(0)
        verify { view.showMovieInformation(any()) }
    }

    @Test
    fun `예매 내역을 보여준다`() {
        every { view.showReservationHistory(any(), any()) } just runs
        presenter.loadTicket(Ticket(), Seats())
        verify { view.showReservationHistory(any(), any()) }
    }
}
