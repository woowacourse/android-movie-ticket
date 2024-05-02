package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.MovieDateTime
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieDateModel(
    val screeningDate: LocalDate,
    val screeningTime: LocalTime,
) : Serializable

fun MovieDateTime.toMovieDateModel(): MovieDateModel {
    return MovieDateModel(
        screeningDate = currentDate,
        screeningTime = currentTime,
    )
}
