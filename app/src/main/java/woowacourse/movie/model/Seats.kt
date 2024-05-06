package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Seats(
    var ticketCount: Int = DEFAULT_TICKET_COUNT,
    val value: MutableList<String> = mutableListOf(),
) : Parcelable {
    fun increaseCount() {
        if (ticketCount < MAX_TICKET_COUNT) ticketCount++
    }

    fun decreaseCount() {
        if (ticketCount > MIN_TICKET_COUNT) ticketCount--
    }

    fun isValidate(): Boolean = value.size == ticketCount

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}
