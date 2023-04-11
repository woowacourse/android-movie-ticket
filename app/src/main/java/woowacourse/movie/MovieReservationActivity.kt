package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import java.text.SimpleDateFormat
import java.util.*

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

        if (movie != null) {
            findViewById<ImageView>(R.id.movie_reservation_poster)
                .setImageResource(movie.picture)
            findViewById<TextView>(R.id.movie_reservation_title).text = movie.title

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            findViewById<TextView>(R.id.movie_reservation_date).text =
                "상영일: %s".format(dateFormat.format(movie.date))

            findViewById<TextView>(R.id.movie_reservation_running_time).text =
                "러닝타임: %d분".format(movie.runningTime)

            findViewById<TextView>(R.id.movie_reservation_description).text =
                movie.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
