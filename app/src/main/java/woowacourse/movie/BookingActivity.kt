package woowacourse.movie

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RELEASE_DATE
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RUNNING_TIME
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_TITLE
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val startDateYear = intent.getIntExtra(KEY_MOVIE_START_YEAR, DEFAULT_DATE_YEAR)
        val startDateMonth = intent.getIntExtra(KEY_MOVIE_START_MONTH, DEFAULT_DATE_MONTH)
        val startDateDay = intent.getIntExtra(KEY_MOVIE_START_DAY, DEFAULT_DATE_DAY)

        val endDateYear = intent.getIntExtra(KEY_MOVIE_END_YEAR, DEFAULT_DATE_YEAR)
        val endDateMonth = intent.getIntExtra(KEY_MOVIE_END_MONTH, DEFAULT_DATE_MONTH)
        val endDateDay = intent.getIntExtra(KEY_MOVIE_END_DAY, DEFAULT_DATE_DAY)

        val startDate = LocalDate.of(startDateYear, startDateMonth, startDateDay)
        val endDate = LocalDate.of(endDateYear, endDateMonth, endDateDay)

        initView(startDate, endDate)
    }

    private fun initView(
        startDate: LocalDate,
        endDate: LocalDate
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = intent.getStringExtra(KEY_MOVIE_TITLE)

        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(intent.getIntExtra(KEY_MOVIE_POSTER, 0))

        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text = joinReleaseDates(startDate, endDate)

        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text = intent.getStringExtra(KEY_MOVIE_RUNNING_TIME)

        setDateSpinner(startDate, endDate)
        setButtonListener()
    }

    private fun setDateSpinner(
        startDate: LocalDate,
        endDate: LocalDate
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.sp_date)

        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningBookingDates
        )

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setTimeSpinner(screeningBookingDates[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setTimeSpinner(
        selectedDate: LocalDate
    ) {
        val timeSpinner: Spinner = findViewById<Spinner>(R.id.sp_time)

        val screeningTimes: List<LocalTime> =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningTimes
        )
    }

    private fun joinReleaseDates(startDate: LocalDate, endDate: LocalDate): String {
    private fun setButtonListener() {
        val increaseBtn = findViewById<Button>(R.id.btn_increase)
        val decreaseBtn = findViewById<Button>(R.id.btn_decrease)

        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        increaseBtn.setOnClickListener {
            val count = peopleCount.text.toString().toInt() + 1
            peopleCount.text = count.toString()
        }

        decreaseBtn.setOnClickListener {
            val count = max(1, peopleCount.text.toString().toInt() - 1)
            peopleCount.text = count.toString()
        }
    }
        return "$startDate ~ $endDate"
    }

    companion object {
        const val DEFAULT_DATE_YEAR = 1
        const val DEFAULT_DATE_MONTH = 1
        const val DEFAULT_DATE_DAY = 1
    }
}
