package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Ticket(
    var count: Int = DEFAULT_TICKET_COUNT,
) : Parcelable {
    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return OutOfRange
        count++
        return InRange
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return OutOfRange
        count--
        return InRange
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}
