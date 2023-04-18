package woowacourse.movie.view

import android.content.Context
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.ReservationDetail
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationDetailView(
    private val context: Context,
    private val reservationDetail: ReservationDetail,
    private val date: TextView? = null,
    private val peopleCount: TextView? = null,
    private val price: TextView? = null,
) {
    fun render() {
        val dateFormat =
            DateTimeFormatter.ofPattern(context.getString(R.string.reservation_datetime_format))
        date?.text = dateFormat.format(reservationDetail.date)

        peopleCount?.text = context.getString(R.string.reservation_people_count)
            .format(reservationDetail.peopleCount)

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetail.totalPrice.value)

        price?.text = context.getString(R.string.reservation_price).format(formattedPrice)
    }
}
