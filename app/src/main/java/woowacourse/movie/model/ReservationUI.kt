package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationUI(
    val movie: MovieUI,
    val dateTime: LocalDateTime,
    val ticket: TicketUI,
    val totalPrice: Int = DEFAULT_TOTAL_PRICE
) : Parcelable {
    companion object {
        private const val DEFAULT_TOTAL_PRICE = 0
    }
}
