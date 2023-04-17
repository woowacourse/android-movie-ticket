package woowacourse.movie.domain.movie

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.PlayingTimes

fun MovieDTO.toDomain(): Movie {
    return Movie(title, PlayingTimes(startDate, endDate), runningTime, description)
}

fun Movie.toPresentation(@DrawableRes res: Int): MovieDTO {
    val startDate = playingTimes.startDate
    val endDate = playingTimes.endDate
    val runningTime = runningTime
    val playingTimes = playingTimes.times
    return MovieDTO(res, title, startDate, endDate, playingTimes, runningTime, description)
}
