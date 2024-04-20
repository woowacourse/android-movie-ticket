package woowacourse.movie.presentation.ui.reservation

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView

interface ReservationContract {
    interface View : BaseView {
        fun showReservation(reservation: Reservation)

        fun back()
    }

    interface Presenter : BasePresenter {
        fun loadReservation(id: Int)
    }
}
