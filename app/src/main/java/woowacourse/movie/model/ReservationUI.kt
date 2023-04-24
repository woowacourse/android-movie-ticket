package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationUI(
    val movie: MovieUI,
    val dateTime: LocalDateTime,
    val ticketsUI: TicketsUI,
    val ticketCount: TicketCountUI
) : Parcelable
