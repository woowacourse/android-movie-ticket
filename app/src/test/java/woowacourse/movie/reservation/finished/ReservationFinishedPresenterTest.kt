package woowacourse.movie.reservation.finished

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReservationFinishedPresenterTest {

    private lateinit var view: ReservationFinishedContract.View
    private lateinit var presenter: ReservationFinishedContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationFinishedContract.View>()
        presenter = ReservationFinishedPresenter(view, 0, 1)
    }

    @Test
    fun `예매할 영화 정보를 표시한다`() {
        every { view.showMovieInformation(any()) } just runs

        presenter.loadMovieInformation()

        verify { view.showMovieInformation(any()) }
    }

    @Test
    fun `영화 예매 정보를 표시한다`() {
        every { view.showReservationHistory(any(), any()) } just runs

        presenter.loadReservationInformation()

        verify { view.showReservationHistory(any(), any()) }
    }
}