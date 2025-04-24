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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.parcelable
import woowacourse.movie.common.parcelableExtra
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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
        initUi()

        val movie =
            intent.parcelableExtra(EXTRA_MOVIE, Movie::class.java) ?: finish().run { return }
        val ticket =
            savedInstanceState?.parcelable(EXTRA_TICKET, Ticket::class.java) ?: Ticket(movie)
        presenter = MovieReservationPresenter(this, ticket)

        initView()
        presenter.loadMovieReservationScreen()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (!dateAdapter.isEmpty && !timeAdapter.isEmpty) {
            outState.putParcelable(EXTRA_TICKET, presenter.ticket)
        }
    }

    override fun showMovieInfo(movie: Movie) {
        val poster: ImageView = findViewById(R.id.poster)
        val title: TextView = findViewById(R.id.movie_title)
        val screeningDate: TextView = findViewById(R.id.screening_date)
        val runningTime: TextView = findViewById(R.id.running_time)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        val startDate = movie.startDate.format(DATE_FORMAT)
        val endDate = movie.endDate.format(DATE_FORMAT)
        screeningDate.text = getString(R.string.screening_date, startDate, endDate)
        runningTime.text = getString(R.string.running_time, movie.runningTime)
    }

    override fun showHeadCount(count: Int) {
        ticketCountTextView.text = count.toString()
        decrementButton.isEnabled = !presenter.ticket.isMinimumCount()
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

    override fun navigateToCompleteScreen(ticket: Ticket) {
        val intent = MovieReservationCompleteActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun initUi() {
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
        decrementButton.setOnClickListener {
            presenter.onClickDecrementButton()
        }
        incrementButton.setOnClickListener {
            presenter.onClickIncrementButton()
        }
    }

    private fun initSelectButton() {
        val alertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(R.string.confirm_reservation)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.complete_reservation) { _, _ -> presenter.completeReservation() }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)

        val selectButton: Button = findViewById(R.id.select_button)
        selectButton.setOnClickListener {
            if (!dateSpinner.adapter.isEmpty && !timeSpinner.adapter.isEmpty) {
                alertDialog.show()
            } else {
                Toast.makeText(this, R.string.select_date_and_time, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }

        private const val EXTRA_MOVIE = "extra_movie"
        private const val EXTRA_TICKET = "extra_ticket"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
