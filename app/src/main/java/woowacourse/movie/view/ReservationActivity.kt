package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Screening
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.TicketData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var dialog: AlertDialog? = null
    private val screeningData: ScreeningData by lazy {
        intent.getParcelableExtraCompat<ScreeningData>(MainActivity.EXTRA_SCREENING_DATA)
            ?: throw IllegalArgumentException(ERROR_CANT_READ_SCREENING_INFO)
    }
    private val screening: Screening by lazy { screeningData.toScreening() }

    private var ticketCount = TicketCount.create()
    private var timeItemPosition = DEFAULT_TIME_ITEM_POSITION

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_COUNT, ticketCount.value)
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

        initModel(savedTicketCount, savedTimeItemPosition)
        initViews()
    }

    private fun initModel(
        savedTicketCount: Int,
        savedTimeItemPosition: Int,
    ) {
        ticketCount = TicketCount.create(savedTicketCount)
        timeItemPosition = savedTimeItemPosition
    }

    private fun initViews() {
        initScreeningInfoViews()

        initDateSpinner(initTimeSpinner())

        initTicketCounterViews()

        initCompleteButtonView()
    }

    private fun initScreeningInfoViews() {
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
        posterImageView.setImageResource(ResourceMapper.movieIdToPoster(screening.movieId))

        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, screening.runningTime)
    }

    private fun navigateToTicketActivity() {
        if (selectedDate == null || selectedTime == null) {
            Toast.makeText(this, ERROR_NOT_SELECTED_SPINNER, Toast.LENGTH_SHORT).show()
            return
        }

        val ticketData =
            TicketData(
                screeningData = screeningData,
                showtime = LocalDateTime.of(selectedDate, selectedTime),
                ticketCount = ticketCount.value,
            )

        val intent =
            Intent(this, TicketActivity::class.java).apply {
                putExtra(EXTRA_TICKET_DATA, ticketData)
            }
        startActivity(intent)
    }

    private fun initTimeSpinner(): Spinner {
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
                    val selectedDate = selectedDate ?: return
                    val screeningTimes: List<LocalTime> = screening.showtimes(selectedDate)
                    selectedTime = screeningTimes[position]
                    timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        return timeSpinnerView
    }

    private fun initDateSpinner(timeSpinnerView: Spinner) {
        val screeningDates = screening.getScreeningDates()

        val dateAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
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
                    this@ReservationActivity.selectedDate = selectedDate
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

    private fun initTicketCounterViews() {
        val ticketCountView = findViewById<TextView>(R.id.tv_reservation_audience_count)
        ticketCountView.text = ticketCount.toString()

        val ticketCountPlusButton = findViewById<Button>(R.id.btn_reservation_plus)
        ticketCountPlusButton.setOnClickListener {
            ticketCount = ticketCount.increase()
            ticketCountView.text = ticketCount.toString()
        }

        val ticketCountMinusButton = findViewById<Button>(R.id.btn_reservation_minus)
        ticketCountMinusButton.setOnClickListener {
            ticketCount = ticketCount.decrease()
            ticketCountView.text = ticketCount.toString()
        }
    }

    private fun initCompleteButtonView() {
        val completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
        completeButton.setOnClickListener {
            dialog =
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

    override fun onDestroy() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        dialog = null
        super.onDestroy()
    }

    companion object {
        const val DEFAULT_TICKET_COUNT = 1
        const val DEFAULT_TIME_ITEM_POSITION = 0
        const val TICKET_COUNT = "TICKET_COUNT"
        const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val ERROR_CANT_READ_SCREENING_INFO = "상영 정보가 전달되지 않았습니다"
        private const val ERROR_NOT_SELECTED_SPINNER = "예매 정보가 선택되지 않았습니다"

        const val EXTRA_TICKET_DATA = "woowacourse.movie.EXTRA_TICKET_DATA"
    }
}
