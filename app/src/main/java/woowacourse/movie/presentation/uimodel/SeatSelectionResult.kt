package woowacourse.movie.presentation.uimodel

sealed class SeatSelectionResult {
    object Success : SeatSelectionResult()
    object Failure : SeatSelectionResult()
    object MaxCapacityReached : SeatSelectionResult()
}
