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
import woowacourse.movie.view.model.MovieListItem.MovieUiModel
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_TICKET
import woowacourse.movie.view.seatSelection.SeatSelectionActivity
import woowacourse.movie.view.utils.buildAlertDialog
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
        presenter.loadReservationInfo()
        initializeTicketCountButtons()
        initializeReserveButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_TICKET, presenter.ticket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val ticket =
            savedInstanceState.getParcelableCompat<TicketUiModel>(KEY_TICKET)
                ?: run { return }
        presenter.onInstanceStateRestored(ticket)
    }

    override fun setDateSpinner(position: Int) {
        dateSpinner.setSelection(position)
    }

    override fun setTimeSpinner(position: Int) {
        timeSpinner.setSelection(position)
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
        val incrementButton = findViewById<Button>(R.id.increment_button)
        incrementButton.setOnClickListener {
            presenter.incrementTicketCount()
        }

        val decrementButton = findViewById<Button>(R.id.decrement_button)
        decrementButton.setOnClickListener {
            presenter.decrementTicketCount()
        }
    }

    private fun initializeReserveButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        selectButton.setOnClickListener { presenter.onReservation() }
    }

    override fun showReservationInfo(ticket: TicketUiModel) {
        val poster = findViewById<ImageView>(R.id.poster)
        val title = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)

        poster.setImageResource(ticket.movie.poster)
        title.text = ticket.movie.title
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_format))
        val startDate = ticket.movie.startDate.format(formatter)
        val endDate = ticket.movie.endDate.format(formatter)
        screeningDate.text = getString(R.string.screening_dates_format).format(startDate, endDate)
        runningTime.text = getString(R.string.running_type_format).format(ticket.movie.runningTime)
        ticketCountTextView.text = ticket.count.toString()
    }

    override fun showScreeningDates(dates: List<LocalDate>) {
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

    override fun showScreeningTimes(
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

    override fun showTicketCount(count: Int) {
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)
        ticketCountTextView.text = count.toString()
    }

    override fun showAlertDialog() {
        val alertDialog =
            buildAlertDialog(
                title = R.string.confirm_reservation_alert_title,
                message = R.string.confirm_reservation_alert_message,
                yes = R.string.confirm_reservation_alert_yes,
            ) { presenter.onReservationConfirmation() }
        alertDialog.show()
    }

    override fun confirmReservation(ticket: TicketUiModel) {
        val intent = SeatSelectionActivity.createIntent(this, ticket)
        startActivity(intent)
    }

//    private fun showToast() {
//        Toast.makeText(
//            this,
//            getString(R.string.no_screening_date_available_toast_message),
//            Toast.LENGTH_SHORT,
//        ).show()
//    }

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
