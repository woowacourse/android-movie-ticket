package woowacourse.movie.view.activities.screeningdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import woowacourse.movie.R
import woowacourse.movie.view.activities.seatselection.SeatSelectionActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ScreeningDetailActivity : AppCompatActivity(), ScreeningDetailContract.View {

    private lateinit var presenter: ScreeningDetailContract.Presenter
    private var timeSpinnerPosition: Int = 0
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_detail)
        this.savedInstanceState = savedInstanceState

        val screeningId = intent.getLongExtra(SCREENING_ID, -1)
        presenter = ScreeningDetailPresenter(this, screeningId)
        presenter.loadScreeningData()
        initSeatSelectionButtonOnClickListener(screeningId)
    }

    private fun initSeatSelectionButtonOnClickListener(screeningId: Long) {
        val seatSelectionButton = findViewById<Button>(R.id.seat_selection_btn)
        seatSelectionButton.setOnClickListener { startSeatSelectionActivity(screeningId) }
    }

    private fun startSeatSelectionActivity(screeningId: Long) {
        fun getSelectedScreeningDateTime(): LocalDateTime {
            val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
            val selectedDate = dateSpinner.selectedItem as LocalDate
            val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
            val selectedTime = timeSpinner.selectedItem as LocalTime
            return LocalDateTime.of(selectedDate, selectedTime)
        }

        SeatSelectionActivity.startActivity(this, screeningId, getSelectedScreeningDateTime())
    }

    override fun setScreening(screeningDetailUIState: ScreeningDetailUIState) {
        val posterView = findViewById<ImageView>(R.id.movie_poster_iv)
        posterView.setImageResource(screeningDetailUIState.poster)

        val titleView = findViewById<TextView>(R.id.movie_title_tv)
        titleView.text = screeningDetailUIState.title

        val screeningRangeView = findViewById<TextView>(R.id.screening_range_tv)
        screeningRangeView.text = getString(R.string.screening_range_format)
            .format(
                DATE_FORMATTER.format(screeningDetailUIState.screeningStartDate),
                DATE_FORMATTER.format(screeningDetailUIState.screeningEndDate)
            )

        val runningTimeView = findViewById<TextView>(R.id.running_time_tv)
        runningTimeView.text = getString(R.string.running_time_format)
            .format(screeningDetailUIState.runningTime)

        val summaryView = findViewById<TextView>(R.id.movie_summary_tv)
        summaryView.text = screeningDetailUIState.summary

        initSpinners(screeningDetailUIState.screeningDateTimes)
    }

    private fun initSpinners(screeningDateTimes: Map<LocalDate, List<LocalTime>>) {
        fun initTimeSpinner(screeningTimes: List<LocalTime>) {
            val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
            timeSpinner.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningTimes
            )
            timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    timeSpinnerPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
        }

        val screeningDates = screeningDateTimes.keys.toList()
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningDates
        )
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                initTimeSpinner(
                    screeningDateTimes[screeningDates[position]]
                        ?: throw IllegalArgumentException("${screeningDates[position]}은 상영하지 않는 날이라서 타임 스피너를 초기화할 수 없습니다.")
                )
                savedInstanceState?.run {
                    val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
                    timeSpinner.setSelection(this.getInt(TIME_SPINNER_POSITION))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putInt(TIME_SPINNER_POSITION, timeSpinnerPosition)
        }
    }

    companion object {
        const val SCREENING_ID = "SCREENING_ID"
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val TIME_SPINNER_POSITION = "TIME_SPINNER_POSITION"

        fun startActivity(context: Context, screeningId: Long) {
            val intent = Intent(context, ScreeningDetailActivity::class.java).apply {
                putExtra(SCREENING_ID, screeningId)
            }
            context.startActivity(intent)
        }
    }
}
