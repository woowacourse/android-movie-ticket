package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.MovieBookingCheckActivity
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.properties.Delegates

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    var timeSpinnerRecoverState: Int = -1

    var ticketCount by Delegates.observable(0) { _, _, new ->
        tvTicketCount.text = new.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initTicketCountView()
        initSpinners()
        recoverState(savedInstanceState)
        initExtraData()
        initMovieInformation()
        initTicketCount()
        initMinusButtonClickListener()
        initPlusButtonClickListener()
        initBookingCompleteButtonClickListener()
        initSpinnerAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("ticketCount", ticketCount)
        outState.putInt("selectedTimePosition", timeSpinner.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            ticketCount = savedInstanceState.getInt("ticketCount")
            timeSpinnerRecoverState = savedInstanceState.getInt("selectedTimePosition")
        }
    }

    private fun initSpinners() {
        dateSpinner = findViewById(R.id.spinner_date)
        timeSpinner = findViewById(R.id.spinner_time)
    }

    private fun initSpinnerAdapter() {
        timeSpinnerAdapter =
            TimeSpinnerAdapter(
                timeSpinner,
                movieData.screeningDay,
                timeSpinnerRecoverState,
                this
            ).apply { initAdapter() }
        dateSpinnerAdapter =
            DateSpinnerAdapter(
                dateSpinner,
                timeSpinnerAdapter::updateTimeTable,
                movieData.screeningDay,
                this
            )
                .apply { initAdapter() }
    }

    private fun initExtraData() {
        movieData =
            intent.customGetParcelableExtra<MovieData>("movieData", ::finishActivity) ?: return
    }

    private fun finishActivity(key: String) {
        Log.d("MovieBooking", "${key}를 찾을 수 없습니다.")
        finish()
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
                movieData.screeningDay.start.format(DateTimeFormatters.hyphenDateFormatter),
                movieData.screeningDay.end.format(DateTimeFormatters.hyphenDateFormatter)
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

    private fun getScreeningDateTime(): ScreeningDateTime {
        val date = dateSpinner.selectedItem as LocalDate
        val time = timeSpinner.selectedItem as LocalTime

        return ScreeningDateTime(LocalDateTime.of(date, time), movieData.screeningDay)
    }

    // timespinner 초기화 관련 방어코드 고려
    private fun initBookingCompleteButtonClickListener() {
        findViewById<Button>(R.id.btn_booking_complete).setOnSingleClickListener {
            val intent = Intent(this, MovieBookingCheckActivity::class.java).apply {
                putExtra("movieData", movieData)
                putExtra("ticketCount", ticketCount)
                putExtra("bookedScreeningDateTime", getScreeningDateTime())
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val RECOVER_STATE_ERROR = "화면 재구성중 null 값이 포함되어 있습니다."
    }
}
