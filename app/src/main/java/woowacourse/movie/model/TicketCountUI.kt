package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class TicketCountUI(val count: Int = MIN_TICKET_COUNT) : Parcelable {
    operator fun dec(): TicketCountUI =
        TicketCountUI((count - TICKET_UP_DOWN_UNIT).coerceAtLeast(MIN_TICKET_COUNT))

    operator fun inc(): TicketCountUI = TicketCountUI(count + TICKET_UP_DOWN_UNIT)

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UP_DOWN_UNIT = 1
    }
}
