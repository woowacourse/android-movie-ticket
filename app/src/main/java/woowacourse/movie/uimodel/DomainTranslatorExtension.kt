package woowacourse.movie.uimodel

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.payment.Reservation

fun Movie.toMovieModel(): MovieModel =
    MovieModel(
        name = this.name,
        posterImage = null,
        screeningPeriod = this.screeningPeriod,
        runningTime = this.runningTime,
        description = this.description
    )

fun Reservation.toReservationModel(): ReservationModel =
    ReservationModel(
        movie = this.movie.toMovieModel(),
        screeningDateTime = this.screeningDateTime,
        ticketCount = this.ticketCount,
        paymentAmount = this.paymentAmount,
        paymentType = this.paymentType
    )
