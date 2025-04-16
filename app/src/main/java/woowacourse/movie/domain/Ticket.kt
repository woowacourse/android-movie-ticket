package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class Ticket(
    val movie: Movie,
    private val showtime: LocalDateTime,
    private val count: Int,
) : Parcelable {
    init {
        require(count >= MINIMUM_TICKET_COUNT)
    }

    fun totalPrice(): Int = count * TICKET_PRICE

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
        private const val TICKET_PRICE = 13_000
    }
}
