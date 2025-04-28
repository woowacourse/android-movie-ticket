package woowacourse.movie.booking.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.booking.seat.BookingSeatActivity
import woowacourse.movie.domain.Ticket
import woowacourse.movie.movies.MovieUiModel
import woowacourse.movie.movies.periodText
import woowacourse.movie.util.parcelableExtraWithVersion
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailActivity :
    AppCompatActivity(),
    BookingDetailContract.View {
    private lateinit var presenter: BookingDetailContract.Presenter
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var timeAdapter: TimeAdapter
    private lateinit var dateAdapter: DateAdapter
    private lateinit var movie: MovieUiModel

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        movie = intent.parcelableExtraWithVersion(MOVIE_KEY, MovieUiModel::class.java)
            ?: return finish()
        presenter = BookingDetailPresenter(this)

        presenter.loadMovie(movie)

        presenter.loadDateList()

        presenter.loadTicketCount()

        setupTicketCountClickListeners()
        setupSelectCompleteClickListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET_COUNT_KEY, presenter.getTicketCount())
        outState.putInt(TICKET_DATE_POSITION_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(TICKET_TIME_POSITION_KEY, timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val count = savedInstanceState.getInt(TICKET_COUNT_KEY, 1)
        presenter.setTicketCount(count)

        selectedDatePosition = savedInstanceState.getInt(TICKET_DATE_POSITION_KEY, 0)
        selectedTimePosition = savedInstanceState.getInt(TICKET_TIME_POSITION_KEY, 0)
    }

    override fun showMovieInfo(movieUiModel: MovieUiModel) {
        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = movieUiModel.title
        findViewById<TextView>(R.id.tv_booking_detail_date).text = movieUiModel.periodText()
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text =
            movieUiModel.runningTimeText
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(movie.posterResId)
    }

    override fun showDateSpinnerItems(dates: List<LocalDate>) {
        dateSpinner = findViewById<Spinner>(R.id.sp_booking_detail_date)
        dateAdapter = DateAdapter(this, dates)
        dateSpinner.adapter = dateAdapter

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selected = dateAdapter.getItem(position) ?: return
                    presenter.onDateSelected(selected)
                    presenter.loadTimeList()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        dateSpinner.setSelection(selectedDatePosition, false)
    }

    override fun showTimeSpinnerItems(times: List<LocalTime>) {
        timeSpinner = findViewById<Spinner>(R.id.sp_booking_detail_time)
        timeAdapter = TimeAdapter(this, times)
        timeSpinner.adapter = timeAdapter

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selected = timeAdapter.getItem(position) ?: return
                    presenter.onTimeSelected(selected)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        timeSpinner.setSelection(selectedTimePosition, false)
    }

    override fun showTicketCount(count: Int) {
        findViewById<TextView>(R.id.tv_booking_detail_count).text = count.toString()
    }

    override fun navigateToBookingSeat(ticket: Ticket) {
        val intent = BookingSeatActivity.newIntent(this, ticket)

        startActivity(intent)
        finish()
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTicketCountClickListeners() {
        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            presenter.onDecreaseTicketCount()
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            presenter.onIncreaseTicketCount()
        }
    }

    private fun setupSelectCompleteClickListener() {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            presenter.onSelectComplete()
        }
    }

    companion object {
        const val MOVIE_KEY = "movie"
        private const val TICKET_COUNT_KEY = "ticket_count"
        private const val TICKET_DATE_POSITION_KEY = "ticket_date_position"
        private const val TICKET_TIME_POSITION_KEY = "ticket_time_position"

        fun newIntent(
            context: Context,
            uiModel: MovieUiModel,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, uiModel)
            }
    }
}
