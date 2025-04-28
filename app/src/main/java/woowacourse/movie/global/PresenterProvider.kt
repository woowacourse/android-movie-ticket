package woowacourse.movie.global

import woowacourse.movie.activity.main.MainContract
import woowacourse.movie.activity.main.MainPresenter
import woowacourse.movie.activity.reservation.ReservationContract
import woowacourse.movie.activity.reservation.ReservationPresenter
import woowacourse.movie.activity.reservation.ReservationSeatContract
import woowacourse.movie.activity.reservation.ReservationSeatPresenter

object PresenterProvider {
    fun mainPresenter(view: MainContract.View): MainContract.Presenter = MainPresenter(view)

    fun reservationPresenter(view: ReservationContract.View): ReservationContract.Presenter = ReservationPresenter(view)

    fun reservationSeatPresenter(view: ReservationSeatContract.View): ReservationSeatContract.Presenter = ReservationSeatPresenter(view)
}
