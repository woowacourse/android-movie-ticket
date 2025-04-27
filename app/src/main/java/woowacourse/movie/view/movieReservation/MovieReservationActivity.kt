package woowacourse.movie.view.movieReservation

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
import woowacourse.movie.presenter.movieReservation.MovieReservationContract
import woowacourse.movie.presenter.movieReservation.MovieReservationPresenter
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_TICKET
import woowacourse.movie.view.seatSelection.SeatSelectionActivity
import woowacourse.movie.view.utils.getParcelableCompat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private val presenter = MovieReservationPresenter(this)
    private val dateSpinner by lazy { findViewById<Spinner>(R.id.date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.time_spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        val movie: MovieUiModel = intent.extras?.getParcelableCompat<MovieUiModel>(KEY_MOVIE) ?: return
        presenter.onViewCreated(movie)
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
        presenter.onTicketCountChange()

        val incrementButton = findViewById<Button>(R.id.increment_button)
        incrementButton.setOnClickListener {
            presenter.onTicketCountIncrement()
            presenter.onTicketCountChange()
        }

        val decrementButton = findViewById<Button>(R.id.decrement_button)
        decrementButton.setOnClickListener {
            presenter.onTicketCountDecrement()
            presenter.onTicketCountChange()
        }
    }

    private fun initializeReserveButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        selectButton.setOnClickListener { presenter.onConfirmSelection() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_TICKET, presenter.ticket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val ticket = savedInstanceState.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: return
        presenter.onInstanceStateRestored(ticket)
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
                    presenter.onDateSelection(selectedDate)
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
                    presenter.onTimeSelection(selectedTime)
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
        val dateTimeFormatter = DateTimeFormatter.ofPattern(getString(R.string.format_date))
        val screeningDateTextView = findViewById<TextView>(R.id.screening_date)
        screeningDateTextView.text =
            getString(R.string.template_screening_dates).format(
                dateTimeFormatter.format(startDate), dateTimeFormatter.format(endDate),
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
        const val KEY_MOVIE = "movie"

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
