package woowacourse.movie.view

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
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Screening
import java.time.LocalDate

class ReservationActivity : AppCompatActivity() {
    private lateinit var screening: Screening

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_reservation_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initModel()
        initViews()
    }

    private fun initModel() {
        val title =
            intent.getStringExtra(MainActivity.EXTRA_TITLE) ?: throw IllegalStateException()
        val startYear = intent.getIntExtra(MainActivity.EXTRA_START_YEAR, 0)
        val startMonth = intent.getIntExtra(MainActivity.EXTRA_START_MONTH, 0)
        val startDay = intent.getIntExtra(MainActivity.EXTRA_START_DAY, 0)
        val endYear = intent.getIntExtra(MainActivity.EXTRA_END_YEAR, 0)
        val endMonth = intent.getIntExtra(MainActivity.EXTRA_END_MONTH, 0)
        val endDay = intent.getIntExtra(MainActivity.EXTRA_END_DAY, 0)
        val posterId = intent.getIntExtra(MainActivity.EXTRA_POSTER_ID, 0)
        val runningTIme = intent.getIntExtra(MainActivity.EXTRA_RUNNING_TIME, 0)
        val startDate = LocalDate.of(startYear, startMonth, startDay)
        val endDate = LocalDate.of(endYear, endMonth, endDay)
        val period = startDate..endDate
        screening = Screening(Movie(title, runningTIme, posterId), period)
    }

    private fun initViews() {
        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = title

        val periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        periodView.text =
            getString(
                R.string.screening_period,
                screening.period.start.year,
                screening.period.start.monthValue,
                screening.period.start.dayOfMonth,
                screening.period.endInclusive.year,
                screening.period.endInclusive.monthValue,
                screening.period.endInclusive.dayOfMonth,
            )
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        posterImageView.setImageResource(screening.posterId)

        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, screening.runningTime)

        initDateSpinner(screening.period.start, screening.period.endInclusive)
    }

    private fun initDateSpinner(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        var currentDate = startDate
        val screeningDates = mutableListOf<LocalDate>()
        while (!currentDate.isAfter(endDate)) {
            screeningDates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }

        val dateAdapter =
            ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                screeningDates,
            )

        val dateSpinnerView = findViewById<Spinner>(R.id.spinner_reservation_screening_date)
        dateSpinnerView.adapter = dateAdapter

        dateSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = screeningDates[position]
                    val screeningTime = screening.showtimes(selectedDate)

                    val timeAdapter =
                        ArrayAdapter(
                            this@ReservationActivity,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            screeningTime,
                        )

                    val timeSpinnerView =
                        findViewById<Spinner>(R.id.spinner_reservation_screening_time)
                    timeSpinnerView.adapter = timeAdapter
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }
}
