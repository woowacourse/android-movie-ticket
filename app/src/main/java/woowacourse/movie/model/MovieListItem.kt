package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface MovieListItem : Parcelable {

    val image: Int

    @Parcelize
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

    @Parcelize
    data class AdModel(
        @DrawableRes override val image: Int,
        val url: String
    ) : MovieListItem
}
