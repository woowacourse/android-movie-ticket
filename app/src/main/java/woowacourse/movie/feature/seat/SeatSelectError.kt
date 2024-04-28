package woowacourse.movie.feature.seat

sealed interface SeatSelectError {
    data object InvalidReceivedMovieId : SeatSelectError

    data object InvalidReceivedScreeningDateTime : SeatSelectError

    data object InvalidReceivedReservationCount : SeatSelectError
}
