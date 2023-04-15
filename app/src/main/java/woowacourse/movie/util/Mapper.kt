package woowacourse.movie.util

import androidx.annotation.DrawableRes
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDTO

fun MovieDTO.toDomain(): Movie {
    return Movie(title, playingTimes, runningTime, description)
}

fun Movie.toPresentation(@DrawableRes res: Int): MovieDTO {
    return MovieDTO(res, title, playingTimes, runningTime, description)
}
