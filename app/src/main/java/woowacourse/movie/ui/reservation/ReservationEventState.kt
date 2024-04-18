package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.model.Reservation

sealed interface ReservationEventState {
    sealed interface Success : ReservationEventState {
        data class ReservationLoading(val reservation: Reservation) : Success
    }

    sealed interface Failure : ReservationEventState {
        data class GoToBack(val message: String) : Failure

        data class UnexpectedFinish(val message: String) : Failure
    }
}
