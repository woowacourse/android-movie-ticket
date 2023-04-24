package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.mapper.toTicketCount
import woowacourse.movie.model.mapper.toTicketCountUI

@JvmInline
@Parcelize
value class TicketCountUI(val count: Int = MIN_TICKET_COUNT) : Parcelable {
    fun decreaseTicketCount(): TicketCountUI = toTicketCount().dec().toTicketCountUI()
    fun increaseTicketCount(): TicketCountUI = toTicketCount().inc().toTicketCountUI()

    companion object {
        private const val MIN_TICKET_COUNT = 0
    }
}
