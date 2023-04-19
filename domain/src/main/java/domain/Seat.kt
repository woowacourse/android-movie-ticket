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
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (position != other.position) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
