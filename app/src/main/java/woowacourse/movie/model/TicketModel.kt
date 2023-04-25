package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val ticketDateFormat: String = "yyyy.M.d"
const val ticketTimeFormat: String = "HH:mm"

@Parcelize
data class TicketModel(
    val reservationInfoModel: ReservationInfoModel,
    val price: Int,
    val seats: List<SeatModel>
) : Parcelable
