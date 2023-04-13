package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime
import woowacourse.movie.domain.movieTimePolicy.MovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekDayMovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekendMovieTime
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

    private val dateSpinner: SaveStateSpinner by lazy {
        SaveStateSpinner(
            DATE_SPINNER_SAVE_STATE_KEY,
            findViewById(R.id.movie_reservation_date_spinner),
        )
    }

    private val timeSpinner: SaveStateSpinner by lazy {
        SaveStateSpinner(
            TIME_SPINNER_SAVE_STATE_KEY,
            findViewById(R.id.movie_reservation_time_spinner),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.extras?.getSerializable<Movie>(getString(R.string.movie_extra_name))

        counter.applyToView()

        if (movie != null) {
            counter.load(savedInstanceState)
            dateSpinner.load(savedInstanceState)
            timeSpinner.load(savedInstanceState)

            val dateArray = movie.date.toList().map { LocalFormattedDate(it) }
            val dateAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dateArray)
            dateSpinner.spinner.adapter = dateAdapter

            dateSpinner.spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val timeArray = MovieTime(
                            listOf(
                                WeekDayMovieTime, WeekendMovieTime
                            )
                        ).determine(dateArray[p2].date).map { LocalFormattedTime(it) }
                        val timeAdapter = ArrayAdapter(
                            this@MovieReservationActivity,
                            android.R.layout.simple_spinner_dropdown_item,
                            timeArray
                        )
                        timeSpinner.spinner.adapter = timeAdapter
                        timeSpinner.load(savedInstanceState)
                    }
                }

            MovieController(
                this,
                movie,
                findViewById(R.id.movie_reservation_poster),
                findViewById(R.id.movie_reservation_title),
                findViewById(R.id.movie_reservation_date),
                findViewById(R.id.movie_reservation_running_time),
                findViewById(R.id.movie_reservation_description)
            ).render()

            findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
                val reservation = makeReservation(movie)
                startReservationResultActivity(reservation)
            }
        }
    }

    private fun makeReservation(movie: Movie): Reservation {
        val reservationDetail = ReservationDetail(
            LocalDateTime.of(
                (dateSpinner.spinner.selectedItem as LocalFormattedDate).date,
                (timeSpinner.spinner.selectedItem as LocalFormattedTime).time
            ),
            counter.count, Price()
        )
        val discount = Discount(listOf(MovieDay, OffTime))
        val discountedReservationDetail = discount.calculate(reservationDetail)
        return Reservation(movie, discountedReservationDetail)
    }

    private fun startReservationResultActivity(reservation: Reservation) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(getString(R.string.reservation_extra_name), reservation)
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
        const val INITIAL_COUNT = 1
        const val COUNTER_SAVE_STATE_KEY = "counter"
        const val DATE_SPINNER_SAVE_STATE_KEY = "date_spinner"
        const val TIME_SPINNER_SAVE_STATE_KEY = "time_spinner"
    }
}
