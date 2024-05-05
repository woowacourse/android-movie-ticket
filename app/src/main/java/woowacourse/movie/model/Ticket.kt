package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Ticket(
    var ticketCount: Int = DEFAULT_TICKET_COUNT,
) : Parcelable {
    fun increaseCount() {
        if (ticketCount < MAX_TICKET_COUNT) ticketCount++
    }

    fun decreaseCount() {
        if (ticketCount > MIN_TICKET_COUNT) ticketCount--
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}
