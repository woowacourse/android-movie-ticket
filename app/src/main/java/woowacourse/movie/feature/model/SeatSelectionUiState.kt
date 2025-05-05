package woowacourse.movie.feature.model

sealed class SeatSelectionUiState {
    data class Success(
        val selectedSeat: MovieSeatUiModel,
    ) : SeatSelectionUiState()

    object ExceedCountFailure : SeatSelectionUiState()
}
