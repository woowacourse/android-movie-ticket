package woowacourse.movie.presentation.uimodel

sealed class SeatSelectionResult {
    data object Success : SeatSelectionResult()

    data object Failure : SeatSelectionResult()

    data object MaxCapacityReached : SeatSelectionResult()

    data object AlreadyMaxCapacityReached : SeatSelectionResult()
}
