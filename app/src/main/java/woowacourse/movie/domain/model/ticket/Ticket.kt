package woowacourse.movie.domain.model.ticket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.movie.Discountable

@JvmInline
@Parcelize
value class Ticket(val count: Int = MIN_TICKET_COUNT) : Parcelable {
    init {
        require(count >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    @JvmOverloads
    fun calculateTotalPrice(
        discountables: List<Discountable>,
        ticketPrice: Int = DEFAULT_TICKET_PRICE,
    ): Int {
        var discountedPrice = ticketPrice
        discountables.forEach { discountedPrice = it.discount(discountedPrice) }

        return discountedPrice * count
    }

    operator fun dec(): Ticket = if (count > MIN_TICKET_COUNT) {
        Ticket(count - TICKET_UP_DOWN_UNIT)
    } else {
        this
    }

    operator fun inc(): Ticket = Ticket(count + TICKET_UP_DOWN_UNIT)

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UP_DOWN_UNIT = 1
        private const val DEFAULT_TICKET_PRICE = 13_000

        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}
