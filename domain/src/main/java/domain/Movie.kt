package domain

data class Movie(
    val imagePath: String,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
) {
    fun makeReservation(tickets: Tickets): Reservation {
        return Reservation(this, tickets)
    }
}
