package woowacourse.movie.view.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.Count
import woowacourse.movie.model.ticketing.BookingDateTime

@Parcelize
data class TicketingForm(
    val screeningId: Long,
    val movieTitle: String,
    val numberOfTickets: Count,
    val bookingDateTime: BookingDateTime,
) : Parcelable
