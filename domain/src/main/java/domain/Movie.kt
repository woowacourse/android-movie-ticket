package domain

data class Movie(
    val imagePath: String,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
) {
    fun makeReservation(ticket: Ticket): Reservation {
        return Reservation(this, ticket)
    }
}
