package woowacourse.movie.presentation.reservation.result

data class ReservationResultUiModel(
    val title: String,
    val cancelDeadLine: Int,
    val date: String,
    val headCount: Int,
    val totalPrice: Int,
    val seats: String,
)
