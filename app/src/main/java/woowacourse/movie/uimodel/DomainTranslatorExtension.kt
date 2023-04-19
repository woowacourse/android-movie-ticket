package woowacourse.movie.uimodel

import movie.Movie
import payment.Reservation

fun movie.Movie.toMovieModel(): MovieModel =
    MovieModel(
        name = this.name,
        posterImage = null,
        screeningPeriod = this.screeningPeriod,
        runningTime = this.runningTime,
        description = this.description
    )

fun payment.Reservation.toReservationModel(): ReservationModel =
    ReservationModel(
        movie = this.movie.toMovieModel(),
        screeningDateTime = this.screeningDateTime,
        ticketCount = this.ticketCount,
        paymentAmount = this.paymentAmount,
        paymentType = this.paymentType
    )
