package woowacourse.movie.ui.view.booking

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R

class BookingViewHolder(
    val view: View,
) {
    val poster: ImageView by lazy { view.findViewById(R.id.img_booking_poster) }
    val bookingTitle: TextView by lazy { view.findViewById(R.id.tv_booking_title) }
    val bookingScreenDate: TextView by lazy { view.findViewById(R.id.tv_booking_screening_date) }
    val bookingRunningTime: TextView by lazy { view.findViewById(R.id.tv_booking_running_time) }

    val headCountView: TextView by lazy { view.findViewById(R.id.tv_people_count) }
    val btnPlus: Button by lazy { view.findViewById(R.id.btn_plus) }
    val btnMinus: Button by lazy { view.findViewById(R.id.btn_minus) }
    val btnReserveConfirm: Button by lazy { view.findViewById(R.id.btn_reserve_confirm) }

    val spinnerScreeningDate: Spinner by lazy { view.findViewById(R.id.spinner_screening_date) }
    val spinnerScreeningTime: Spinner by lazy { view.findViewById(R.id.spinner_screening_time) }
}
