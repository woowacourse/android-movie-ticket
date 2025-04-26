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
    override fun changeSeat(
        position: Coordination,
        limit: Int,
    ) {
        val newSeat =
            Seat(
                x = position.x.value,
                y = position.y.value,
            )

        when (seats.isSelected(newSeat)) {
            true -> seats.removeSeat(newSeat)
            false -> {
                if (canSelect(limit).not()) return
                seats.addSeat(newSeat)
            }
        }

        view.showSeat(toCoordination())
    }

    private fun canSelect(limit: Int): Boolean {
        if (seats.canSelect(limit).not()) {
            view.showToast(limit)
            return false
        }
        return true
    }

    private fun toCoordination(): List<Coordination> {
        return seats
            .item
            .map {
                Coordination(Column(it.x), Row(it.y))
            }
    }
}
