package woowacourse.movie.model

import java.io.Serializable

data class Ticket(val movieTitle: String, val screeningDateTime: String, val selectedSeats: List<Seat>) : Serializable {
    val totalPrice = selectedSeats.sumOf { it.seatClass.price }
    val totalCount = selectedSeats.size
}
