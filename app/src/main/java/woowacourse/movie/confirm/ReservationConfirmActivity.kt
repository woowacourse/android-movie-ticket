package woowacourse.movie.confirm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.main.MainActivity
import woowacourse.movie.reservation.MovieDetailActivity

class ReservationConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_confirm)

        val movie = intent.getSerializableExtra(MainActivity.KEY_MOVIE_DATA) as Movie
        val reservationCount = intent.getIntExtra(MovieDetailActivity.KEY_RESERVATION_COUNT, 0)
        Log.d("mendel", "$movie , $reservationCount")

        val titleTextView = findViewById<TextView>(R.id.reservation_title)
        val dateTextView = findViewById<TextView>(R.id.reservation_date)
        val moneyTextView = findViewById<TextView>(R.id.reservation_money)

        titleTextView.text = movie.title
        dateTextView.text = movie.startDate.toString()
        moneyTextView.text = (reservationCount * MOVIE_TICKET_MONEY).toString()
    }

    companion object {
        private const val MOVIE_TICKET_MONEY = 13000
    }
}
