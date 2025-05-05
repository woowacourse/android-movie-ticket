package woowacourse.movie.domain.model

sealed class SeatSelectionResult {
    data class Success(
        val selectedSeat: MovieSeat,
    ) : SeatSelectionResult()

    object ExceedCountFailure : SeatSelectionResult()
}
