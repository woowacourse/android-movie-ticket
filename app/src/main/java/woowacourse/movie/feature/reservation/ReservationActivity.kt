package woowacourse.movie.feature.reservation

import android.content.Context
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.extension.setCustomImageResource
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.seatSelect.SeatSelectActivity
import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.movieSelect.screening.Screening
import java.time.LocalDate
import java.time.LocalTime

class ReservationActivity :
    AppCompatActivity(),
    ReservationContract.View {
    private lateinit var presenter: ReservationContract.Presenter
    private val dateSpinnerView: Spinner by lazy { findViewById(R.id.spinner_reservation_screening_date) }
    private val timeSpinnerView: Spinner by lazy { findViewById(R.id.spinner_reservation_screening_time) }

    private var timeItemPosition = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET_COUNT, presenter.getTicketCountValue())
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
        timeItemPosition =
            savedInstanceState?.getInt(TIME_ITEM_POSITION) ?: DEFAULT_TIME_ITEM_POSITION

        // 0. Presenter에 View(자신) 의존성 주입하며 초기화 + Screening 객체 전달
        presenter = ReservationPresenter(this, getScreeningByIntent() ?: return)

        // 1. presenter에게 데이터를 받아올 필요가 없는 View 자체 초기화
        initTicketPlusBtnUi()
        initTicketMinusBtnUi()
        initCompleteButtonView()

        // 2. presenter에게 View에 표시되는 데이터 초기화 요청
        presenter.initReservationView()

        // 3. 화면 회전시 초기화 되는 티켓 수량을 presenter에 복구
        presenter.recoverReservationData(savedTicketCount)
    }

    private fun getScreeningByIntent(): Screening? {
        val screeningData = intent.getParcelableExtraCompat<ScreeningData>(EXTRA_SCREENING_DATA)
        if (screeningData == null) {
            printError(ReservationErrorType.NotReceiveData)
            finish()
            return null
        }
        return screeningData.toScreening()
    }

    override fun showScreeningData(screeningData: ScreeningData) {
        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = screeningData.title

        val periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        periodView.text =
            getString(
                R.string.screening_period,
                screeningData.startDate.year,
                screeningData.startDate.monthValue,
                screeningData.startDate.dayOfMonth,
                screeningData.endDate.year,
                screeningData.endDate.monthValue,
                screeningData.endDate.dayOfMonth,
            )
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        posterImageView.setCustomImageResource(screeningData.poster)

        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, screeningData.runningTime)
    }

    override fun setDateSelectUi(screeningDates: List<LocalDate>) {
        val dateAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningDates,
            )

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
                    presenter.onChangedDate(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun setTimeSelectUi(
        selectedDate: LocalDate,
        screening: Screening,
    ) {
        val screeningTimes: List<LocalTime> = screening.showtimes(selectedDate)

        val timeAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningTimes,
            )
        timeSpinnerView.adapter = timeAdapter

        restoreTimeSpinnerPosition(screeningTimes)

        timeSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.onChangedTime(screeningTimes[position])
                    timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun restoreTimeSpinnerPosition(screeningTimes: List<LocalTime>) {
        if (timeItemPosition in 0 until screeningTimes.size) {
            timeSpinnerView.setSelection(timeItemPosition, false)
            presenter.onChangedTime(screeningTimes[timeItemPosition])
        }
    }

    override fun setTicketCounterUi(ticketCountValue: Int) {
        val ticketCountView = findViewById<TextView>(R.id.tv_reservation_audience_count)
        ticketCountView.text = ticketCountValue.toString()
    }

    override fun initTicketMinusBtnUi() {
        val ticketCountMinusButton = findViewById<Button>(R.id.btn_reservation_minus)
        ticketCountMinusButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }
    }

    override fun initTicketPlusBtnUi() {
        val ticketCountPlusButton = findViewById<Button>(R.id.btn_reservation_plus)
        ticketCountPlusButton.setOnClickListener {
            presenter.increaseTicketCount()
        }
    }

    private fun initCompleteButtonView() {
        val completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
        completeButton.setOnClickListener {
            presenter.handleCompleteReservation()
        }
    }

    override fun printError(errorType: ReservationErrorType) {
        val message =
            when (errorType) {
                ReservationErrorType.NotSelectedDateTime -> getString(R.string.reservation_error_not_selected_date_time)
                ReservationErrorType.OverCapacityTheater -> getString(R.string.reservation_error_over_capacity_theater)
                ReservationErrorType.NotReceiveData -> getString(R.string.reservation_error_not_receive_data)
            }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSelectSeatView(ticketData: TicketData) {
        startActivity(SeatSelectActivity.newIntent(this, ticketData))
    }

    companion object {
        const val DEFAULT_TICKET_COUNT = 1
        const val DEFAULT_TIME_ITEM_POSITION = 0
        const val TICKET_COUNT = "TICKET_COUNT"
        const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val EXTRA_SCREENING_DATA = "woowacourse.movie.EXTRA_SCREENING_DATA"

        fun newIntent(
            context: Context,
            screeningData: ScreeningData,
        ): Intent =
            Intent(context, ReservationActivity::class.java).apply {
                putExtra(EXTRA_SCREENING_DATA, screeningData)
            }
    }
}
