package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class TicketPrice(val amount: Int = DEFAULT_TICKET_PRICE) : Parcelable {
    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
