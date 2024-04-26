package woowacourse.movie.model.board

sealed interface SeatSelectionResult

data class Success(val board: SeatBoard, val updatedSeat: Seat) : SeatSelectionResult

data object Failure : SeatSelectionResult

inline fun SeatSelectionResult.onSuccess(block: (SeatBoard, Seat) -> Unit): SeatSelectionResult {
    if (this is Success) block(board, updatedSeat)
    return this
}

inline fun SeatSelectionResult.onFailure(block: () -> Unit): SeatSelectionResult {
    if (this is Failure) block()
    return this
}

fun SeatSelectionResult.isSuccess(): Boolean = this is Success

fun SeatSelectionResult.isFailure(): Boolean = this is Failure