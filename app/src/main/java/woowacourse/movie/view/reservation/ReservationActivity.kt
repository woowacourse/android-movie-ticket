package woowacourse.movie.view.reservation

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.view.MainActivity
import woowacourse.movie.view.reservation.model.Screening
import woowacourse.movie.view.ticket.TicketActivity
import woowacourse.movie.view.ticket.model.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {
    private val showReservationDialog by lazy { ShowReservationDialog(this) }

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
        initModel(savedTicketCount, savedTimeItemPosition)
        initViews()

        Log.e("Gio", "onCreate invoked...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("Gio", "onRestoreInstanceState invoked...")
    }

    private fun initModel(
        savedTicketCount: Int,
        savedTimeItemPosition: Int,
    ) {
        screening = intent.getScreeningExtra(MainActivity.Companion.EXTRA_SCREENING)
            ?: error("상영 정보가 전달되지 않았습니다.")
        ticketCount = savedTicketCount
        timeItemPosition = savedTimeItemPosition
    }

    @Suppress("DEPRECATION")
    private fun Intent.getScreeningExtra(key: String): Screening? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getParcelableExtra(
                    key,
                    Screening::class.java,
                )

            else -> getParcelableExtra(key) as? Screening
        }

    private fun initViews() {
        initTitleView()
        initPeriodView()
        initPosterView()
        initRunningTimeView()
        initDateSpinner()
        initTimeSpinner()
        initTicketCountLayout()
        initCompleteButton()
    }

    private fun initTicketCountLayout() {
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
    }

    private fun initCompleteButton() {
        val completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
        completeButton.setOnClickListener {
            showReservationDialog(
                title = getString(R.string.ticket_dialog_title),
                message = getString(R.string.ticket_dialog_message),
                positiveButtonText = getString(R.string.ticket_dialog_positive_button),
                positiveButtonAction = { _, _ -> navigateToTicketActivity() },
                negativeButtonText = getString(R.string.ticket_dialog_nagative_button),
                negativeButtonAction = { dialog: DialogInterface, _ -> dialog.dismiss() },
            )
        }
    }

    private fun initRunningTimeView() {
        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, screening.runningTime)
    }

    private fun initPosterView() {
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        posterImageView.setImageResource(screening.posterId)
    }

    private fun initPeriodView() {
        val periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        periodView.text =
            getString(
                R.string.screening_period,
                screening.startYear,
                screening.startMonth,
                screening.startDay,
                screening.endYear,
                screening.endMonth,
                screening.endDay,
            )
    }

    private fun initTitleView() {
        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = screening.title
    }

    private fun initDateSpinner() {
        val availableDates = screening.availableDates()
        val dateAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                availableDates,
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
                    selectedDate = availableDates[position]
                    val showtimes: List<LocalTime> = screening.showtimes(selectedDate)
                    val timeAdapter =
                        ArrayAdapter(
                            this@ReservationActivity,
                            android.R.layout.simple_spinner_item,
                            showtimes,
                        )

                    val timeSpinnerView =
                        findViewById<Spinner>(R.id.spinner_reservation_screening_time)
                    timeSpinnerView.adapter = timeAdapter

                    if (timeItemPosition >= showtimes.size) {
                        timeSpinnerView.setSelection(showtimes.lastIndex)
                    } else {
                        timeSpinnerView.setSelection(timeItemPosition)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTimeSpinner() {
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
    }

    private fun navigateToTicketActivity() {
        val intent =
            Intent(this, TicketActivity::class.java).putExtra(
                EXTRA_TICKET,
                Ticket(
                    screening.title,
                    ticketCount,
                    LocalDateTime.of(selectedDate, selectedTime),
                ),
            )
        startActivity(intent)
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
        const val EXTRA_TICKET = "woowacourse.movie.EXTRA_TICKET"
    }
}
