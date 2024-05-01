package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.MovieSeat
import java.io.Serializable

data class SeatUiModel(
    val countThreshold: Int = 0,
    val selectedSeat: List<MovieSeat> = emptyList(),
) : Serializable {
    val selectedCount: Int = selectedSeat.size
    val totalPrice: Int = selectedSeat.sumOf { it.tier.price }

    fun select(
        movieSeat: MovieSeat,
        isSelected: Boolean,
    ): SeatUiModel? {
        return if (isSelected) deSelectSeat(movieSeat) else selectSeatInfNeeded(movieSeat)
    }

    private fun selectSeatInfNeeded(movieSeat: MovieSeat): SeatUiModel? {
        if (selectedCount < countThreshold) {
            return copy(selectedSeat = selectedSeat + movieSeat)
        }
        return null
    }

    private fun deSelectSeat(movieSeat: MovieSeat): SeatUiModel {
        return copy(
            selectedSeat = selectedSeat - movieSeat,
        )
    }
}
