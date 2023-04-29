package woowacourse.movie.uimodel

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.seat.Seat

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
        seats = this.seats.map { it.toSeatModel() },
        paymentAmount = this.paymentAmount,
        paymentType = this.paymentType
    )

fun Seat.toSeatModel(): SeatModel =
    SeatModel(
        row = this.row,
        column = this.column,
        seatType = this.seatType
    )
