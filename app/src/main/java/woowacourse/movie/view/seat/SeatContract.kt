package woowacourse.movie.view.seat

import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.model.coord.Coordination

interface SeatContract {
    interface View {
        fun onClickSeat(position: Coordination)

        fun showSeat(seat: List<Coordination>)
    }

    interface Presenter {
        fun changeSeat(position: Coordination)
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val seats = Seats()
            return SeatPresenter(view, seats)
        }
    }
}
