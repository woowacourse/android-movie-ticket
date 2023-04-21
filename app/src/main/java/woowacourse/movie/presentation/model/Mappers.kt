package woowacourse.movie.presentation.model

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.util.formatLocationToString

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
    thumbnail = MovieDrawableData.getMovieThumbnail(id),
    poster = MovieDrawableData.getMoviePoster(id),
)

fun Ticket.toPresentation() = TicketModel(
    movieId = movieId,
    bookedDateTime = bookedDateTime,
    count = count,
    paymentMoney = getPaymentMoney().value,
    seats = seats.map { it.location }.formatLocationToString(),
)
