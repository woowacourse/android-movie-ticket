package woowacourse.movie.view

import java.io.Serializable

data class MovieView(
    val poster: ImageView,
    val title: String,
    val date: DateRangeView,
    val runningTime: Int,
    val description: String
) : Serializable {
    companion object {
        const val MOVIE_EXTRA_NAME = "movie"
    }
}
