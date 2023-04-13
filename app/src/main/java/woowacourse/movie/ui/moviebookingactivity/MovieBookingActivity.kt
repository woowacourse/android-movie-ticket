package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.ui.DateFormatters
import woowacourse.movie.ui.MovieBookingCheckActivity
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import kotlin.properties.Delegates

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter

    var ticketCount by Delegates.observable(0) { _, _, new ->
        tvTicketCount.text = new.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initExtraData()
        initMovieInformation()
        initTicketCountView()
        initTicketCount()
        initMinusButtonClickListener()
        initPlusButtonClickListener()
        initBookingCompleteButtonClickListener()
        initSpinners()
        initSpinnerAdapter()
    }

    private fun initSpinners() {
        dateSpinner = findViewById(R.id.spinner_date)
        timeSpinner = findViewById(R.id.spinner_time)
    }

    private fun initSpinnerAdapter() {
        timeSpinnerAdapter =
            TimeSpinnerAdapter(timeSpinner, movieData.screeningDay, this).apply { initAdapter() }
        dateSpinnerAdapter =
            DateSpinnerAdapter(dateSpinner, timeSpinnerAdapter::updateTimeTable, movieData.screeningDay, this)
                .apply { initAdapter() }
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(
                R.drawable.img_error,
                "-1",
                ScreeningPeriod(LocalDate.parse("9999-12-30"), LocalDate.parse("9999-12-31")),
                -1
            )
        }
    }

    private fun initMovieInformation() {
        val ivBookingPoster = findViewById<ImageView>(R.id.iv_booking_poster)
        val tvBookingMovieName = findViewById<TextView>(R.id.tv_booking_movie_name)
        val tvBookingScreeningDay = findViewById<TextView>(R.id.tv_booking_screening_day)
        val tvBookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)
        val tvBookingDescription = findViewById<TextView>(R.id.tv_booking_description)

        ivBookingPoster.setImageResource(movieData.posterImage)
        tvBookingMovieName.text = movieData.title
        tvBookingScreeningDay.text = this.getString(R.string.screening_date_format)
            .format(
                movieData.screeningDay.start.format(DateFormatters.hyphenDateFormatter),
                movieData.screeningDay.end.format(DateFormatters.hyphenDateFormatter)
            )
        tvBookingRunningTime.text =
            this.getString(R.string.running_time_format).format(movieData.runningTime)
        tvBookingDescription.text = movieData.description
    }

    private fun initTicketCount() {
        tvTicketCount.text = ticketCount.toString()
    }

    private fun initTicketCountView() {
        tvTicketCount = findViewById(R.id.tv_ticket_count)
    }

    private fun initPlusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_plus).setOnSingleClickListener {
            ticketCount++
        }
    }

    private fun initMinusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_minus).setOnSingleClickListener {
            ticketCount--
            if (ticketCount <= 0) ticketCount = 0
        }
    }

    private fun initBookingCompleteButtonClickListener() {
        findViewById<Button>(R.id.btn_booking_complete).setOnSingleClickListener {
            val intent = Intent(this, MovieBookingCheckActivity::class.java).apply {
                putExtra("movieData", movieData)
                putExtra("ticketCount", ticketCount)
            }
            startActivity(intent)
        }
    }
}
