package woowacourse.movie.presentation.reservation.seat

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.repository.MovieRepository


@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {
    @RelaxedMockK
    lateinit var view: SeatSelectionView

    @RelaxedMockK
    lateinit var repository: MovieRepository

    var navArgs: SeatSelectionNavArgs = stubSeatSelectionNavArgs()

    @InjectMockKs
    lateinit var presenter: SeatSelectionPresenter

    @Test
    fun `영화 좌석 보드를 불러오는 걸 성공하면, view 가 영화 타이틀과 영화 좌석을 보여준다`() {
        // given
        val board = stubSeatBoard()
        val expectedTitle = navArgs.movieTitle
        val expectedBoardUiModel = board.toUiModel()
        every { repository.screenSeats(any(), any(), any()) } returns Result.success(
            stubSeatBoard()
        )
        // when
        presenter.loadScreenSeats()
        // then
        verify { view.showMovieTitle(expectedTitle) }
        verify { view.showSeatBoard(expectedBoardUiModel) }
    }
}