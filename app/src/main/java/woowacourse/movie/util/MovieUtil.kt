package woowacourse.movie.util

import domain.movie.Movie
import woowacourse.movie.model.MovieRecyclerItem

fun Movie.toDomainModel(posterImage: Int) = MovieRecyclerItem.MovieInfo(
    movieName.value,
    posterImage,
    screeningPeriod.startDate.value,
    screeningPeriod.endDate.value,
    screeningPeriod.getScreeningDates().map { it.value },
    runningTime.value,
    description
)
