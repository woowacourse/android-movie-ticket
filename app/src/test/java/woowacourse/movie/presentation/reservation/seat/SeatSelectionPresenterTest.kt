package woowacourse.movie.presentation.reservation.seat

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.board.Seats
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {
    @RelaxedMockK
    private lateinit var view: SeatSelectionView

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @RelaxedMockK
    private lateinit var navArgs: SeatSelectionNavArgs

    @InjectMockKs
    private lateinit var presenter: SeatSelectionPresenter

    @Test
    fun `영화 좌석 보드를 불러오는 걸 성공하면, view 가 영화 타이틀과 영화 좌석을 보여준다`() {
        // given
        val board = stubSeatBoard()
        val idSlot = slot<Long>()
        val dateTimeSlot = slot<LocalDateTime>()
        val headCountSlot = slot<Int>()
        val titleSlot = slot<String>()
        val boardSlot = slot<SeatBoardUiModel>()
        every {
            repository.screenSeats(
                capture(idSlot),
                capture(headCountSlot),
                capture(dateTimeSlot),
            )
        } returns Result.success(board)
        every {
            view.showSeatBoard(capture(boardSlot))
        } just Runs
        every {
            view.showMovieTitle(capture(titleSlot))
        } just Runs
        // when
        presenter.loadScreenSeats()
        // then
        verify {
            repository.screenSeats(idSlot.captured, headCountSlot.captured, dateTimeSlot.captured)
        }
        verify { view.showMovieTitle(titleSlot.captured) }
        verify { view.showSeatBoard(boardSlot.captured) }
    }
}
