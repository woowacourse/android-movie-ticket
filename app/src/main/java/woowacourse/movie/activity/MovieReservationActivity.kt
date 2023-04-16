package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movie.Companion.MOVIE_KEY_VALUE
import woowacourse.movie.domain.MovieReservationOffice
import woowacourse.movie.domain.Reservation
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieDtoConverter
import woowacourse.movie.dto.ReservationDtoConverter
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.Counter
import woowacourse.movie.view.DateSpinner
import woowacourse.movie.view.MovieController
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
    private val reservationButton: Button by lazy {
        findViewById(R.id.movie_reservation_button)
    }
    private val movieReservationOffice: MovieReservationOffice by lazy { MovieReservationOffice() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = getMovieData()
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
            reservationButtonClick(movie)
        }
    }

    private fun getMovieData(): Movie? {
        val movieDto = intent.extras?.getSerializableCompat<MovieDto>(MOVIE_KEY_VALUE)
        return movieDto?.let { MovieDtoConverter().convertDtoToModel(it) }
    }

    private fun reservationButtonClick(movie: Movie) {
        reservationButton.setOnClickListener {
            val reservationDetail = movieReservationOffice.makeReservationDetail(
                dateSpinner.getSelectedDate(), timeSpinner.getSelectedTime(), counter.getCount()
            )
            val reservation = movieReservationOffice.makeReservation(movie, reservationDetail)
            startReservationResultActivity(reservation)
        }
    }

    private fun startReservationResultActivity(reservation: Reservation) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        val reservationDto = ReservationDtoConverter().convertModelToDto(reservation)
        intent.putExtra(getString(R.string.reservation_extra_name), reservationDto)
        startActivity(intent)
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
