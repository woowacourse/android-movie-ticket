package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ui.seat.SeatRow
import java.time.LocalDateTime

@Parcelize
data class TicketUiModel(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val seat: SeatUiModel,
) : Parcelable {
    val coordinate get() = SeatRow.find(seat.position.row).name + (seat.position.column + 1).toString()
}
