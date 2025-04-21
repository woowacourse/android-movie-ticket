package woowacourse.movie.domain.ticket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class Ticket(
    val title: String,
    val count: Int,
    val showtime: LocalDateTime,
) : Parcelable {
    val price: Int = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE = 13_000
    }
}
