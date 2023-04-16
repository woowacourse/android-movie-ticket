package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime
import woowacourse.movie.getSerializable
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.Reservation
import woowacourse.movie.view.mapper.ReservationDetailMapper.toView
import woowacourse.movie.view.widget.Counter
import woowacourse.movie.view.widget.DateSpinner
import woowacourse.movie.view.widget.LocalFormattedDate
import woowacourse.movie.view.widget.LocalFormattedTime
import woowacourse.movie.view.widget.MovieController
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
                INITIAL_COUNT,
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.extras?.getSerializable<MovieView>(MovieView.MOVIE_EXTRA_NAME)

        counter.applyToView()

        if (movie != null) {
            counter.load(savedInstanceState)

            dateSpinner.make(
                savedInstanceState = savedInstanceState,
                movie = movie,
                timeSpinner = timeSpinner
            )

            MovieController(
                movie = movie,
                poster = findViewById(R.id.movie_reservation_poster),
                title = findViewById(R.id.movie_reservation_title),
                date = findViewById(R.id.movie_reservation_date),
                runningTime = findViewById(R.id.movie_reservation_running_time),
                description = findViewById(R.id.movie_reservation_description)
            ).render()

            findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
                val reservationDetail = makeReservationDetail(dateSpinner, timeSpinner, counter.counter)
                val reservation = makeReservation(movie, reservationDetail)
                startReservationResultActivity(reservation, Reservation.RESERVATION_EXTRA_NAME)
            }
        }
    }

    private fun makeReservationDetail(
        dateSpinner: DateSpinner,
        timeSpinner: TimeSpinner,
        counter: Counter
    ): ReservationDetail {
        return ReservationDetail(
            LocalDateTime.of(
                (dateSpinner.spinner.spinner.selectedItem as LocalFormattedDate).date,
                (timeSpinner.spinner.spinner.selectedItem as LocalFormattedTime).time
            ),
            counter.count, Price()
        )
    }

    private fun makeReservation(movie: MovieView, reservationDetail: ReservationDetail): Reservation {
        val discount = Discount(listOf(MovieDay, OffTime))
        val discountedReservationDetail = discount.calculate(reservationDetail).toView()
        return Reservation(movie, discountedReservationDetail)
    }

    private fun startReservationResultActivity(reservation: Reservation, extraName: String) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(extraName, reservation)
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
        private const val INITIAL_COUNT = 1
        private const val COUNTER_SAVE_STATE_KEY = "counter"
        private const val DATE_SPINNER_SAVE_STATE_KEY = "date_spinner"
        private const val TIME_SPINNER_SAVE_STATE_KEY = "time_spinner"
    }
}
