package woowacourse.movie.feature.mapper

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieDates
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieTimeUiModel
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.model.getPosterImage

fun Movie.toUi(): MovieUiModel =
    MovieUiModel(
        title = title,
        startDate = startDate.toUi(),
        endDate = endDate.toUi(),
        runningTime = runningTime,
        poster = getPosterImage(title),
    )

fun MovieUiModel.toDomain(): Movie =
    Movie(
        title = title,
        startDate = startDate.toDomain(),
        endDate = endDate.toDomain(),
        runningTime = runningTime,
    )

fun BookingInfo.toUi(): BookingInfoUiModel =
    BookingInfoUiModel(
        movie = movie.toUi(),
        date = date.toUi(),
        movieTime = movieTime.toUi(),
        ticketCount = ticketCount.value,
        eachPrice = eachPrice,
        totalPrice = totalPrice,
    )

fun MovieTime.toUi(): MovieTimeUiModel {
    val hour = if (value.hour == 0) 24 else value.hour
    return MovieTimeUiModel(hour, value.minute)
}

fun MovieTimeUiModel.toDomain(): MovieTime = MovieTime(hour, minute)

fun MovieDate.toUi(): MovieDateUiModel =
    MovieDateUiModel(
        year = value.year,
        month = value.monthValue,
        day = value.dayOfMonth,
    )

fun MovieDateUiModel.toDomain(): MovieDate = MovieDate(year, month, day)

fun MovieDates.toUi() = value.map { it.toUi() }
