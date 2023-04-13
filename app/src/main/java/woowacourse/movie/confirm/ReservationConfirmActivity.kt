package woowacourse.movie.confirm

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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_confirm)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getSerializableExtra(KEY_MOVIE) as Movie
        val reservationCount = intent.getSerializableExtra(KEY_RESERVATION_COUNT) as Count
        val date =
            intent.getSerializableExtra(KEY_RESERVATION_DATE) as LocalDate
        val time =
            intent.getSerializableExtra(KEY_RESERVATION_TIME) as LocalTime
        val dateTime = LocalDateTime.of(date, time)

        Log.d("mendel", "$movie , $reservationCount")

        val titleTextView = findViewById<TextView>(R.id.reservation_title)
        val dateTextView = findViewById<TextView>(R.id.reservation_date)
        val moneyTextView = findViewById<TextView>(R.id.reservation_money)

        titleTextView.text = movie.title
        dateTextView.text = dateTime.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
        moneyTextView.text =
            DiscountCalculator().discount(reservationCount, dateTime).value.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
