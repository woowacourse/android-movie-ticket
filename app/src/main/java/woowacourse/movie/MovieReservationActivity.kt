package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
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
import java.time.format.DateTimeFormatter

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
            val dateArray = movie.date.toList().map { LocalFormattedDate(it) }
            val dateAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dateArray)
            val dateSpinner: Spinner = findViewById(R.id.movie_reservation_date_spinner)
            dateSpinner.adapter = dateAdapter

            val timeSpinner: Spinner = findViewById(R.id.movie_reservation_time_spinner)

            dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    timeSpinner.adapter = timeAdapter
                }
            }

            findViewById<ImageView>(R.id.movie_reservation_poster).setImageResource(movie.picture)
            findViewById<TextView>(R.id.movie_reservation_title).text = movie.title

            val dateFormat = DateTimeFormatter.ofPattern(getString(R.string.movie_date_format))
            findViewById<TextView>(R.id.movie_reservation_date).text =
                getString(R.string.movie_date).format(
                    dateFormat.format(movie.date.startDate), dateFormat.format(movie.date.endDate)
                )

            findViewById<TextView>(R.id.movie_reservation_running_time).text =
                getString(R.string.movie_running_time).format(movie.runningTime)

            findViewById<TextView>(R.id.movie_reservation_description).text = movie.description

            findViewById<Button>(R.id.movie_reservation_button).setOnClickListener {
                val reservationDetail = ReservationDetail(
                    LocalDateTime.of(
                        (dateSpinner.selectedItem as LocalFormattedDate).date,
                        (timeSpinner.selectedItem as LocalFormattedTime).time
                    ),
                    counter.count, Price()
                )
                val discount = Discount(listOf(MovieDay, OffTime))
                val discountedReservationDetail = discount.calculate(reservationDetail)
                val reservation = Reservation(movie, discountedReservationDetail)
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
