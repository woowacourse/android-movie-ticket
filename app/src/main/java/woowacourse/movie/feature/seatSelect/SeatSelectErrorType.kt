package woowacourse.movie.feature.seatSelect

sealed class SeatSelectErrorType {
    object OverBooking : SeatSelectErrorType()

    object NotReceiveData : SeatSelectErrorType()
}
