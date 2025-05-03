package woowacourse.movie.util.mapper

import woowacourse.movie.domain.model.seat.Col
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.ui.model.seat.ColUiModel
import woowacourse.movie.ui.model.seat.RowUiModel
import woowacourse.movie.ui.model.seat.SeatUiModel
import woowacourse.movie.ui.model.seat.SeatsUiModel
import java.util.SortedSet

object SeatsModelMapper {
    fun toModel(seatsUiModel: SeatsUiModel): Seats {
        return Seats(
            availableSelectCount = seatsUiModel.availableSelectCount.toInt(),
            _reservedSeats = seatsUiModel.reservedSeats.toSeatElements(),
            _reservingSeats = seatsUiModel.reservingSeats.toSeatElements(),
        )
    }

    fun toUi(seats: Seats): SeatsUiModel {
        return SeatsUiModel(
            availableSelectCount = seats.availableSelectCount.toString(),
            reservedSeats = seats.reservedSeats.toSeatUiElements(),
            reservingSeats = seats.reservingSeats.toSeatUiElements(),
        )
    }

    private fun Set<SeatUiModel>.toSeatElements(): MutableSet<Seat> {
        return this.map { uiSeat -> uiSeat.toSeat() }.toMutableSet()
    }

    private fun SeatUiModel.toSeat(): Seat {
        return Seat(
            row = this.row.toRow(),
            col = this.col.toCol(),
        )
    }

    private fun RowUiModel.toRow(): Row {
        return Row(this.row.first())
    }

    private fun ColUiModel.toCol(): Col {
        return Col(this.col.toInt())
    }

    private fun Set<Seat>.toSeatUiElements(): SortedSet<SeatUiModel> {
        return this
            .map { seat -> seat.toUiSeat() }
            .toCollection(
                sortedSetOf(
                    compareBy<SeatUiModel> { it.row.row }
                        .thenBy { it.col.col.toInt() },
                ),
            )
    }

    private fun Seat.toUiSeat(): SeatUiModel {
        return SeatUiModel(
            row = this.row.toUiRow(),
            col = this.col.toUiCol(),
        )
    }

    private fun Row.toUiRow(): RowUiModel {
        return RowUiModel(this.value.toString())
    }

    private fun Col.toUiCol(): ColUiModel {
        return ColUiModel(this.value.toString())
    }
}
