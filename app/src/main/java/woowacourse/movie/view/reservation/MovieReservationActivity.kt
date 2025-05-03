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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.StringFormatter
import woowacourse.movie.common.getParcelableCompat
import woowacourse.movie.common.getParcelableExtraCompat
import woowacourse.movie.domain.scheduler.DefaultScheduler
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.seat.SeatSelectActivity
import java.time.LocalDate
import java.time.LocalTime

class MovieReservationActivity :
    AppCompatActivity(),
    MovieReservationContract.View {
    private lateinit var presenter: MovieReservationPresenter

    private val decrementButton: Button by lazy { findViewById(R.id.decrement_button) }
    private val incrementButton: Button by lazy { findViewById(R.id.increment_button) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.ticket_count) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.time_spinner) }
    private lateinit var dateAdapter: ArrayAdapter<LocalDate>
    private lateinit var timeAdapter: ArrayAdapter<LocalTime>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()

        val movie =
            intent.getParcelableExtraCompat(EXTRA_MOVIE, MovieUiModel::class.java)
                ?: finish().run { return }
        presenter = MovieReservationPresenter(this, TicketUiModel.from(movie), DefaultScheduler)

        initView()
        presenter.loadMovieReservationScreen()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (!dateAdapter.isEmpty && !timeAdapter.isEmpty) {
            outState.putParcelable(EXTRA_TICKET, presenter.ticket)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredTicket =
            savedInstanceState.getParcelableCompat(EXTRA_TICKET, TicketUiModel::class.java)
        restoredTicket?.let { restored ->
            presenter.restoreTicket(restored)
        }
    }

    override fun showMovieInfo(movie: MovieUiModel) {
        val poster: ImageView = findViewById(R.id.poster)
        val title: TextView = findViewById(R.id.movie_title)
        val screeningDate: TextView = findViewById(R.id.screening_date)
        val runningTime: TextView = findViewById(R.id.running_time)

        poster.setImageResource(movie.posterResId)
        title.text = movie.title
        val startDate = StringFormatter.date(movie.startDate)
        val endDate = StringFormatter.date(movie.endDate)
        screeningDate.text = getString(R.string.screening_date, startDate, endDate)
        runningTime.text = getString(R.string.running_time, movie.runningTime)
    }

    override fun showHeadCount(count: Int) {
        ticketCountTextView.text = count.toString()
    }

    override fun updateDecrementButtonState(enabled: Boolean) {
        decrementButton.isEnabled = enabled
    }

    override fun updateDateSpinner(
        screeningDates: List<LocalDate>,
        selectedDate: LocalDate,
    ) {
        dateAdapter.clear()
        dateAdapter.addAll(screeningDates)
        dateSpinner.setSelection(dateAdapter.getPosition(selectedDate))

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.onSelectDate(screeningDates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun updateTimeSpinner(
        showtimes: List<LocalTime>,
        selectedTime: LocalTime,
    ) {
        timeAdapter.clear()
        timeAdapter.addAll(showtimes)
        timeSpinner.setSelection(timeAdapter.getPosition(selectedTime))

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.onSelectTime(showtimes[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun navigateToCompleteScreen(ticket: TicketUiModel) {
        val intent = SeatSelectActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun setView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initView() {
        initSpinners()
        initButtons()
        initSelectButton()
    }

    private fun initSpinners() {
        dateAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf<LocalDate>())
        timeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf<LocalTime>())
        dateSpinner.adapter = dateAdapter
        timeSpinner.adapter = timeAdapter
    }

    private fun initButtons() {
        decrementButton.setOnClickListener { presenter.onClickDecrementButton() }
        incrementButton.setOnClickListener { presenter.onClickIncrementButton() }
    }

    private fun initSelectButton() {
        val selectButton: Button = findViewById(R.id.select_button)
        selectButton.setOnClickListener {
            if (!dateSpinner.adapter.isEmpty && !timeSpinner.adapter.isEmpty) {
                presenter.completeReservation()
            } else {
                Toast.makeText(this, R.string.select_date_and_time, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            movie: MovieUiModel,
        ): Intent =
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }

        private const val EXTRA_MOVIE = "extra_movie"
        private const val EXTRA_TICKET = "extra_ticket"
    }
}
