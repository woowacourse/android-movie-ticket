package woowacourse.movie.model.mapper

import com.example.domain.model.Movie
import woowacourse.movie.model.MovieRes

fun MovieRes.asDomain(): Movie = Movie(imgId, title, startDate, endDate, runningTime, description)

fun Movie.asPresentation(): MovieRes =
    MovieRes(imgId, title, startDate, endDate, runningTime, description)
