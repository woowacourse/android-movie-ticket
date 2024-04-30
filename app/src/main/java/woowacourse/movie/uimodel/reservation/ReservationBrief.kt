package woowacourse.movie.uimodel.reservation

data class ReservationBrief(
    val movieTitle: String,
    val positions: String,
    val screeningDateTime: String,
    val price: String,
)
