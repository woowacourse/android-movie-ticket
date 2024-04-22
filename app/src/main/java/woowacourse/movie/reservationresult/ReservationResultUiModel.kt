package woowacourse.movie.reservationresult

data class ReservationResultUiModel(
    val title: String,
    val cancelDeadLine: Int,
    val date: String,
    val headCount: Int,
    val totalPrice: Int,
)
