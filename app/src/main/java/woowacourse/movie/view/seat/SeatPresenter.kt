package woowacourse.movie.view.seat

import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatPresenter(
    private val view: SeatContract.View,
    private val seats: Seats,
) : SeatContract.Presenter {
    override fun changeSeat(position: Coordination) {
        val newSeat =
            Seat(
                x = position.x.value,
                y = position.y.value,
            )

        when (seats.isSelected(newSeat)) {
            true -> seats.removeSeat(newSeat)
            false -> seats.addSeat(newSeat)
        }
        val coordination =
            seats
                .item
                .map {
                    Coordination(Column(it.x), Row(it.y))
                }

        view.showSeat(coordination)
    }
}
