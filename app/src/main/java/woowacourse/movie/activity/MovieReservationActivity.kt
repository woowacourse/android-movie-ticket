package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movie.Companion.MOVIE_KEY_VALUE
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.Counter
import woowacourse.movie.view.DateSpinner
import woowacourse.movie.view.MovieController
import woowacourse.movie.view.ReservationButton
import woowacourse.movie.view.TimeSpinner

class MovieReservationActivity : AppCompatActivity() {
    private val counter: Counter by lazy {
        Counter(
            findViewById(R.id.movie_reservation_people_count_minus),
            findViewById(R.id.movie_reservation_people_count_plus),
            findViewById(R.id.movie_reservation_people_count),
            COUNTER_SAVE_STATE_KEY
        )
    }

    private val dateSpinner: DateSpinner by lazy {
        DateSpinner(
            findViewById(R.id.movie_reservation_date_spinner),
            DATE_SPINNER_SAVE_STATE_KEY,
        )
    }

    private val timeSpinner: TimeSpinner by lazy {
        TimeSpinner(
            findViewById(R.id.movie_reservation_time_spinner),
            TIME_SPINNER_SAVE_STATE_KEY,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.extras?.getSerializableCompat<Movie>(MOVIE_KEY_VALUE)

        if (movie != null) {
            counter.load(savedInstanceState)
            dateSpinner.make(savedInstanceState, movie, timeSpinner)

            MovieController(
                this,
                movie,
                findViewById(R.id.movie_reservation_poster),
                findViewById(R.id.movie_reservation_title),
                findViewById(R.id.movie_reservation_date),
                findViewById(R.id.movie_reservation_running_time),
                findViewById(R.id.movie_reservation_description)
            ).render()

            ReservationButton(
                findViewById(R.id.movie_reservation_button),
                getString(R.string.reservation_extra_name),
                movie,
                dateSpinner,
                timeSpinner,
                counter
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        counter.save(outState)
        dateSpinner.save(outState)
        timeSpinner.save(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val COUNTER_SAVE_STATE_KEY = "counter"
        private const val DATE_SPINNER_SAVE_STATE_KEY = "date_spinner"
        private const val TIME_SPINNER_SAVE_STATE_KEY = "time_spinner"
    }
}
