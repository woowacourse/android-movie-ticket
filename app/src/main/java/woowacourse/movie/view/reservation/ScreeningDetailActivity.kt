package woowacourse.movie.view.reservation

import android.content.Context
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
import woowacourse.movie.contract.reservation.ScreeningDetailContract
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.presenter.reservation.ScreeningDetailPresenter
import woowacourse.movie.view.reservation.Poster.posterId
import woowacourse.movie.view.util.ErrorMessage
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningDetailActivity :
    AppCompatActivity(),
    ScreeningDetailContract.View {
    private var presenter: ScreeningDetailContract.Presenter? = null

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

        presenter?.ticketCount?.let { outState.putInt(TICKET_COUNT, it) }
        presenter?.timeItemPosition?.let { outState.putInt(TIME_ITEM_POSITION, it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screening_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_screening_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initPresenter(
            savedInstanceState?.getInt(TICKET_COUNT),
            savedInstanceState?.getInt(TIME_ITEM_POSITION),
        )
        findViews()
        initViews()
        initEventListeners()
    }

    private fun initPresenter(
        ticketCount: Int?,
        timeItemPosition: Int?,
    ) {
        presenter =
            ScreeningDetailPresenter(
                this,
                intent.getScreeningExtra(EXTRA_SCREENING)
                    ?: error(ErrorMessage(CAUSE_SCREENING).notProvided()),
                ticketCount,
                timeItemPosition,
            )
    }

    private fun findViews() {
        posterImageView = findViewById<ImageView>(R.id.iv_screening_detail_poster)
        titleView = findViewById<TextView>(R.id.tv_screening_detail_movie_title)
        periodView = findViewById<TextView>(R.id.tv_screening_detail_movie_period)
        runningTimeView = findViewById<TextView>(R.id.tv_screening_detail_movie_running_time)
        dateSpinner = findViewById<Spinner>(R.id.spinner_screening_detail_date)
        timeSpinner = findViewById<Spinner>(R.id.spinner_screening_detail_time)
        ticketCountView = findViewById<TextView>(R.id.tv_screening_detail_count)
        ticketCountMinusButton = findViewById<Button>(R.id.btn_screening_detail_minus)
        ticketCountPlusButton = findViewById<Button>(R.id.btn_screening_detail_plus)
        completeButton = findViewById<Button>(R.id.btn_screening_detail_select_complete)
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
            fetchScreeningDetail()
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
                    presenter?.fetchAvailableTimes(selectedDate)
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
                    presenter?.timeItemPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTicketCountPlusButtonClickEvent() {
        ticketCountPlusButton.setOnClickListener {
            presenter?.plusTicketCount()
                ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        }
    }

    private fun initTicketCountMinusButtonClickEvent() {
        ticketCountMinusButton.setOnClickListener {
            presenter?.minusTicketCount()
                ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        }
    }

    private fun initCompleteButtonClickEvent() {
        completeButton.setOnClickListener {
            presenter?.fetchAvailableSeats()
                ?: error(ErrorMessage(CAUSE_SCREENING).notProvided())
        }
    }

    override fun setScreeningDetail(screening: Screening) {
        with(screening) {
            setPoster(id)
            setTitle(title)
            setPeriod(
                startYear,
                startMonth,
                startDay,
                endYear,
                endMonth,
                endDay,
            )
            setRunningTime(runningTime)
            setDates(availableDates)
        }
    }

    private fun setPoster(movieId: Int) {
        val posterResourceId = posterId(movieId)
        if (posterResourceId != null) posterImageView.setImageResource(posterResourceId)
    }

    private fun setTitle(title: String) {
        titleView.text = title
    }

    private fun setPeriod(
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

    private fun setRunningTime(runningTime: Int) {
        runningTimeView.text =
            getString(
                R.string.running_time,
                runningTime,
            )
    }

    private fun setDates(dates: List<LocalDate>) {
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
    }

    override fun setAvailableTimes(
        times: List<LocalTime>,
        timeItemPosition: Int,
    ) {
        timeSpinner.adapter =
            ArrayAdapter(
                this@ScreeningDetailActivity,
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
        ticketCountView.text = count.toString()
    }

    override fun navigateToSeatSelectionScreen(
        title: String,
        ticketCount: Int,
    ) {
        val intent =
            SeatSelectionActivity.newIntent(
                this,
                title,
                ticketCount,
                LocalDateTime.of(
                    dateSpinner.selectedItem as LocalDate,
                    timeSpinner.selectedItem as LocalTime,
                ),
            )
        startActivity(intent)
    }

    companion object {
        private const val TICKET_COUNT = "TICKET_COUNT"
        private const val TIME_ITEM_POSITION = "TIME_ITEM_POSITION"

        private const val CAUSE_SCREENING = "screening"

        private const val EXTRA_SCREENING = "woowacourse.movie.EXTRA_SCREENING"

        fun newIntent(
            context: Context,
            screening: Screening,
        ): Intent =
            Intent(context, ScreeningDetailActivity::class.java).putExtra(
                EXTRA_SCREENING,
                screening,
            )
    }
}
