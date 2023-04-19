package domain

import java.time.LocalDateTime

data class Seat(
    val position: Position,
    val price: TicketPrice,
) {

    fun applyPolicyPrice(dateTime: LocalDateTime): Int {
        return price.applyPolicy(dateTime).price
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seat) return false
        return position == other.position
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }
}
