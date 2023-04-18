package woowacourse.movie.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.PlayingDateTimes
import woowacourse.movie.domain.movie.Movie
import java.util.TreeMap

fun MovieDTO.toDomain(): Movie {
    return Movie(
        title,
        PlayingDateTimes(startDate, endDate),
        runningTime,
        description
    )
}

fun Movie.toPresentation(@DrawableRes res: Int): MovieDTO {
    val startDate = playingDateTimes.startDate
    val endDate = playingDateTimes.endDate
    val runningTime = runningTime
    val playingTimesInfo = TreeMap(playingDateTimes.times)
    return MovieDTO(res, title, startDate, endDate, playingTimesInfo, runningTime, description)
}
