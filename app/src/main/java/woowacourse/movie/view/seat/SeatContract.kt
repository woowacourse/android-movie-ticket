package woowacourse.movie.view.seat

import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.model.coord.Coordination

interface SeatContract {
    interface View {
        fun onClickSeat(
            position: Coordination,
            peopleCount: Int,
        )

        fun showSeat(seat: List<Coordination>)

        fun showToast(peopleCount: Int)
    }

    interface Presenter {
        fun changeSeat(
            position: Coordination,
            limit: Int,
        )
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val seats = Seats()
            return SeatPresenter(view, seats)
        }
    }
}
