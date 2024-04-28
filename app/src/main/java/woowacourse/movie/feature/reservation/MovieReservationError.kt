package woowacourse.movie.feature.reservation

sealed interface MovieReservationError {
    data object InvalidReceivedMovieId : MovieReservationError

    data object ReservationCountRange : MovieReservationError
}
