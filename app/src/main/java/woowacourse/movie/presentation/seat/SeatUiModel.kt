package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.MovieSeat
import java.io.Serializable

data class SeatUiModel(
    val movieId: Long = -1,
    val movieScreenDateTimeId: Long = -1,
    val movieTitle: String? = null,
    val countThreshold: Int = 0,
    val selectedSeat: List<MovieSeat> = mutableListOf(),
) : Serializable {
    val selectedCount: Int = selectedSeat.size
    val totalPrice: Int = selectedSeat.sumOf { it.tier.price }
}
