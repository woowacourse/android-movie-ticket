package movie

data class Reservation(
    val movie: Movie,
    val ticketCount: Int,
    val paymentAmount: Int,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
)
