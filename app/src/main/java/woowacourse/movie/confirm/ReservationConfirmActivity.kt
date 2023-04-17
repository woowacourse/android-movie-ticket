package woowacourse.movie.confirm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import woowacourse.movie.BackKeyActionBarActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.domain.DiscountCalculator
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.ViewingDate
import woowacourse.movie.entity.ViewingTime
import woowacourse.movie.utils.getParcelableCompat
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val movie = intent.getParcelableCompat<Movie>(KEY_MOVIE)!!
        val reservationCount = intent.getParcelableCompat<Count>(KEY_RESERVATION_COUNT)!!
        val date = intent.getParcelableCompat<ViewingDate>(KEY_RESERVATION_DATE)!!
        val time = intent.getParcelableCompat<ViewingTime>(KEY_RESERVATION_TIME)!!
        val dateTime = LocalDateTime.of(date.value, time.value)
        Log.d(LOG_TAG, "$movie , $reservationCount")
        setInitReservationData(movie, dateTime, reservationCount)
    }

    private fun setInitReservationData(
        movie: Movie,
        dateTime: LocalDateTime,
        reservationCount: Count
    ) {
        titleTextView.text = movie.title
        dateTextView.text = dateTime.format(DATE_TIME_FORMATTER)
        moneyTextView.text = formattingMoney(reservationCount, dateTime)
        reservationCountTextView.text = reservationCount.value.toString()
    }

    private fun formattingMoney(reservationCount: Count, dateTime: LocalDateTime): String {
        val money = DiscountCalculator().discount(reservationCount, dateTime).value
        return DECIMAL_FORMATTER.format(money)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
        private const val LOG_TAG = "mendel and bbotto"
    }
}
