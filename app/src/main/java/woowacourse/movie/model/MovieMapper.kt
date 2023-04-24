package woowacourse.movie.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.PlayingDateTimes
import java.util.TreeMap

fun MovieModel.toDomain(): Movie {
    return Movie(
        title,
        PlayingDateTimes(startDate, endDate),
        runningTime,
        description
    )
}

fun Movie.toPresentation(@DrawableRes res: Int): MovieModel {
    val startDate = playingDateTimes.times.keys.min()
    val endDate = playingDateTimes.times.keys.max()
    val runningTime = runningTime
    val playingTimesInfo = TreeMap(playingDateTimes.times)
    return MovieModel(res, title, startDate, endDate, playingTimesInfo, runningTime, description)
}
