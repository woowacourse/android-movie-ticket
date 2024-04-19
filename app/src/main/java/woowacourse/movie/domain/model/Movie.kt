package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Movie(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: LocalDateTime,
    val runningTime: Int,
    val description: String,
) : Serializable {
    companion object {
        const val KEY_NAME_MOVIE = "movie"
    }
}
