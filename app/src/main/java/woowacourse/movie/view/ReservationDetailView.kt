package woowacourse.movie.view

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.model.ReservationDetailViewModel
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationDetailView(
    private val date: TextView? = null,
    private val peopleCount: TextView? = null,
    private val price: TextView? = null,
) {
    fun render(reservationDetailDto: ReservationDetailViewModel) {
        val dateFormat =
            DateTimeFormatter.ofPattern(date?.context?.getString(R.string.reservation_datetime_format))
        date?.text = dateFormat.format(reservationDetailDto.date)

        peopleCount?.text = peopleCount?.context?.getString(R.string.reservation_people_count)
            ?.format(reservationDetailDto.peopleCount)

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetailDto.price)

        price?.text = price?.context?.getString(R.string.reservation_price)?.format(formattedPrice)
    }
}
