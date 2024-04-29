package woowacourse.movie.ui

data class ReservationBrief(
    val movieTitle: String,
    val positions: List<String>,
    val screeningDateTime: String,
    val price: String
)
