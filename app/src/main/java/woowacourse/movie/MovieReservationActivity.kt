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
import java.text.SimpleDateFormat
import java.util.Locale

class MovieReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getSerializable("movie", Movie::class.java)
        } else {
            intent.extras?.getSerializable("movie") as Movie
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

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            findViewById<TextView>(R.id.movie_reservation_date).text =
                "상영일: %s".format(dateFormat.format(movie.date))

            findViewById<TextView>(R.id.movie_reservation_running_time).text =
                "러닝타임: %d분".format(movie.runningTime)

            findViewById<TextView>(R.id.movie_reservation_description).text = movie.description

            findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
                val reservation = Reservation(movie.date, counter.count, movie, 13000)
                val intent = Intent(this, ReservationResultActivity::class.java)
                intent.putExtra("reservation", reservation)
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
