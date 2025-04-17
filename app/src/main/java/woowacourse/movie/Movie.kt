package woowacourse.movie

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.ScreeningDate

class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    val releaseDate: ScreeningDate,
    val runningTime: String,
)
