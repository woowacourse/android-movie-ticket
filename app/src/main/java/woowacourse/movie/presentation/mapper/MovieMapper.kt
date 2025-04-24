package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieDates
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.presentation.model.BookingInfoUiModel
import woowacourse.movie.presentation.model.MovieDateUiModel
import woowacourse.movie.presentation.model.MovieTimeUiModel
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.getPosterImage

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
    return MovieTimeUiModel("%02d:%02d".format(hour, value.minute))
}

fun MovieTimeUiModel.toDomain(): MovieTime =
    value
        .split(":")
        .let { time -> MovieTime(time[0].toInt(), time[1].toInt()) }

fun MovieDate.toUi(): MovieDateUiModel =
    MovieDateUiModel(
        year = value.year,
        month = value.monthValue,
        day = value.dayOfMonth,
    )

fun MovieDateUiModel.toDomain(): MovieDate = MovieDate(year, month, day)

fun MovieDates.toUi() = value.map { it.toUi() }
