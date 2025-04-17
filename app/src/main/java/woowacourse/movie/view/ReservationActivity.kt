package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Screening
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_POSTER_ID
import woowacourse.movie.view.MainActivity.Companion.EXTRA_RUNNING_TIME
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_TITLE
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {
    private lateinit var screening: Screening
    private lateinit var selectedDate: LocalDate
    private lateinit var selectedTime: LocalTime

    private var ticketCount = DEFAULT_TICKET_COUNT
    private var timeItemPosition = DEFAULT_TIME_ITEM_POSITION

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_COUNT, ticketCount)
        outState.putInt(TIME_ITEM_POSITION, timeItemPosition)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_reservation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val savedTicketCount = savedInstanceState?.getInt(TICKET_COUNT) ?: DEFAULT_TICKET_COUNT
        val savedTimeItemPosition = savedInstanceState?.getInt(TIME_ITEM_POSITION) ?: 0
        Log.d("time", "$savedTimeItemPosition 값 oncreate")
        initModel(savedTicketCount, savedTimeItemPosition)
        initViews()
    }

    private fun initModel(
        savedTicketCount: Int,
        savedTimeItemPosition: Int,
    ) {
        val title = intent.getStringExtra(EXTRA_TITLE) ?: error("영화 제목을 전달받지 못했습니다.")
        val startYear = intent.getIntExtra(EXTRA_START_YEAR, -1).takeIf { it != -1 } ?: error("")
        val startMonth = intent.getIntExtra(EXTRA_START_MONTH, 0)
        val startDay = intent.getIntExtra(EXTRA_START_DAY, 0)
        val endYear = intent.getIntExtra(EXTRA_END_YEAR, 0)
        val endMonth = intent.getIntExtra(EXTRA_END_MONTH, 0)
        val endDay = intent.getIntExtra(EXTRA_END_DAY, 0)
        val posterId = intent.getIntExtra(EXTRA_POSTER_ID, 0)
        val runningTIme = intent.getIntExtra(EXTRA_RUNNING_TIME, 0)
        val startDate = LocalDate.of(startYear, startMonth, startDay)
        val endDate = LocalDate.of(endYear, endMonth, endDay)
        val period = startDate..endDate
        screening = Screening(Movie(title, runningTIme, posterId), period)
        ticketCount = savedTicketCount
        timeItemPosition = savedTimeItemPosition
    }

    private fun initViews() {
        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = screening.title

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

        val ticketCountView = findViewById<TextView>(R.id.tv_reservation_audience_count)
        ticketCountView.text = ticketCount.toString()

        val ticketCountPlusButton = findViewById<Button>(R.id.btn_reservation_plus)
        ticketCountPlusButton.setOnClickListener {
            ticketCount++
            ticketCountView.text = ticketCount.toString()
        }

        val ticketCountMinusButton = findViewById<Button>(R.id.btn_reservation_minus)
        ticketCountMinusButton.setOnClickListener {
            if (ticketCount > 1) ticketCount--
            ticketCountView.text = ticketCount.toString()
        }

        val completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
        completeButton.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.ticket_dialog_title))
                .setMessage(getString(R.string.ticket_dialog_message))
                .setPositiveButton(getString(R.string.ticket_dialog_positive_button)) { _, _ ->
                    navigateToTicketActivity()
                }.setNegativeButton(getString(R.string.ticket_dialog_nagative_button)) { dialog, _ ->
                    dialog.dismiss()
                }.setCancelable(false)
                .show()
        }
    }

    private fun navigateToTicketActivity() {
        val intent =
            Intent(this, TicketActivity::class.java).apply {
                putExtra(EXTRA_TITLE, screening.title)
                putExtra(EXTRA_RUNNING_TIME, screening.runningTime)
                putExtra(EXTRA_POSTER_ID, screening.posterId)
                putExtra(EXTRA_TICKET_COUNT, ticketCount)
                putExtra(EXTRA_START_DATE, screening.period.start.toString())
                putExtra(EXTRA_END_DATE, screening.period.endInclusive.toString())
                putExtra(
                    EXTRA_SHOWTIME,
                    LocalDateTime.of(selectedDate, selectedTime).toString(),
                )
            }
        startActivity(intent)
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
                android.R.layout.simple_spinner_item,
                screeningDates,
            )

        val dateSpinnerView = findViewById<Spinner>(R.id.spinner_reservation_screening_date)
        dateSpinnerView.adapter = dateAdapter

        val timeSpinnerView =
            findViewById<Spinner>(R.id.spinner_reservation_screening_time)

        timeSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val screeningTimes: List<LocalTime> = screening.showtimes(selectedDate)
                    selectedTime = screeningTimes[position]
                    timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        dateSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedDate = screeningDates[position]
                    val screeningTimes: List<LocalTime> = screening.showtimes(selectedDate)

                    val timeAdapter =
                        ArrayAdapter(
                            this@ReservationActivity,
                            android.R.layout.simple_spinner_item,
                            screeningTimes,
                        )

                    timeSpinnerView.adapter = timeAdapter

                    if (timeItemPosition >= screeningTimes.size) {
                        timeSpinnerView.setSelection(screeningTimes.lastIndex)
                    } else {
                        timeSpinnerView.setSelection(timeItemPosition)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    companion object {
        const val DEFAULT_TICKET_COUNT = 1
        const val DEFAULT_TIME_ITEM_POSITION = 0
        const val TICKET_COUNT = "TICKET_COUNT"
        const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        const val EXTRA_TICKET_COUNT = "woowacourse.movie.EXTRA_TICKET_COUNT"
        const val EXTRA_SHOWTIME = "woowacourse.movie.EXTRA_SHOWTIME"
        const val EXTRA_START_DATE = "woowacourse.movie.EXTRA_START_DATE"
        const val EXTRA_END_DATE = "woowacourse.movie.EXTRA_END_DATE"
    }
}
