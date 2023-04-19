package domain

data class Seat(
    val position: Int,
    val price: TicketPrice,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seat) return false
        return position == other.position
    }

    override fun hashCode(): Int {
        return position
    }
}
