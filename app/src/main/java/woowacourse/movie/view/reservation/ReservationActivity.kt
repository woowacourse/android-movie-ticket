package woowacourse.movie.view.reservation

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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.presenter.ReservationPresenter
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.model.setImageResource
import woowacourse.movie.view.selectSeat.SelectSeatActivity
import java.time.LocalDate
import java.time.LocalTime

class ReservationActivity :
    AppCompatActivity(),
    ReservationView {
    private val present: ReservationPresenter = ReservationPresenter(this)
    private var dialog: AlertDialog? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_COUNT, present.ticketCount.value)
        outState.putInt(TIME_ITEM_POSITION, present.timeItemPosition)
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
        val savedTimeItemPosition =
            savedInstanceState?.getInt(TIME_ITEM_POSITION) ?: DEFAULT_TIME_ITEM_POSITION

        present.initReservationData(savedTicketCount, savedTimeItemPosition)
        present.initReservationUI()
        initTicketPlusBtnUi()
        initTicketMinusBtnUi()
        initCompleteButtonView()
    }

    override fun getScreeningData(): ScreeningData =
        intent.getParcelableExtraCompat<ScreeningData>(EXTRA_SCREENING_DATA)
            ?: throw IllegalArgumentException(ERROR_CANT_READ_SCREENING_INFO)

    override fun initScreeningInfoUI(screeningData: ScreeningData) {
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
        posterImageView.setImageResource(screeningData.poster)

        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, screeningData.runningTime)
    }

    override fun setDateSelectUi(screening: Screening) {
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
                    present.onChangedDate(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun setTimeSelectUi(
        selectedDate: LocalDate,
        screening: Screening,
        position: Int,
    ) {
        val timeSpinnerView =
            findViewById<Spinner>(R.id.spinner_reservation_screening_time)

        val screeningTimes: List<LocalTime> = screening.showtimes(selectedDate)

        val timeAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningTimes,
            )

        timeSpinnerView.adapter = timeAdapter

        timeSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    present.onChangedTime(screeningTimes[position])
                    present.timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        if (position >= screeningTimes.size) {
            timeSpinnerView.setSelection(screeningTimes.lastIndex)
        } else {
            timeSpinnerView.setSelection(position)
        }
    }

    override fun setTicketCounterUi(ticketCount: TicketCount) {
        val ticketCountView = findViewById<TextView>(R.id.tv_reservation_audience_count)
        ticketCountView.text = ticketCount.toString()
    }

    override fun initTicketMinusBtnUi() {
        val ticketCountMinusButton = findViewById<Button>(R.id.btn_reservation_minus)
        ticketCountMinusButton.setOnClickListener {
            present.decreaseTicketCount()
        }
    }

    override fun initTicketPlusBtnUi() {
        val ticketCountPlusButton = findViewById<Button>(R.id.btn_reservation_plus)
        ticketCountPlusButton.setOnClickListener {
            present.increaseTicketCount()
        }
    }

    private fun initCompleteButtonView() {
        val completeButton = findViewById<Button>(R.id.btn_reservation_select_complete)
        completeButton.setOnClickListener {
            present.navigateToSelectSeatUI()
        }
    }

    override fun printError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSelectSeatUI(ticketData: TicketData) {
        startActivity(SelectSeatActivity.newIntent(this, ticketData))
    }

    companion object {
        const val DEFAULT_TICKET_COUNT = 1
        const val DEFAULT_TIME_ITEM_POSITION = 0
        const val TICKET_COUNT = "TICKET_COUNT"
        const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val ERROR_CANT_READ_SCREENING_INFO = "상영 정보가 전달되지 않았습니다"

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
