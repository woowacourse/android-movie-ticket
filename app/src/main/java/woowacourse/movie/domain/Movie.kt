package woowacourse.movie.domain

import java.io.Serializable
data class Movie(
    val picture: Int,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
) : Serializable {
    companion object {
        const val MOVIE_KEY_VALUE = "movie"
    }
}
