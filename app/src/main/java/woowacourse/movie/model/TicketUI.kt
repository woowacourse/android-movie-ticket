package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.seat.SeatPositionUI

@JvmInline
@Parcelize
value class TicketUI(val seatPosition: SeatPositionUI) : Parcelable
