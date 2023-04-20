package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.seat.Seat

fun mapToSeat(seat: SeatModel): Seat {
    return Seat(
        seat.row.value,
        seat.column.value,
        mapToRank(seat.rank)
    )
}

fun mapToSeatModel(seat: Seat): SeatModel {
    return SeatModel(
        mapToRowModel(seat.row),
        mapToColumnModel(seat.column),
        mapToRankModel(seat.rank)
    )
}

@Parcelize
data class SeatModel(val row: RowModel, val column: ColumnModel, val rank: RankModel) : Parcelable
