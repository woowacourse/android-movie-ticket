package woowacourse.movie.confirm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import woowacourse.movie.BackKeyActionBarActivity
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.domain.discount.DiscountCalculator
import woowacourse.movie.entity.Count
import woowacourse.movie.extensions.customGetSerializable
import woowacourse.movie.main.MainActivity.Companion.KEY_MOVIE
import woowacourse.movie.reservation.MovieDetailActivity.Companion.KEY_COUNT
import woowacourse.movie.reservation.MovieDetailActivity.Companion.KEY_DATE
import woowacourse.movie.reservation.MovieDetailActivity.Companion.KEY_TIME
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    private val discountCalculator: DiscountCalculator by lazy { DiscountCalculator() }
    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val movie =
            intent.customGetSerializable<Movie>(KEY_MOVIE, ::keyNoExistError) ?: return
        val reservationCount =
            intent.customGetSerializable<Count>(KEY_COUNT, ::keyNoExistError) ?: return
        val date =
            intent.customGetSerializable<LocalDate>(KEY_DATE, ::keyNoExistError) ?: return
        val time =
            intent.customGetSerializable<LocalTime>(KEY_TIME, ::keyNoExistError) ?: return
        val dateTime = LocalDateTime.of(date, time)
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
        moneyTextView.text = formattingMoney(reservationCount, movie, dateTime)
        reservationCountTextView.text = reservationCount.value.toString()
    }

    private fun formattingMoney(
        reservationCount: Count,
        movie: Movie,
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
