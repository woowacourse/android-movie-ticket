package woowacourse.movie.feature.movieReservation

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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.seatSelection.SeatSelectionActivity
import woowacourse.movie.util.Formatter
import woowacourse.movie.util.getParcelableCompat
import java.time.LocalDate
import java.time.LocalTime

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private val presenter = MovieReservationPresenter(this)
    private val dateSpinner by lazy { findViewById<Spinner>(R.id.date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.time_spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        val movie: MovieUiModel = intent.extras?.getParcelableCompat<MovieUiModel>(KEY_MOVIE) ?: return
        presenter.loadReservationInfo(movie)
        initializeTicketCountButtons()
        initializeReserveButton()
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeTicketCountButtons() {
        presenter.updateTicketCountControls()

        val incrementButton = findViewById<Button>(R.id.increment_button)
        incrementButton.setOnClickListener {
            presenter.incrementTicketCount()
            presenter.updateTicketCountControls()
        }

        val decrementButton = findViewById<Button>(R.id.decrement_button)
        decrementButton.setOnClickListener {
            presenter.decrementTicketCount()
            presenter.updateTicketCountControls()
        }
    }

    private fun initializeReserveButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        selectButton.setOnClickListener { presenter.confirmSelection() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_TICKET, presenter.ticket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val ticket = savedInstanceState.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: return
        presenter.restoreReservationInfo(ticket)
    }

    override fun showSpinnerDates(dates: List<LocalDate>) {
        val dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateSpinner.adapter = dateAdapter

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate: LocalDate = dates[position]
                    presenter.selectDate(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showSpinnerTimes(
        times: List<LocalTime>,
        savedTime: LocalTime,
    ) {
        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
        timeSpinner.adapter = timeAdapter

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime: LocalTime = times[position]
                    presenter.selectTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        savedTime.let { timeSpinner.setSelection(times.indexOf(savedTime)) }
    }

    override fun setTimeSpinner(position: Int) {
        timeSpinner.setSelection(position)
    }

    override fun showMoviePoster(posterImage: Int) {
        val posterImageView = findViewById<ImageView>(R.id.poster)
        posterImageView.setImageResource(posterImage)
    }

    override fun showMovieTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = title
    }

    override fun showScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val screeningDateTextView = findViewById<TextView>(R.id.screening_date)
        screeningDateTextView.text =
            getString(R.string.template_screening_dates).format(
                Formatter.format(startDate), Formatter.format(endDate),
            )
    }

    override fun showRunningTime(runningTime: Int) {
        val runningTimeTextView = findViewById<TextView>(R.id.running_time)
        runningTimeTextView.text = getString(R.string.template_running_type).format(runningTime)
    }

    override fun showTicketCount(count: Int) {
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)
        ticketCountTextView.text = count.toString()
    }

    override fun setIncrementEnabled(canIncrement: Boolean) {
        val incrementButton = findViewById<Button>(R.id.increment_button)
        incrementButton.isEnabled = canIncrement
    }

    override fun setDecrementEnabled(canDecrement: Boolean) {
        val decrementButton = findViewById<Button>(R.id.decrement_button)
        decrementButton.isEnabled = canDecrement
    }

    override fun goToSeatSelection(ticket: TicketUiModel) {
        val intent = SeatSelectionActivity.createIntent(this, ticket)
        startActivity(intent)
    }

    companion object {
        const val KEY_TICKET = "ticket"
        private const val KEY_MOVIE = "movie"

        fun createIntent(
            context: Context,
            movie: MovieUiModel,
        ): Intent {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            return intent
        }
    }
}
