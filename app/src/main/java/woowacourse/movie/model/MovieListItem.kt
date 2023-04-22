package woowacourse.movie.model

import androidx.annotation.DrawableRes

sealed class MovieListItem : java.io.Serializable {
    data class MovieModel(
        @DrawableRes val image: Int,
        val title: String,
        val startDate: String,
        val endDate: String,
        val runningTime: Int,
        val description: String
    ) : MovieListItem() {
        companion object {
            const val MOVIE_DATE_FORMAT: String = "yyyy.M.d"
        }
    }

    data class AdModel(
        @DrawableRes val image: Int
    ) : MovieListItem()
}
