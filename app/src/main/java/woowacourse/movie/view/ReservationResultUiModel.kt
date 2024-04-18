package woowacourse.movie.view

data class ReservationResultUiModel(
    val title: String,
    val cancelDeadLine: Int,
    val date: String,
    val headCount: Int,
    val totalPrice: Int,
)
