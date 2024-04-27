package woowacourse.movie.presentation.reservation.seat

import android.util.Log
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.board.Position
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.SeatGrade
import woowacourse.movie.model.board.onFailure
import woowacourse.movie.model.board.onSuccess
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.seat.model.SeatSelectionUiState
import woowacourse.movie.repository.MovieRepository

class SeatSelectionPresenter(
    private val repository: MovieRepository,
    private val navArgs: SeatSelectionNavArgs,
    private val view: SeatSelectionView,
) {
    private lateinit var board: SeatBoard
    val uiState: SeatSelectionUiState
        get() = SeatSelectionUiState(board.toUiModel(), navArgs)

    fun loadScreenSeats() {
        repository.screenSeats(navArgs.screenMovieId, navArgs.selectedDateTime).onSuccess {
            board = it
            view.showMovieTitle(navArgs.movieTitle)
            view.showSeatBoard(it.toUiModel())
            Log.d("로그", "${it.totalSeats().filter { it.grade == SeatGrade.S }}")
            Log.d("로그", "${it.totalSeats().filter { it.grade == SeatGrade.A }}")
            Log.d("로그", "${it.totalSeats().filter { it.grade == SeatGrade.B }}")
        }
    }

    fun selectSeat(
        x: Int,
        y: Int,
    ) {
        board.select(Position(x, y)).onSuccess { newBoard, selectedSeat ->
            board = newBoard
            view.showSeat(selectedSeat.toUiModel())
            view.showTotalPrice(board.selectedSeatsPrice())
        }.onFailure {
            view.showSelectionError()
        }
    }

    fun completeReservation() {
        repository.reserveMovie(
            navArgs.screenMovieId,
            dateTime = navArgs.selectedDateTime,
            count = navArgs.headCount.let(::HeadCount),
            selectedSeats = board.selectedSeats,
        ).onSuccess {
            view.navigateToReservationResult(it)
        }
    }

    fun restoreState(state: SeatSelectionUiState) {
        board = state.seatBoard.toDomain()
        view.showSeatBoard(state.seatBoard)
        view.showTotalPrice(board.selectedSeatsPrice())
        view.showMovieTitle(navArgs.movieTitle)
    }
}
