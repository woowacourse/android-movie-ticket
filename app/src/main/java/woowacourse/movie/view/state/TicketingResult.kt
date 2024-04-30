package woowacourse.movie.view.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.ticketing.BookingSeat
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class TicketingResult(
    val movieTitle: String,
    val numberOfTickets: Int,
    val date: LocalDate,
    val time: LocalTime,
    val seats: List<BookingSeat>,
    val price: Int,
) : Parcelable
