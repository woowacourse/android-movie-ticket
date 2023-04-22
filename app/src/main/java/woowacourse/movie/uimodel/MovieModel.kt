package woowacourse.movie.uimodel

import java.io.Serializable

data class MovieModel(
    val name: movie.Name,
    val posterImage: Int?,
    val screeningPeriod: movie.ScreeningPeriod,
    val runningTime: Int,
    val description: String
) : Serializable {

    companion object {
        const val MOVIE_INTENT_KEY = "movie"
    }
}
