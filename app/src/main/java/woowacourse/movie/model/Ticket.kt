package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class Ticket(
    val screeningTime: LocalDateTime,
    val reservationCount: ReservationCount,
    private val price: Int = DEFAULT_PRICE,
) : Parcelable {
    fun amount(): Int = price * reservationCount.count

    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}
