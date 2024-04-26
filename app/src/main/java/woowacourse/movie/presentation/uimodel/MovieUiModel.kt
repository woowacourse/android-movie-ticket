package woowacourse.movie.presentation.uimodel

import woowacourse.movie.domain.model.Movie
import java.time.format.DateTimeFormatter

data class MovieUiModel(
    val movieId: Int,
    val posterImageId: Int,
    val title: String,
    val screeningStartDate: String,
    val screeningEndDate: String,
    val runningTime: Int,
    val summary: String,
) {
    constructor(movie: Movie) : this(
        movie.movieId,
        movie.posterImageId,
        movie.title,
        movie.screeningInfo.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        movie.screeningInfo.endDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        movie.screeningInfo.runningTime,
        movie.summary,
    )
}
