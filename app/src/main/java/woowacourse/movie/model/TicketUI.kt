package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class TicketUI(val count: Int = MIN_TICKET_COUNT) : Parcelable {
    companion object {
        private const val MIN_TICKET_COUNT = 1
    }
}
