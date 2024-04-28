package woowacourse.movie.feature.complete

sealed interface MovieReservationCompleteError {
    data object InvalidReceivedTicketId : MovieReservationCompleteError
}
