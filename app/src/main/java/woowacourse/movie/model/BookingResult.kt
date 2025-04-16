package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingResult(
    val headCount: Int,
    val selectedDate: String,
    val selectedTime: String,
) : Parcelable {
    fun calculateAmount(): Int {
        return TICKET_PRICE * headCount
    }

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}
