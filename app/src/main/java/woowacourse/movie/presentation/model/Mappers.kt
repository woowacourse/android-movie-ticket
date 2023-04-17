package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Ticket

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
)

fun TicketModel.toDomainModel() = Ticket(
    movieId = movieId,
    bookedDateTime = bookedDateTime,
    count = count,
)
