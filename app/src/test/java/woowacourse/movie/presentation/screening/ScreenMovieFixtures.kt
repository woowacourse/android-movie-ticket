package woowacourse.movie.presentation.screening

import woowacourse.movie.model.ImageUrl
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Price
import woowacourse.movie.model.RunningTime
import woowacourse.movie.model.date.ScreenDateTimes
import woowacourse.movie.model.date.ScreeningMovie
import kotlin.time.Duration.Companion.minutes

fun stubScreenMovie(): ScreeningMovie = ScreeningMovie(
    id = 1,
    movie = stubMovie(),
    price = Price(13_000),
    screenDateTimes = ScreenDateTimes(emptyList())
)

fun stubMovie(): Movie = Movie(
    id = 1,
    title = "해리 포터와 마법사의 돌",
    description = "",
    imageUrl = ImageUrl.none(),
    runningTime = RunningTime(152.minutes),
)