package woowacourse.movie.uiModels.reservation

data class ReservationBrief(
    val movieTitle: String,
    val positions: String,
    val screeningDateTime: String,
    val price: String,
)
