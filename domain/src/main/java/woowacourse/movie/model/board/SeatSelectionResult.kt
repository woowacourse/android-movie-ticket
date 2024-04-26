package woowacourse.movie.model.board

sealed interface SeatSelectionResult

data class Success(val board: SeatBoard, val selectedSeat: Seat) : SeatSelectionResult

data object Failure : SeatSelectionResult

inline fun SeatSelectionResult.onSuccess(block: (SeatBoard, Seat) -> Unit): SeatSelectionResult {
    if (this is Success) block(board, selectedSeat)
    return this
}