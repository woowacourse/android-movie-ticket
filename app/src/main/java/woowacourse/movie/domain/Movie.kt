package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val image: Int,
    val title: String,
    val date: Date,
    val time: Int,
) : Parcelable {
    companion object {
        const val KEY_MOVIE = "movie"
    }
}
