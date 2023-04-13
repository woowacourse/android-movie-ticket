package woowacourse.movie.confirm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.domain.DiscountCalculator
import woowacourse.movie.entity.Count
import java.io.Serializable
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : AppCompatActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_confirm)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.customGetSerializable<Movie>(KEY_MOVIE)!!
        val reservationCount = intent.customGetSerializable<Count>(KEY_RESERVATION_COUNT)!!
        val date = intent.customGetSerializable<LocalDate>(KEY_RESERVATION_DATE)
        val time = intent.customGetSerializable<LocalTime>(KEY_RESERVATION_TIME)
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
        moneyTextView.text = formattingMoney(reservationCount, dateTime)
        reservationCountTextView.text = reservationCount.value.toString()
    }

    private fun formattingMoney(reservationCount: Count, dateTime: LocalDateTime): String {
        val money = DiscountCalculator().discount(reservationCount, dateTime).value
        return DECIMAL_FORMATTER.format(money)
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Intent.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializableExtra(key, T::class.java)
        } else {
            getSerializableExtra(key) as? T
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
        private const val LOG_TAG = "mendel and bbotto"
    }
}
