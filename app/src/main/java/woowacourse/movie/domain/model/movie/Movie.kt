package woowacourse.movie.domain.model.movie

import woowacourse.movie.domain.policy.MovieContent
import java.io.Serializable

data class Movie(
    val title: String,
    val screeningDate: ScreeningDate,
    val runningMinute: RunningMinute,
    val poster: String,
) : Serializable, MovieContent
