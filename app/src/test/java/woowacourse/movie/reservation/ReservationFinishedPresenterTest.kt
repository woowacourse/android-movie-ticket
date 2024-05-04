package woowacourse.movie.reservation

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenterTest {
    private lateinit var view: ReservationFinishedContract.View
    private lateinit var presenter: ReservationFinishedContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationFinishedContract.View>()

        every {
            view.showMovieInformation(any())
            view.showReservationInformation(any(), any(), any())
            view.showReservationSchedule(any(), any())
        } just runs

        presenter = ReservationFinishedPresenter(view, 0, Ticket(), "", 0, ReservationSchedule())
    }

    @Test
    fun `예매할 영화 정보를 표시한다`() {
        every {
            view.showMovieInformation(any())
        } just runs

        presenter.loadMovieInformation()

        verify {
            view.showMovieInformation(any())
        }
    }

    @Test
    fun `예매할 영화 예매 정보를 표시한다`() {
        every {
            view.showReservationInformation(any(), any(), any())
        } just runs

        presenter.loadReservationInformation()

        verify {
            view.showReservationInformation(any(), any(), any())
        }
    }

    @Test
    fun `예매할 예매 스케줄 정보를 표시한다`() {
        every {
            view.showReservationSchedule(any(), any())
        } just runs

        presenter.loadReservationSchedule()

        verify {
            view.showReservationSchedule(any(), any())
        }
    }
}
