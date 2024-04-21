package woowacourse.movie.presentation.screen.model

import woowacourse.movie.domain.model.Movie
import java.io.Serializable

class MovieModel(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
):Serializable

fun Movie.toMovieModel(): MovieModel{
    return MovieModel(
        title = this.title,
        posterResourceId = this.posterResourceId,
        screeningDate = this.screeningDate,
        runningTime = this.runningTime,
        description = this.description,
    )
}
