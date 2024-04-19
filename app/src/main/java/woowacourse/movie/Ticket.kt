package woowacourse.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
    val personCount: Int,
    val movie: Movie
) : Parcelable {
    fun calculateTicketPrice(): Int {
        return personCount * TICKET_PRICE
    }

    companion object {
        private const val TICKET_PRICE = 10000
    }
}
