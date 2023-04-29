package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class TicketState(
    val movieState: MovieState,
    val dateTime: LocalDateTime,
    val seatPositionState: SeatPositionState,
    val discountedMoneyState: MoneyState
) : Parcelable
