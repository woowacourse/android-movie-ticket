package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Ticket
import java.text.SimpleDateFormat
import java.util.Locale

class MovieReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getSerializable(getString(R.string.movie_extra_name), Movie::class.java)
        } else {
            intent.extras?.getSerializable(getString(R.string.movie_extra_name)) as Movie
        }

        val counter = Counter(
            findViewById(R.id.movie_reservation_people_count_minus),
            findViewById(R.id.movie_reservation_people_count_plus),
            findViewById(R.id.movie_reservation_people_count),
            INITIAL_COUNT,
        )

        if (movie != null) {
            findViewById<ImageView>(R.id.movie_reservation_poster).setImageResource(movie.picture)
            findViewById<TextView>(R.id.movie_reservation_title).text = movie.title

            val dateFormat = SimpleDateFormat(getString(R.string.movie_date_format), Locale.KOREA)
            findViewById<TextView>(R.id.movie_reservation_date).text =
                getString(R.string.movie_date).format(dateFormat.format(movie.date))

            findViewById<TextView>(R.id.movie_reservation_running_time).text =
                getString(R.string.movie_running_time).format(movie.runningTime)

            findViewById<TextView>(R.id.movie_reservation_description).text = movie.description

            findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
                val reservation = Reservation(movie.date, counter.count, movie, Ticket())
                val intent = Intent(this, ReservationResultActivity::class.java)
                intent.putExtra(getString(R.string.reservation_extra_name), reservation)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val INITIAL_COUNT = 1
    }
}
