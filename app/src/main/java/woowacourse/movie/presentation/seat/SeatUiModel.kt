package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.MovieSeat

data class SeatUiModel(
    val movieId: Long = -1,
    val movieScreenDateTimeId: Long = -1,
    val movieTitle: String? = null,
    val countThreshold: Int = 0,
    val selectedCount: Int = 0,
    val totalPrice: Int = 0,
    val selectedSeat: List<MovieSeat> = mutableListOf(),
)
