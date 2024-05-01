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
import woowacourse.movie.model.board.Position
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatUiModel
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

    @Test
    fun `영화 좌석 선택했을 때, 좌석과 총 가격을 보여준다`() {
        // given
        initBoard()
        val (x, y) = Position(0, 0)
        val selectedSeatSlot = slot<SeatUiModel>()
        val totalPriceSlot = slot<Long>()
        every { view.showSeat(capture(selectedSeatSlot)) } just Runs
        every { view.showTotalPrice(capture(totalPriceSlot)) } just Runs
        // when
        presenter.selectSeat(x, y)
        //then
        verify { view.showSeat(selectedSeatSlot.captured) }
        verify { view.showTotalPrice(totalPriceSlot.captured) }
        verify(exactly = 0) { view.showSelectionError() }
    }

    @Test
    fun `인원수 만큼 좌석을 모두 선택했을 때, 예매 버튼을 활성화시킨다`() {
        // given
        initBoard()
        val (x, y) = Position(0, 0)
        val (x1, y1) = Position(1, 0)
        // when
        presenter.selectSeat(x, y)
        presenter.selectSeat(x1, y1)
        // then
        verify(exactly = 1) {
            view.activateReservationButton()
        }
    }


    @Test
    fun `영화 좌석에 실패했을 때, 선택 에러 화면을 보여준다`() {
        // given
        initBoard()
        val (x, y) = banPosition()
        every { view.showSelectionError() } just Runs
        // when
        presenter.selectSeat(x, y)
        //then
        verify(exactly = 0) { view.showSeat(any()) }
        verify(exactly = 0) { view.showTotalPrice(any()) }
        verify { view.showSelectionError() }
    }

    @Test
    fun `영화 좌석의 좌석들을 복구한 후, 영화 좌석,영화 이름, 가격을 보여준다`() {
        val uiState = stubSeatSelectionUiState()
        val boardSlot = slot<SeatBoardUiModel>()
        val titleSlot = slot<String>()
        val totalPriceSlot = slot<Long>()
        every { view.showSeatBoard(capture(boardSlot)) } just Runs
        every { view.showMovieTitle(capture(titleSlot)) } just Runs
        every { view.showTotalPrice(capture(totalPriceSlot)) } just Runs
        // when
        presenter.restoreState(uiState)
        // then
        verify { view.showSeatBoard(boardSlot.captured) }
        verify { view.showMovieTitle(titleSlot.captured) }
        verify { view.showTotalPrice(totalPriceSlot.captured) }
    }

    private fun initBoard() {
        val board = stubSeatBoard()
        every {
            repository.screenSeats(
                any(),
                any(),
                any(),
            )
        } returns Result.success(board)
        presenter.loadScreenSeats()
    }
}
