package woowacourse.movie.uiModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import woowacourse.movie.model.SeatSelection
import java.time.format.DateTimeFormatter

@Parcelize
data class TicketUIModel(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String>,
    val count: Int,
    val money: Int,
) : Parcelable {
    companion object {
        fun from(seatSelection: SeatSelection): TicketUIModel {
            val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
            return TicketUIModel(
                seatSelection.titleText,
                seatSelection.dateValue.format(formatter),
                seatSelection.timeValue.toString(),
                seatSelection.seats.map { it.tag },
                seatSelection.count,
                seatSelection.money,
            )
        }
    }
}
