package woowacourse.movie.domain.model.movies

import woowacourse.movie.domain.model.booking.ScreeningDate

class Movie(
    val title: String,
    val posterResource: Poster,
    val releaseDate: ScreeningDate,
    val runningTime: Int,
)
