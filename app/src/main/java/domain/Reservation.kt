package domain

data class Reservation(
    val movie: Movie,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) {

    companion object {
        private const val TICKET_PRICE = 13000
        fun from(movie: Movie, ticketCount: Int) = Reservation(
            movie = movie,
            ticketCount = ticketCount,
            paymentAmount = PaymentAmount(ticketCount * TICKET_PRICE),
        )
    }
}
