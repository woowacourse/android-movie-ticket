package woowacourse.movie.util

import domain.movie.Movie
import woowacourse.movie.model.DisplayItem

fun Movie.toDomainModel(posterImage: Int?) = DisplayItem.MovieInfo(
    movieName.value,
    posterImage,
    screeningPeriod.startDate.value,
    screeningPeriod.endDate.value,
    screeningPeriod.getScreeningDates().map { it.value },
    runningTime.value,
    description
)
