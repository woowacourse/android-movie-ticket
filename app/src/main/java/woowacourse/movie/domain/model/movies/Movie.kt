package woowacourse.movie.domain.model.movies

import woowacourse.movie.domain.model.booking.ScreeningDate
import java.util.UUID

class Movie(
    val id: Long = UUID.randomUUID().leastSignificantBits,
    val title: String,
    val posterResource: String,
    val releaseDate: ScreeningDate,
    val runningTime: Int,
)
