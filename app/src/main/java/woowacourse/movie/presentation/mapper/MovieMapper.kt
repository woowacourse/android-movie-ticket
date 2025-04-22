package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.presentation.model.BookingInfoUiModel
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.getPosterImage

fun Movie.toUi(): MovieUiModel =
    MovieUiModel(
        title = title,
        startDate = startDate,
        endDate = endDate,
        runningTime = runningTime,
        poster = getPosterImage(title),
    )

fun MovieUiModel.toDomain(): Movie =
    Movie(
        title = title,
        startDate = startDate,
        endDate = endDate,
        runningTime = runningTime,
    )

fun BookingInfo.toUi(): BookingInfoUiModel =
    BookingInfoUiModel(
        movie = movie.toUi(),
        date = date,
        movieTime = movieTime,
        ticketCount = ticketCount,
        eachPrice = eachPrice,
        totalPrice = totalPrice,
    )

fun BookingInfoUiModel.toDomain(): BookingInfo =
    BookingInfo(
        movie = movie.toDomain(),
        date = date,
        movieTime = movieTime,
        ticketCount = ticketCount,
        eachPrice = eachPrice,
    )

fun MovieTime.toUi(): String {
    val hour = if (value.hour == 0) 24 else value.hour
    return "%02d:%02d".format(hour, value.minute)
}
