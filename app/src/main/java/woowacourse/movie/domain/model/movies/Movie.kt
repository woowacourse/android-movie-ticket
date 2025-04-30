package woowacourse.movie.domain.model.movies

import woowacourse.movie.domain.model.booking.ScreeningDate
import java.io.Serializable

class Movie(
    val id: Int,
    val title: String,
    val posterResource: String,
    val releaseDate: ScreeningDate,
    val runningTime: Int,
) : Serializable
