package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
) : Serializable {
    companion object {
        const val KEY_NAME_MOVIE = "movie"
    }
}
