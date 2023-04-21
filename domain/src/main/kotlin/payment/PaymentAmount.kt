package payment

import seat.Seat

@JvmInline
value class PaymentAmount(val value: Int) {
    init {
        require(value >= MINIMUM)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val MINIMUM = 0

        fun from(seats: List<Seat>): PaymentAmount =
            PaymentAmount(seats.sumOf { it.seatType!!.paymentAmount })
    }
}
