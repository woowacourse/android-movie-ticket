package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.MovieDate
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

class MovieDateModel(
    val screeningDate: LocalDate,
    val screeningTime: LocalDateTime,
) : Serializable

fun MovieDate.toMovieDateModel(): MovieDateModel {
    return MovieDateModel(
        screeningDate = currentDate,
        screeningTime = currentTime,
    )
}
