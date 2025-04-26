package woowacourse.movie.booking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.bookingResult.BookingResultActivity
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.domain.TicketMaker
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.util.DataUtils
import woowacourse.movie.util.MovieScheduleUtils

class BookingActivity :
    AppCompatActivity(),
    BookingContract.View {
    private lateinit var movieInfo: MovieInfo
    private lateinit var movieTime: Spinner
    private lateinit var selectedDate: Spinner
    private lateinit var ticketCount: TextView
    private lateinit var movieDate: TextView
    private var ticketCountValue = TicketCount()
    private var presenter = BookingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        selectedDate = findViewById(R.id.selected_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)
        movieDate = findViewById(R.id.movie_date)
        movieInfo = DataUtils.getExtraOrFinish<MovieInfo>(intent, this, KEY_MOVIE_INFO) ?: return

        presenter.onCreateView(this, savedInstanceState)
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

        poster.setImageResource(movieInfo.poster)
        title.text = movieInfo.title
        movieDate.text =
            this.getString(
                R.string.movie_date,
                movieInfo.startDate,
                movieInfo.endDate,
            )
        runningTime.text = this.getString(R.string.running_time, movieInfo.runningTime)

        SpinnerAdapter.bind(
            this,
            selectedDate,
            MovieScheduleUtils.generateScreeningDates(movieInfo.startDate, movieInfo.endDate),
        )
        SpinnerAdapter.bind(this, movieTime, MovieScheduleUtils.generateScreeningTimesFor(movieInfo.startDate))
    }

    override fun moveActivity() {
        val ticketDTO =
            TicketMaker.generator(
                title = findViewById<TextView>(R.id.title).text.toString(),
                date = selectedDate.selectedItem.toString(),
                time = movieTime.selectedItem.toString(),
                count = ticketCountValue.count,
            )
        val intent =
            Intent(this, BookingResultActivity::class.java).apply {
                putExtra(KEY_TICKET, ticketDTO)
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
                    presenter.dateSpinnerSelect(this@BookingActivity, movieInfo, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun confirmButtonHandler() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            presenter.onBookButtonClick(this@BookingActivity, ticketCountValue)
        }
    }

    override fun countButtonHandler() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            presenter.onDownButtonClick(this@BookingActivity, ticketCountValue)
        }

        plusButton.setOnClickListener {
            presenter.onUpButtonClick(this@BookingActivity, ticketCountValue)
        }
    }

    override fun changeTicketCount(ticketCountValue: TicketCount) {
        ticketCount.text = ticketCountValue.count.toString()
    }

    override fun askToConfirmBook() {
        ConfirmDialog.show(this) {
            presenter.onYesClick(this@BookingActivity)
        }
    }

    override fun timeSpinnerSet(times: List<String>) {
        SpinnerAdapter.bind(this@BookingActivity, movieTime, times)
    }

    companion object {
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        private const val KEY_TICKET = "TICKET"
    }
}
