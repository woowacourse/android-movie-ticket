package woowacourse.movie.ui.confirm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.domain.discount.DiscountCalculator
import com.example.domain.model.Count
import woowacourse.movie.R
import woowacourse.movie.model.MovieRes
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_RESERVATION_INFO
import woowacourse.movie.util.customGetSerializable
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    private val discountCalculator: DiscountCalculator by lazy { DiscountCalculator() }
    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val reservationInfo =
            intent.customGetSerializable<ReservationInfo>(KEY_RESERVATION_INFO, ::keyNoExistError)
                ?: return
        val (movie, dateTime, reservationCount) = reservationInfo.getInfo()
        Log.d(LOG_TAG, "$movie ,$dateTime, $reservationCount")
        setInitReservationData(movie, dateTime, reservationCount)
    }

    private fun setInitReservationData(
        movie: MovieRes,
        dateTime: LocalDateTime,
        reservationCount: Count
    ) {
        titleTextView.text = movie.title
        dateTextView.text = dateTime.format(DATE_TIME_FORMATTER)
        moneyTextView.text = formattingMoney(reservationCount, movie, dateTime)
        reservationCountTextView.text =
            getString(R.string.person_count_text, reservationCount.value)
    }

    private fun formattingMoney(
        reservationCount: Count,
        movie: MovieRes,
        dateTime: LocalDateTime
    ): String {
        val money = discountCalculator.discount(reservationCount, movie, dateTime).value
        return DECIMAL_FORMATTER.format(money)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
        private const val LOG_TAG = "mendel and bbotto"
    }
}
