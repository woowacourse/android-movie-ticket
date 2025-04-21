package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Ticket(
    val movie: Movie,
    val showtime: LocalDateTime,
    val count: Int,
) : Parcelable {
    init {
        require(count >= MINIMUM_TICKET_COUNT)
    }

    fun totalPrice(): Int = count * TICKET_PRICE

    fun increment(): Ticket {
        return this.copy(count = count + 1)
    }

    fun decrement(): Ticket {
        return when (count) {
            MINIMUM_TICKET_COUNT -> this
            else -> return this.copy(count = count - 1)
        }
    }

    companion object {
        const val MINIMUM_TICKET_COUNT = 1
        private const val TICKET_PRICE = 13_000
    }
}
