package woowacourse.movie.feature.mapper

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieSeatUiModel
import woowacourse.movie.feature.model.MovieTimeUiModel
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.model.SeatTypeUiModel
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
        totalPrice = totalPrice.value,
    )

fun BookingInfoUiModel.toDomain(): BookingInfo {
    val previousDate = date
    val previousMovieTime = movieTime
    val previousTicketCount = ticketCount
    val selectedSeats = selectedSeats

    return BookingInfo(
        movie = movie.toDomain(),
    ).apply {
        updateDate(previousDate.toDomain())
        updateMovieTime(previousMovieTime.toDomain())
        increaseTicketCount(previousTicketCount)
        addSeats(selectedSeats.map { it.toDomain() }.toSet())
    }
}

fun MovieTime.toUi(): MovieTimeUiModel {
    val hour = if (value.hour == 0) 24 else value.hour
    return MovieTimeUiModel(hour, value.minute)
}

fun MovieTimeUiModel.toDomain(): MovieTime {
    val hour = if (hour == 24) 0 else hour
    return MovieTime(hour, minute)
}

fun MovieDate.toUi(): MovieDateUiModel =
    MovieDateUiModel(
        year = value.year,
        month = value.monthValue,
        day = value.dayOfMonth,
    )

fun MovieDateUiModel.toDomain(): MovieDate = MovieDate(year, month, day)

fun MovieSeatUiModel.toDomain(): MovieSeat = MovieSeat(row, column)

fun MovieSeat.toUi(): MovieSeatUiModel = MovieSeatUiModel(row, column, SeatTypeUiModel.valueOf(seatType.name))
