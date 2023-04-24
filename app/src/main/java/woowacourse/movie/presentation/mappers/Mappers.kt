package woowacourse.movie.presentation.mappers

import woowacourse.movie.R
import woowacourse.movie.data.MovieDrawableData
import woowacourse.movie.domain.model.tools.Movie
import woowacourse.movie.domain.model.tools.Ticket
import woowacourse.movie.domain.model.tools.seat.Seat
import woowacourse.movie.presentation.model.MovieModel
import woowacourse.movie.presentation.model.TicketModel

fun MovieModel.toDomainModel() = Movie(
    id = id,
    title = title,
    screeningStartDate = screeningStartDate,
    screeningEndDate = screeningEndDate,
    runningTime = runningTime,
    description = description,
)

fun Movie.toPresentation() = MovieModel(
    id = id,
    title = title,
    screeningStartDate = screeningStartDate,
    screeningEndDate = screeningEndDate,
    runningTime = runningTime,
    description = description,
    thumbnail =
    MovieDrawableData.getMovieThumbnail(id) ?: R.drawable.default_thumbnail,
    poster =
    MovieDrawableData.getMoviePoster(id) ?: R.drawable.default_poster,
)

fun Ticket.toPresentation() = TicketModel(
    movieId = movieId,
    bookedDateTime = bookedDateTime,
    count = count,
    paymentMoney = getPaymentMoney().value,
    seats = seats.value.map { it.formatLocation() },
)

private fun Seat.formatLocation() = "${location.row}${(location.number + 1)}"
