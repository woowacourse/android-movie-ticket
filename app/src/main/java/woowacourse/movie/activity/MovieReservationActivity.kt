package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.Counter
import woowacourse.movie.view.DateSpinner
import woowacourse.movie.view.MovieDateTimePicker
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.TimeSpinner
import woowacourse.movie.view.mapper.MovieMapper
import woowacourse.movie.view.model.*

class MovieReservationActivity : AppCompatActivity() {
    private val counter: Counter by lazy {
        Counter(
            findViewById(R.id.movie_reservation_people_count_minus),
            findViewById(R.id.movie_reservation_people_count_plus),
            findViewById(R.id.movie_reservation_people_count),
            COUNTER_SAVE_STATE_KEY
        )
    }
    private val movieDateTimePicker: MovieDateTimePicker by lazy {
        MovieDateTimePicker(
            DateSpinner(
                findViewById(R.id.movie_reservation_date_spinner),
                DATE_SPINNER_SAVE_STATE_KEY,
            ), TimeSpinner(
                findViewById(R.id.movie_reservation_time_spinner),
                TIME_SPINNER_SAVE_STATE_KEY,
            )
        )
    }
    private val reservationButton: Button by lazy {
        findViewById(R.id.movie_reservation_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieViewModel = getMovieModelView()
        if (movieViewModel == null) {
            finishActivityWithMessage(getString(R.string.movie_data_null_error))
        } else {
            renderMovieView(movieViewModel)
            counter.load(savedInstanceState)
            movieDateTimePicker.makeView(movieViewModel, savedInstanceState)
            reservationButtonClick(movieViewModel)
        }
    }

    private fun renderMovieView(movieUiModel: MovieUiModel) {
        MovieView(
            poster = findViewById(R.id.movie_reservation_poster),
            title = findViewById(R.id.movie_reservation_title),
            date = findViewById(R.id.movie_reservation_date),
            runningTime = findViewById(R.id.movie_reservation_running_time),
            description = findViewById(R.id.movie_reservation_description)
        ).render(movieUiModel)
    }

    private fun finishActivityWithMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun getMovieModelView(): MovieUiModel? {
        return intent.extras?.getSerializableCompat(MOVIE_KEY_VALUE)
    }

    private fun reservationButtonClick(movieUiModel: MovieUiModel) {
        reservationButton.setOnClickListener {
            val dateTime = TicketDateUiModel(movieDateTimePicker.getSelectedDateTime())
            val peopleCount = counter.getCount()
            SelectSeatActivity.start(this, peopleCount, dateTime, movieUiModel)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        counter.save(outState)
        movieDateTimePicker.save(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_KEY_VALUE = "movie"
        fun start(context: Context, movie: Movie) {
            val intent = Intent(context, MovieReservationActivity::class.java)
            val movieUiModel = MovieMapper.toUi(movie)
            intent.putExtra(MOVIE_KEY_VALUE, movieUiModel)
            context.startActivity(intent)
        }

        private const val COUNTER_SAVE_STATE_KEY = "counter"
        private const val DATE_SPINNER_SAVE_STATE_KEY = "date_spinner"
        private const val TIME_SPINNER_SAVE_STATE_KEY = "time_spinner"
    }
}
