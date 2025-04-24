package woowacourse.movie.view.screening

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.presenter.screening.ReservationPresenter
import woowacourse.movie.view.screening.Poster.posterId
import woowacourse.movie.view.ticket.TicketActivity
import woowacourse.movie.view.util.ErrorMessage
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity :
    AppCompatActivity(),
    ReservationContract.View {
    private var presenter: ReservationContract.Presenter? = null
    private val showReservationDialog by lazy { ShowReservationDialog(this) }

    private var screening: Screening? = null
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null

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
        presenter =
            ReservationPresenter(this, screening ?: error(ErrorMessage("screening").notProvided()))
        initViews()
    }

    private fun initModel(
        savedTicketCount: Int,
        savedTimeItemPosition: Int,
    ) {
        screening = intent.getScreeningExtra(EXTRA_SCREENING)
            ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        ticketCount = savedTicketCount
        timeItemPosition = savedTimeItemPosition
    }

    @Suppress("DEPRECATION")
    private fun Intent.getScreeningExtra(key: String): Screening? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(key, Screening::class.java)

            else -> getSerializableExtra(key) as? Screening
        }

    private fun initViews() {
        (presenter ?: error(ErrorMessage("screening").notProvided())).run {
            presentTitle()
            presentPeriod()
            presentRunningTime()
        }
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
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text =
            getString(
                R.string.running_time,
                screening.runningTime,
            )
    }

    private fun initPosterView() {
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        val posterResourceId = screening.posterId()
        if (posterResourceId != null) posterImageView.setImageResource(posterResourceId)
    }

    private fun initPeriodView() {
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
    }

    private fun initTitleView() {
    }

    private fun initDateSpinner() {
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())

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
                    val showtimes: List<LocalTime> =
                        screening.showtimes(
                            selectedDate ?: error(ErrorMessage(CAUSE_DATE).notSelected()),
                        )
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
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())

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
                    val screeningTimes: List<LocalTime> =
                        screening.showtimes(
                            selectedDate ?: error(ErrorMessage(CAUSE_DATE).notSelected()),
                        )
                    selectedTime = screeningTimes[position]
                    timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun navigateToTicketActivity() {
        val screening: Screening = screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        val intent =
            TicketActivity.newIntent(
                this,
                screening.title,
                ticketCount,
                LocalDateTime.of(selectedDate, selectedTime),
            )
        startActivity(intent)
        finish()
    }

    override fun setTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = title
    }

    override fun setPeriod(
        startYear: Int,
        startMonth: Int,
        startDay: Int,
        endYear: Int,
        endMonth: Int,
        endDay: Int,
    ) {
        val periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        periodView.text =
            getString(
                R.string.screening_period,
                startYear,
                startMonth,
                startDay,
                endYear,
                endMonth,
                endDay,
            )
    }

    override fun setRunningTime(runningTime: Int) {
        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text =
            getString(
                R.string.running_time,
                runningTime,
            )
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val DEFAULT_TIME_ITEM_POSITION = 0

        private const val TICKET_COUNT = "TICKET_COUNT"
        private const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val CAUSE_SCREENING = "screening"
        private const val CAUSE_DATE = "date"

        private const val EXTRA_SCREENING = "woowacourse.movie.EXTRA_SCREENING"

        fun newIntent(
            context: Context,
            screening: Screening,
        ): Intent =
            Intent(context, ReservationActivity::class.java).putExtra(
                EXTRA_SCREENING,
                screening,
            )
    }
}
