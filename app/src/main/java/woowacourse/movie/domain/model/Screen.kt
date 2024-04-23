package woowacourse.movie.domain.model

data class Screen(
    val id: Int,
    val movie: Movie,
    val startDate: String,
    val endDate: String,
    val selectableDates: List<String>,
    val selectableTimes: List<String>,
)
