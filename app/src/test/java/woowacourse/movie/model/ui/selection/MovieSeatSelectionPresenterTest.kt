package woowacourse.movie.model.ui.selection

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.ui.selection.MovieSeatSelectionContract
import woowacourse.movie.ui.selection.MovieSeatSelectionPresenter

class MovieSeatSelectionPresenterTest {
    private lateinit var presenter: MovieSeatSelectionPresenter
    private lateinit var view: MovieSeatSelectionContract.View

    @BeforeEach
    fun setUp() {
        view = mockk<MovieSeatSelectionContract.View>(relaxed = true)
        presenter = MovieSeatSelectionPresenter(view, UserTicketsImpl)
    }

    @Test
    fun `영화관 좌석정보를 불러온다`() {
        // given
        every { view.showTheater(any(), any()) }

        // when
        presenter.loadTheater()

        // then
        verify { view.showTheater(any(), any()) }
    }

    @Test
    fun `좌석을 선택한다`() {
        // given
        every { view.showSelectedSeat(any(), any()) }
        every { view.updateSelectCompletion(any()) }
        every { view.showReservationTotalAmount(any()) }

        // when
        presenter.selectSeat(1, 1)

        // then
        verify { view.showSelectedSeat(any(), any()) }
        verify { view.updateSelectCompletion(any()) }
        verify { view.showReservationTotalAmount(any()) }
    }

    @Test
    fun `영화 타이틀을 가져온다`() {
        // given
        every { view.showMovieTitle(any()) }

        // when
        presenter.loadMovieTitle(0L)

        // then
        verify { view.showMovieTitle(any()) }
    }
}
