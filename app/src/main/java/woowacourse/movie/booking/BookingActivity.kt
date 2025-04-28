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
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

class BookingActivity :
    AppCompatActivity(),
    BookingContract.View {
    private lateinit var movieInfoUIModel: MovieInfoUIModel
    private lateinit var movieTime: Spinner
    private lateinit var selectedDate: Spinner
    private lateinit var ticketCount: TextView
    private lateinit var movieDate: TextView
    private var ticketCountValue = TicketCount()
    private var presenter = BookingPresenter(this)
    private lateinit var movieInfo: MovieInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        selectedDate = findViewById(R.id.selected_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)
        movieDate = findViewById(R.id.movie_date)
        movieInfoUIModel = intent.fetchExtraOrNull<MovieInfoUIModel>(KEY_MOVIE_INFO) ?: return
        movieInfo = MovieInfo.fromUiModel(movieInfoUIModel)
        presenter.onCreateView(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_TICKET_COUNT, ticketCount.text.toString())
        outState.putInt(KEY_MOVIE_DATE_POSITION, selectedDate.selectedItemPosition)
        outState.putInt(KEY_MOVIE_TIME_POSITION, movieTime.selectedItemPosition)
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

    override fun setupPage() {
        val title = findViewById<TextView>(R.id.title)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val poster = findViewById<ImageView>(R.id.movie_poster)

        poster.setImageResource(PosterMapper.getPosterResourceId(movieInfoUIModel.posterKey))
        title.text = movieInfoUIModel.title
        movieDate.text =
            this.getString(
                R.string.movie_date,
                movieInfoUIModel.startDate,
                movieInfoUIModel.endDate,
            )
        runningTime.text = this.getString(R.string.running_time, movieInfoUIModel.runningTime)

        SpinnerAdapter.bind(
            this,
            selectedDate,
            MovieScheduleGenerator
                .generateScreeningDates(movieInfo.startDate, movieInfo.endDate)
                .toFormattedStringList("yyyy.M.d"),
        )
        SpinnerAdapter.bind(
            this,
            movieTime,
            MovieScheduleGenerator
                .generateScreeningTimesFor(movieInfo.startDate)
                .toFormattedStringList("HH:mm"),
        )
    }

    inline fun <reified T : TemporalAccessor> List<T>.toFormattedStringList(pattern: String): List<String> {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return map { ta ->
            when (ta) {
                is LocalTime -> {
                    if (ta.hour == 0 && ta.minute == 0) {
                        "24:00"
                    } else {
                        ta.format(formatter)
                    }
                }
                else -> formatter.format(ta)
            }
        }
    }

    override fun moveActivity(ticketUIModel: TicketUIModel) {
        val intent =
            Intent(this, SelectSeatActivity::class.java).apply {
                putExtra(KEY_TICKET, ticketUIModel)
            }
        startActivity(intent)
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
                    presenter.dateSpinnerSelect(
                        MovieInfo.fromUiModel(movieInfoUIModel),
                        position,
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun confirmButtonHandler() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val title = findViewById<TextView>(R.id.title).text.toString()
            val date = findViewById<Spinner>(R.id.selected_date).selectedItem.toString()
            val time = findViewById<Spinner>(R.id.movie_time).selectedItem.toString()
            presenter.onBookButtonClick(title, date, time, ticketCountValue)
        }
    }

    override fun countButtonHandler() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            presenter.onDownButtonClick(ticketCountValue)
        }

        plusButton.setOnClickListener {
            presenter.onUpButtonClick(ticketCountValue)
        }
    }

    override fun changeTicketCount(ticketCountValue: TicketCount) {
        ticketCount.text = ticketCountValue.count.toString()
    }

    override fun timeSpinnerSet(times: List<LocalTime>) {
        SpinnerAdapter.bind(this@BookingActivity, movieTime, times.toFormattedStringList("HH:mm"))
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)

    companion object {
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        const val KEY_TICKET = "TICKET"
    }
}
