package woowacourse.movie.domain

import woowacourse.movie.domain.policy.DiscountDecorator
import java.io.Serializable

@JvmInline
value class Ticket(val count: Int = MIN_TICKET_COUNT) : Serializable {
    init {
        require(count >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    fun calculateTotalPrice(
        discountDecorator: DiscountDecorator
    ): Int = discountDecorator.calculatePrice() * count

    operator fun dec(): Ticket = if (count > MIN_TICKET_COUNT) {
        Ticket(count - TICKET_UP_DOWN_UNIT)
    } else {
        this
    }

    operator fun inc(): Ticket = Ticket(count + TICKET_UP_DOWN_UNIT)

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UP_DOWN_UNIT = 1

        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}
