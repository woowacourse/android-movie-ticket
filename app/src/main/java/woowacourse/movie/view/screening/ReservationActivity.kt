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
import woowacourse.movie.contract.screening.ReservationContract
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
    private var screening: Screening? = null
    private var presenter: ReservationContract.Presenter? = null
    private val showConfirmDialog by lazy { ShowReservationConfirmDialog(this) }

    private var ticketCount = DEFAULT_TICKET_COUNT
    private var timeItemPosition = DEFAULT_TIME_ITEM_POSITION

    private lateinit var posterImageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var periodView: TextView
    private lateinit var runningTimeView: TextView
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var ticketCountView: TextView
    private lateinit var ticketCountMinusButton: Button
    private lateinit var ticketCountPlusButton: Button
    private lateinit var completeButton: Button

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

        findViews()
        initModel(savedInstanceState)
        presenter =
            ReservationPresenter(
                this,
                screening ?: error(ErrorMessage(CAUSE_SCREENING).notProvided()),
            )
        initViews()
        initEventListeners()
    }

    private fun findViews() {
        posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        dateSpinner = findViewById<Spinner>(R.id.spinner_reservation_screening_date)
        timeSpinner = findViewById<Spinner>(R.id.spinner_reservation_screening_time)
        ticketCountView = findViewById<TextView>(R.id.tv_reservation_audience_count)
        ticketCountMinusButton = findViewById<Button>(R.id.btn_reservation_minus)
        ticketCountPlusButton = findViewById<Button>(R.id.btn_reservation_plus)
        completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
    }

    private fun initModel(savedInstanceState: Bundle?) {
        val savedTicketCount = savedInstanceState?.getInt(TICKET_COUNT) ?: DEFAULT_TICKET_COUNT
        val savedTimeItemPosition =
            savedInstanceState?.getInt(TIME_ITEM_POSITION) ?: DEFAULT_TIME_ITEM_POSITION
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
            presentPoster()
            presentTitle()
            presentPeriod()
            presentRunningTime()
            presentDates()
            setTicketCount(ticketCount)
        }
    }

    private fun initEventListeners() {
        initDateSpinnerItemSelectedEvent()
        initTimeSpinnerItemSelectedEvent()
        initTicketCountPlusButtonClickEvent()
        initTicketCountMinusButtonClickEvent()
        initCompleteButtonClickEvent()
    }

    private fun initDateSpinnerItemSelectedEvent() {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate: LocalDate = dateSpinner.selectedItem as LocalDate
                    presenter?.presentTimes(selectedDate)
                        ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTimeSpinnerItemSelectedEvent() {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTicketCountPlusButtonClickEvent() {
        ticketCountPlusButton.setOnClickListener {
            presenter?.plusTicketCount(ticketCount) ?: error("")
        }
    }

    private fun initTicketCountMinusButtonClickEvent() {
        ticketCountMinusButton.setOnClickListener {
            presenter?.minusTicketCount(ticketCount) ?: error("")
        }
    }

    private fun initCompleteButtonClickEvent() {
        completeButton.setOnClickListener {
            presenter?.tryReservation() ?: error("")
        }
    }

    override fun setPoster(movieId: Int) {
        val posterResourceId = posterId(movieId)
        if (posterResourceId != null) posterImageView.setImageResource(posterResourceId)
    }

    override fun setTitle(title: String) {
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
        runningTimeView.text =
            getString(
                R.string.running_time,
                runningTime,
            )
    }

    override fun setDates(dates: List<LocalDate>) {
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
    }

    override fun setTimes(times: List<LocalTime>) {
        timeSpinner.adapter =
            ArrayAdapter(
                this@ReservationActivity,
                android.R.layout.simple_spinner_item,
                times,
            )

        if (timeItemPosition >= times.size) {
            timeSpinner.setSelection(times.lastIndex)
        } else {
            timeSpinner.setSelection(timeItemPosition)
        }
    }

    override fun setTicketCount(count: Int) {
        ticketCount = count
        ticketCountView.text = ticketCount.toString()
    }

    override fun showConfirmDialog() {
        showConfirmDialog(
            title = getString(R.string.ticket_dialog_title),
            message = getString(R.string.ticket_dialog_message),
            positiveButtonText = getString(R.string.ticket_dialog_positive_button),
            positiveButtonAction = { _, _ -> presenter?.confirmReservation() ?: error("") },
            negativeButtonText = getString(R.string.ticket_dialog_nagative_button),
            negativeButtonAction = { dialog: DialogInterface, _ -> dialog.dismiss() },
        )
    }

    override fun navigateToTicketScreen(title: String) {
        val intent =
            TicketActivity.newIntent(
                this,
                title,
                ticketCount,
                LocalDateTime.of(
                    dateSpinner.selectedItem as LocalDate,
                    timeSpinner.selectedItem as LocalTime,
                ),
            )
        startActivity(intent)
        finish()
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val DEFAULT_TIME_ITEM_POSITION = 0

        private const val TICKET_COUNT = "TICKET_COUNT"
        private const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val CAUSE_SCREENING = "screening"

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
