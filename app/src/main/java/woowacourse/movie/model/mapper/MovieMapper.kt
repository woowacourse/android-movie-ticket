package woowacourse.movie.model.mapper

import com.example.domain.model.Movie
import woowacourse.movie.model.MovieState

fun MovieState.asDomain(): Movie = Movie(imgId, title, startDate, endDate, runningTime, description)

fun Movie.asPresentation(): MovieState =
    MovieState(imgId, title, startDate, endDate, runningTime, description)
