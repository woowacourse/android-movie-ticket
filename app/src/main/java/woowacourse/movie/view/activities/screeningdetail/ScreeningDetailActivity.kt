package woowacourse.movie.view.activities.screeningdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import woowacourse.movie.R
import woowacourse.movie.view.PosterResourceProvider
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ScreeningDetailActivity : AppCompatActivity(), ScreeningDetailContract.View {

    private val presenter: ScreeningDetailContract.Presenter = ScreeningDetailPresenter(this)
    private var dateSpinnerPosition: Int = 0
    private var timeSpinnerPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_detail)

        presenter.loadScreeningData(getScreeningIdFromIntent())
    }

    private fun getScreeningIdFromIntent(): Long {
        return intent.getLongExtra(SCREENING_ID, -1)
    }

    override fun setScreening(screeningDetailUIState: ScreeningDetailUIState) {
        val posterView = findViewById<ImageView>(R.id.movie_poster_iv)
        val titleView = findViewById<TextView>(R.id.movie_title_tv)
        val screeningRangeView = findViewById<TextView>(R.id.screening_range_tv)
        val runningTimeView = findViewById<TextView>(R.id.running_time_tv)
        val summaryView = findViewById<TextView>(R.id.movie_summary_tv)

        posterView.setImageResource(
            PosterResourceProvider.getPosterResourceId(screeningDetailUIState.screeningId)
        )
        titleView.text = screeningDetailUIState.title
        screeningRangeView.text = getString(R.string.screening_range_format)
            .format(
                DATE_FORMATTER.format(screeningDetailUIState.screeningStartDate),
                DATE_FORMATTER.format(screeningDetailUIState.screeningEndDate)
            )
        runningTimeView.text = getString(R.string.running_time_format)
            .format(screeningDetailUIState.runningTime)
        summaryView.text = screeningDetailUIState.summary

        initSpinner(screeningDetailUIState.screeningDateTimes)
    }

    private fun initSpinner(screeningDateTimes: Map<LocalDate, List<LocalTime>>) {
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
                dateSpinnerPosition = position
                initTimeSpinner(
                    screeningDateTimes[screeningDates[position]]
                        ?: throw IllegalArgumentException("${screeningDates[position]}은 상영하지 않는 날이라서 타임 스피너를 초기화할 수 없습니다.")
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    companion object {
        const val SCREENING_ID = "SCREENING_ID"
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
