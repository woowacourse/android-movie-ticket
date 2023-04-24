package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Count
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.view.data.LocalFormattedDate
import woowacourse.movie.view.data.LocalFormattedTime
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.ReservationDetailViewData
import woowacourse.movie.view.error.ActivityError.finishWithError
import woowacourse.movie.view.error.ViewError
import woowacourse.movie.view.getSerializable
import woowacourse.movie.view.mapper.ReservationDetailMapper.toView
import woowacourse.movie.view.widget.Counter
import woowacourse.movie.view.widget.DateSpinner
import woowacourse.movie.view.widget.MovieController
import woowacourse.movie.view.widget.MovieView
import woowacourse.movie.view.widget.SaveStateCounter
import woowacourse.movie.view.widget.SaveStateSpinner
import woowacourse.movie.view.widget.TimeSpinner
import java.time.LocalDateTime

class MovieReservationActivity : AppCompatActivity() {
    private val counter: SaveStateCounter by lazy {
        SaveStateCounter(
            Counter(
                findViewById(R.id.movie_reservation_people_count_minus),
                findViewById(R.id.movie_reservation_people_count_plus),
                findViewById(R.id.movie_reservation_people_count),
                Count(INITIAL_COUNT),
            ),
            COUNTER_SAVE_STATE_KEY,
        )
    }

    private val dateSpinner: DateSpinner by lazy {
        DateSpinner(
            SaveStateSpinner(
                DATE_SPINNER_SAVE_STATE_KEY,
                findViewById(R.id.movie_reservation_date_spinner),
            )
        )
    }

    private val timeSpinner: TimeSpinner by lazy {
        TimeSpinner(
            SaveStateSpinner(
                TIME_SPINNER_SAVE_STATE_KEY,
                findViewById(R.id.movie_reservation_time_spinner),
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initMovieReservationView(savedInstanceState)
    }

    private fun initMovieReservationView(savedInstanceState: Bundle?) {
        makeBackButton()
        val movie = intent.extras?.getSerializable<MovieViewData>(MovieViewData.MOVIE_EXTRA_NAME)
            ?: return finishWithError(ViewError.ActivityMissingExtras(MovieViewData.MOVIE_EXTRA_NAME))
        makeCounter(savedInstanceState)
        makeSpinners(savedInstanceState, movie)
        renderMovie(movie)
        makeReservationButtonClickListener(movie)
    }

    private fun makeBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun makeCounter(savedInstanceState: Bundle?) {
        counter.applyToView()
        counter.load(savedInstanceState)
    }

    private fun makeSpinners(savedInstanceState: Bundle?, movie: MovieViewData) {
        dateSpinner.make(
            savedInstanceState = savedInstanceState, movie = movie, timeSpinner = timeSpinner
        )
    }

    private fun renderMovie(movie: MovieViewData) {
        MovieController.bind(
            movie = movie,
            MovieView(
                poster = findViewById(R.id.movie_reservation_poster),
                title = findViewById(R.id.movie_reservation_title),
                date = findViewById(R.id.movie_reservation_date),
                runningTime = findViewById(R.id.movie_reservation_running_time),
                description = findViewById(R.id.movie_reservation_description)
            )
        )
    }

    private fun makeReservationButtonClickListener(
        movie: MovieViewData
    ) {
        findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
            val reservationDetail = makeReservationDetail(dateSpinner, timeSpinner, counter.counter)
            SeatSelectionActivity.from(this, movie, reservationDetail).run {
                startActivity(this)
            }
        }
    }

    private fun makeReservationDetail(
        dateSpinner: DateSpinner,
        timeSpinner: TimeSpinner,
        counter: Counter
    ): ReservationDetailViewData {
        return ReservationDetail(
            LocalDateTime.of(
                (dateSpinner.spinner.spinner.selectedItem as LocalFormattedDate).date,
                (timeSpinner.spinner.spinner.selectedItem as LocalFormattedTime).time
            ),
            counter.count.value
        ).toView()
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
        private const val INITIAL_COUNT = 1
        private const val COUNTER_SAVE_STATE_KEY = "counter"
        private const val DATE_SPINNER_SAVE_STATE_KEY = "date_spinner"
        private const val TIME_SPINNER_SAVE_STATE_KEY = "time_spinner"
        fun from(context: Context, movie: MovieViewData): Intent {
            return Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(MovieViewData.MOVIE_EXTRA_NAME, movie)
            }
        }
    }
}
