package woowacourse.movie.feature.mapper

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.SeatType
import woowacourse.movie.domain.model.TicketCount
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieSeatUiModel
import woowacourse.movie.feature.model.MovieTimeUiModel
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.model.SeatTypeUiModel
import woowacourse.movie.resource.getPosterImage

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
        date = selectedDate.toUi(),
        movieTime = selectedTime.toUi(),
        ticketCount = currentTicketCount,
        totalPrice = totalPrice.value,
        selectedSeats = selectedSeats.map { it.toUi() }.toSet(),
    )

fun BookingInfoUiModel.toDomain(): BookingInfo =
    BookingInfo(
        movie = movie.toDomain(),
        date = date.toDomain(),
        time = movieTime.toDomain(),
        seats = MovieSeats(selectedSeats.map { it.toDomain() }.toSet()),
        ticketCount = TicketCount(ticketCount),
    )

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

fun MovieSeatUiModel.toDomain(): MovieSeat = MovieSeat(row, column, SeatType.from(seatType.name, row), isSelected)

fun MovieSeat.toUi(): MovieSeatUiModel = MovieSeatUiModel(row, column, SeatTypeUiModel.from(seatType.name), isSelected)
