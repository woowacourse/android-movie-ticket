package woowacourse.movie.uimodel

import movie.Movie
import movie.Name
import movie.ScreeningPeriod
import java.io.Serializable

data class MovieModel(
    val name: movie.Name,
    val posterImage: Int?,
    val screeningPeriod: movie.ScreeningPeriod,
    val runningTime: Int,
    val description: String
) : Serializable {

    fun toDomainModel(): movie.Movie =
        movie.Movie(
            name = name,
            screeningPeriod = screeningPeriod,
            runningTime = runningTime,
            description = description
        )

    companion object {
        const val MOVIE_INTENT_KEY = "movie"
    }
}
