package woowacourse.movie.presentation.dto

data class ReservationData(
    val movieTitle: String,
    val screeningDate: String,
    val reservationCount: Int,
    val totalPrice: Int,
)
