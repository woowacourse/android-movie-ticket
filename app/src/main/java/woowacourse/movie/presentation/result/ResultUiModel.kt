package woowacourse.movie.presentation.result

import woowacourse.movie.domain.MovieSeat
import java.time.LocalDateTime

data class ResultUiModel(
    val movieTitle: String? = null,
    val localDateTime: LocalDateTime = LocalDateTime.now(),
    val seats: List<MovieSeat?> = mutableListOf(),
) {
    val reservationCount: Int = seats.size
    val totalPrice: Int = seats.sumOf { it?.tier?.price ?: 0 }
}
