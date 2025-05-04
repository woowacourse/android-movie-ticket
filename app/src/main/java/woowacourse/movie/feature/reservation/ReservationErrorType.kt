package woowacourse.movie.feature.reservation

sealed class ReservationErrorType {
    object NotSelectedDateTime : ReservationErrorType()

    object OverCapacityTheater : ReservationErrorType()

    object NotReceiveData : ReservationErrorType()
}
