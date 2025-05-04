package woowacourse.movie.feature.seatSelect

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatCol
import woowacourse.movie.model.ticket.seat.SeatRow
import woowacourse.movie.model.ticket.seat.Seats

@Parcelize
data class SeatsData(
    val seatsIndexes: List<SeatIndexData>,
) : Parcelable {
    fun toSeatList(): List<Seat> = seatsIndexes.map { Seat(SeatRow(it.row), SeatCol(it.col)) }

    companion object {
        fun getEmptySeatsData(): SeatsData = SeatsData(listOf())

        fun fromSeats(seats: Seats): SeatsData = SeatsData(seats.seats.map { SeatIndexData(it.row.index, it.col.index) })
    }
}
