package woowacourse.movie.ui.confirm

import android.os.Bundle
import android.widget.TextView
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Reservation
import woowacourse.movie.R
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_RESERVATION_INFO
import woowacourse.movie.util.customGetSerializable
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    private val discountCalculator: DiscountPolicy by lazy { DiscountPolicy() }
    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val reservationInfo =
            intent.customGetSerializable<ReservationInfo>(KEY_RESERVATION_INFO, ::keyNoExistError)
                ?: return
        setInitReservationData(reservationInfo)
    }

    private fun setInitReservationData(
        reservationInfo: ReservationInfo
    ) {
        val reservation = reservationInfo.asDomain()
        titleTextView.text = reservation.movie.title
        dateTextView.text = reservation.dateTime.format(DATE_TIME_FORMATTER)
        moneyTextView.text = formattingMoney(reservation)
        reservationCountTextView.text =
            getString(R.string.person_count_text, reservation.count.value)
    }

    private fun formattingMoney(reservation: Reservation): String {
        val money = discountCalculator.discount(reservation).value
        return DECIMAL_FORMATTER.format(money)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
    }
}
