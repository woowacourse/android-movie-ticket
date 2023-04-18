package woowacourse.movie.view

import java.io.Serializable

data class MovieViewData(
    val poster: ImageViewData,
    val title: String,
    val date: DateRangeViewData,
    val runningTime: Int,
    val description: String
) : Serializable {
    companion object {
        const val MOVIE_EXTRA_NAME = "movie"
    }
}
