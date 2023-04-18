package woowacourse.movie.view

import android.content.Context
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.dto.ReservationDetailDto
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationDetailView(
    private val context: Context,
    private val reservationDetailDto: ReservationDetailDto,
    private val date: TextView? = null,
    private val peopleCount: TextView? = null,
    private val price: TextView? = null,
) {
    fun render() {
        val dateFormat =
            DateTimeFormatter.ofPattern(context.getString(R.string.reservation_datetime_format))
        date?.text = dateFormat.format(reservationDetailDto.date)

        peopleCount?.text = context.getString(R.string.reservation_people_count)
            .format(reservationDetailDto.peopleCount)

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetailDto.price)

        price?.text = context.getString(R.string.reservation_price).format(formattedPrice)
    }
}
