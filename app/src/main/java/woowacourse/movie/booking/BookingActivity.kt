package woowacourse.movie.booking

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.MovieScheduleGenerator
import woowacourse.movie.model.TicketCount
import woowacourse.movie.selectSeat.SelectSeatActivity
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.PosterMapper
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

class BookingActivity :
    AppCompatActivity(),
    BookingContract.View {
    private lateinit var movieInfoUIModel: MovieInfoUIModel
    private lateinit var movieInfo: MovieInfo

    private lateinit var movieTime: Spinner
    private lateinit var selectedDate: Spinner
    private lateinit var ticketCount: TextView
    private lateinit var movieDate: TextView
    private lateinit var availableDates: List<LocalDate>

    private val ticketCountValue = TicketCount()
    private val presenter = BookingPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        movieInfoUIModel = intent.fetchExtraOrNull(KEY_MOVIE_INFO) ?: return
        movieInfo = MovieInfo.fromUiModel(movieInfoUIModel)

        bindViews()
        renderMovieInfo()

        availableDates = MovieScheduleGenerator.generateScreeningDates(movieInfo.startDate, movieInfo.endDate)
        SpinnerAdapter.bind(this, selectedDate, availableDates.toFormattedStringList("yyyy.M.d"))
        SpinnerAdapter.bind(
            this,
            movieTime,
            MovieScheduleGenerator.generateScreeningTimesFor(availableDates[0]).toFormattedStringList("HH:mm"),
        )

        presenter.onCreateView(savedInstanceState)

        savedInstanceState?.let { repairInstanceState(it) }
    }

    private fun bindViews() {
        selectedDate = findViewById(R.id.selected_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)
        movieDate = findViewById(R.id.movie_date)
    }

    private fun renderMovieInfo() {
        findViewById<TextView>(R.id.title).text = movieInfoUIModel.title
        findViewById<TextView>(R.id.running_time).text = getString(R.string.running_time, movieInfoUIModel.runningTime)
        movieDate.text = getString(R.string.movie_date, movieInfoUIModel.startDate, movieInfoUIModel.endDate)
        findViewById<ImageView>(R.id.movie_poster).setImageResource(PosterMapper.getPosterResourceId(movieInfoUIModel.posterKey))
    }

    override fun repairInstanceState(state: Bundle) {
        selectedDate.setSelection(state.getInt(KEY_MOVIE_DATE_POSITION))
        selectedDate.post {
            movieTime.post {
                movieTime.setSelection(state.getInt(KEY_MOVIE_TIME_POSITION))
            }
        }
        ticketCount.text = state.getString(KEY_TICKET_COUNT)
    }

    override fun showAvailableDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        // 이미 Activity에서 처리함, 안 써도 됨
    }

    override fun showAvailableTime(selectedDate: LocalDate) {
        val times = MovieScheduleGenerator.generateScreeningTimesFor(selectedDate)
        SpinnerAdapter.bind(this, movieTime, times.toFormattedStringList("HH:mm"))
    }

    override fun setupDateChangeListener() {
        selectedDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selected = availableDates.getOrNull(position) ?: return
                    presenter.changeTimesByDate(selected)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun confirmButtonHandler() {
        findViewById<Button>(R.id.confirm_button).setOnClickListener {
            val title = findViewById<TextView>(R.id.title).text.toString()
            val date = selectedDate.selectedItem.toString()
            val time = movieTime.selectedItem.toString()
            presenter.onBookButtonClick(title, date, time, ticketCountValue)
        }
    }

    override fun countButtonHandler() {
        findViewById<Button>(R.id.minus_button).setOnClickListener {
            presenter.downTicketCount(ticketCountValue)
        }
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            presenter.upTicketCount(ticketCountValue)
        }
    }

    override fun changeTicketCount(ticketCountValue: TicketCount) {
        ticketCount.text = ticketCountValue.count.toString()
    }

    override fun navigateToResult(ticket: TicketUIModel) {
        startActivity(
            Intent(this, SelectSeatActivity::class.java).apply {
                putExtra(KEY_TICKET, ticket)
            },
        )
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)

    private fun <T : TemporalAccessor> List<T>.toFormattedStringList(pattern: String): List<String> {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return map {
            when (it) {
                is LocalTime -> if (it.hour == 0 && it.minute == 0) "24:00" else it.format(formatter)
                else -> formatter.format(it)
            }
        }
    }

    companion object {
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        const val KEY_TICKET = "TICKET"
    }
}
