package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class Ticket(val count: Int = MIN_TICKET_COUNT) : Parcelable {
    companion object {
        private const val MIN_TICKET_COUNT = 1
    }
}
