package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.seat.Seat

fun SeatModel.mapToSeat(): Seat {
    return Seat(
        row.value,
        column.value,
        rank.mapToRank()
    )
}

fun Seat.mapToSeatModel(): SeatModel {
    return SeatModel(
        row.mapToRowModel(),
        column.mapToColumnModel(),
        rank.mapToRankModel()
    )
}

@Parcelize
data class SeatModel(val row: RowModel, val column: ColumnModel, val rank: RankModel) : Parcelable
