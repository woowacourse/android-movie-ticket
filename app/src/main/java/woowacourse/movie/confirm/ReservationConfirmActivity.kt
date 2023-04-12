package woowacourse.movie.confirm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.domain.DiscountCalculator
import woowacourse.movie.main.MainActivity
import woowacourse.movie.reservation.MovieDetailActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_confirm)

        val movie = intent.getSerializableExtra(MainActivity.KEY_MOVIE_DATA) as Movie
        val reservationCount = intent.getIntExtra(MovieDetailActivity.KEY_RESERVATION_COUNT, 0)
        val date =
            intent.getSerializableExtra(MovieDetailActivity.KEY_RESERVATION_DATE) as LocalDate
        val time =
            intent.getSerializableExtra(MovieDetailActivity.KEY_RESERVATION_TIME) as LocalTime
        val dateTime = LocalDateTime.of(date, time)

        Log.d("mendel", "$movie , $reservationCount")

        val titleTextView = findViewById<TextView>(R.id.reservation_title)
        val dateTextView = findViewById<TextView>(R.id.reservation_date)
        val moneyTextView = findViewById<TextView>(R.id.reservation_money)

        titleTextView.text = movie.title
        dateTextView.text = dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
        moneyTextView.text = DiscountCalculator().discount(reservationCount, dateTime).toString()
    }

    companion object {
    }
}
