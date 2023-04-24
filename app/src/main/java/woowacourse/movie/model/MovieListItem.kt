package woowacourse.movie.model

import androidx.annotation.DrawableRes

sealed interface MovieListItem : java.io.Serializable {

    val image: Int

    data class MovieModel(
        @DrawableRes override val image: Int,
        val title: String,
        val startDate: String,
        val endDate: String,
        val runningTime: Int,
        val description: String
    ) : MovieListItem {
        companion object {
            const val MOVIE_DATE_FORMAT: String = "yyyy.M.d"
        }
    }

    data class AdModel(
        @DrawableRes override val image: Int,
        val url: String
    ) : MovieListItem
}
