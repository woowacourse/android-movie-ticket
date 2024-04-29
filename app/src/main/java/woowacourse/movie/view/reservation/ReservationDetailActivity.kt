package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.ScreeningDateTime
import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.presenter.reservation.ReservationDetailContract
import woowacourse.movie.presenter.reservation.ReservationDetailPresenter
import woowacourse.movie.utils.MovieUtils.bundleSerializable
import woowacourse.movie.utils.MovieUtils.convertPeriodFormat
import woowacourse.movie.utils.MovieUtils.makeToast
import woowacourse.movie.view.home.ReservationHomeActivity.Companion.MOVIE_ID

class ReservationDetailActivity : AppCompatActivity(), ReservationDetailContract.View {
    private val presenter: ReservationDetailPresenter = ReservationDetailPresenter(this, ScreeningDao())

    private val poster: ImageView by lazy { findViewById(R.id.image_view_reservation_detail_poster) }
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_detail_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_screening_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.text_view_reservation_running_time) }
    private val summary: TextView by lazy { findViewById(R.id.text_view_reservation_summary) }
    private val screeningPeriodSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_detail_screening_date) }
    private val screeningTimeSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_detail_screening_time) }
    private val minusButton: Button by lazy { findViewById(R.id.button_reservation_detail_minus) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_detail_number_of_tickets) }
    private val plusButton: Button by lazy { findViewById(R.id.button_reservation_detail_plus) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_reservation_detail_finished) }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)

        with(presenter) {
            loadMovie(movieId)
            loadScreeningPeriod(movieId)
        }
        updateScreeningTimes(movieId)
        initializeMinusButton()
        initializePlusButton()
        initializeReservationButton(movieId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putSerializable(TICKET, presenter.ticket)
            putInt(SCREENING_PERIOD, screeningPeriodSpinner.selectedItemPosition)
            putInt(SCREENING_TIME, screeningTimeSpinner.selectedItemPosition)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let { bundle ->
            runCatching {
                bundle.bundleSerializable(TICKET, Ticket::class.java) ?: throw NoSuchElementException()
            }.onSuccess { ticket ->
                presenter.ticket.restoreTicket(ticket.count)
                numberOfTickets.text = presenter.ticket.count.toString()
            }.onFailure {
                showErrorToast()
                finish()
            }

            val selectedTimeId = bundle.getInt(SCREENING_TIME, 0)
            updateScreeningTimes(movieId, selectedTimeId)
        }
    }

    override fun showMovieInformation(movie: Movie) {
        poster.setImageResource(movie.posterId)
        title.text = movie.title
        screeningDate.text = convertPeriodFormat(movie.screeningPeriod)
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    override fun showScreeningPeriod(movie: Movie) {
        screeningPeriodSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                movie.screeningPeriod,
            )
    }

    override fun showScreeningTimes(
        movie: Movie,
        selectedDate: String,
    ) {
        screeningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                movie.screeningTimes.loadScheduleByDateType(selectedDate),
            )
    }

    override fun changeHeadCount(count: Int) {
        numberOfTickets.text = count.toString()
    }

    override fun showResultToast() = makeToast(this, getString(R.string.invalid_number_of_tickets))

    override fun showErrorToast() = makeToast(this, getString(R.string.all_error))

    override fun navigateToSeatSelection(
        movieId: Int,
        ticket: Ticket,
    ) {
        val intent = Intent(this, SeatSelectionActivity::class.java)
        intent.apply {
            putExtra(MOVIE_ID, movieId)
            putExtra(TICKET, ticket)
        }
        startActivity(intent)
    }

    private fun updateScreeningTimes(
        movieId: Int,
        selectedTimeId: Int? = null,
    ) {
        screeningPeriodSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = screeningPeriodSpinner.selectedItem.toString()
                    presenter.loadScreeningTimes(movieId, selectedDate)
                    selectedTimeId?.let {
                        screeningTimeSpinner.setSelection(it)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d(SELECTED_DATE_TAG, NOTHING_SELECTED_MESSAGE)
                }
            }
    }

    private fun initializeMinusButton() {
        minusButton.setOnClickListener {
            presenter.decreaseTicketCount(presenter.ticket.count)
        }
    }

    private fun initializePlusButton() {
        plusButton.setOnClickListener {
            presenter.increaseTicketCount(presenter.ticket.count)
        }
    }

    private fun initializeReservationButton(movieId: Int) {
        reservationButton.setOnClickListener {
            val date = screeningPeriodSpinner.selectedItem.toString()
            val time = screeningTimeSpinner.selectedItem.toString()
            val dateTime = ScreeningDateTime(date, time)
            presenter.initializeReservationButton(movieId, dateTime)
        }
    }

    companion object {
        const val DEFAULT_MOVIE_ID = 0
        const val TICKET = "ticket"
        private const val SCREENING_TIME = "screeningTime"
        private const val SCREENING_PERIOD = "screeningPeriod"
        const val SELECTED_DATE_TAG = "notSelectedDate"
        const val NOTHING_SELECTED_MESSAGE = "nothingSelected"
    }
}
