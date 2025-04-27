package woowacourse.movie.booking.complete

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.StringFormatter

@Parcelize
data class BookingCompleteUiModel(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String>,
    val ticketQuantity: Int,
    val ticketTotalPrice: Int,
) : Parcelable {
    val formattedDateTme = "$date $time"
    val formattedQuantity = "일반 ${ticketQuantity}명"
    val formattedPrice = "${StringFormatter.formatMoney(ticketTotalPrice)}원 (현장 결제)"
    val formattedSeats = seats.joinToString()
}
