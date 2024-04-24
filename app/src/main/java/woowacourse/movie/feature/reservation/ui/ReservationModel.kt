package woowacourse.movie.feature.reservation.ui

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Reservation
import java.io.Serializable
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

data class ReservationModel(
    val title: String,
    val schedule: String,
    val quantity: Int,
    val price: Long,
) : Serializable {
    fun formatPrice(context: Context): String {
        val formattedPrice = DecimalFormat(DECIMAL_FORMAT).format(price)
        return String.format(context.getString(R.string.reservation_price), formattedPrice)
    }

    fun formatQuantity(context: Context) = String.format(context.getString(R.string.reservation_quantity), quantity)

    companion object {
        private const val DECIMAL_FORMAT = "#,###"
        const val DATE_FORMAT = "yyyy.M.d"
    }
}

fun Reservation.toUiModel(): ReservationModel {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(ReservationModel.DATE_FORMAT, Locale.getDefault())
    return ReservationModel(
        title = getTitle(),
        schedule = getScreeningSchedule().format(formatter),
        quantity = getQuantity(),
        price = price,
    )
}
