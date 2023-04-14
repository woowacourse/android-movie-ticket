package woowacourse.movie.view

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.ReservationDetail
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationDetailController(
    private val reservationDetail: ReservationDetail,
    private val date: TextView? = null,
    private val peopleCount: TextView? = null,
    private val price: TextView? = null,
) {
    fun render() {
        if (date != null) {
            val dateFormat =
                DateTimeFormatter.ofPattern(date.context.getString(R.string.reservation_datetime_format))
            date.text = dateFormat.format(reservationDetail.date)
        }

        if (peopleCount != null) {
            peopleCount.text = peopleCount.context.getString(R.string.reservation_people_count)
                .format(reservationDetail.peopleCount)
        }

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetail.getTotalPrice())

        if (price != null) {
            price.text = price.context.getString(R.string.reservation_price).format(formattedPrice)
        }
    }
}
