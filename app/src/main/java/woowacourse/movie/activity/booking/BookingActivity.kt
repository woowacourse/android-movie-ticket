package woowacourse.movie.activity.booking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.seatselection.SeatSelectionActivity
import woowacourse.movie.adapter.SpinnerAdapter
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.MovieUiModel

class BookingActivity :
    AppCompatActivity(),
    BookingContract.View {
    private lateinit var presenter: BookingContract.Presenter
    private val ticketCount: TextView by lazy { findViewById(R.id.ticket_count) }
    private val movieDate: Spinner by lazy { findViewById(R.id.movie_date) }
    private val movieTime: Spinner by lazy { findViewById(R.id.movie_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        presenter = BookingPresenter(this)

        val movie: Movie =
            intent.getParcelableExtra(KEY_MOVIE_INFO)
                ?: run {
                    Toast
                        .makeText(this, R.string.no_movie_data_error_message, Toast.LENGTH_SHORT)
                        .show()
                    finish()
                    return
                }

        presenter.initData(movie)

        setupDateChangeDateListener()
        setupDateChangeTimeListener()
        countButtonHandler()
        confirmButtonHandler()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreState(savedInstanceState)
    }

    private fun setupDateChangeDateListener() {
        movieDate.onItemSelectedListener =
            SpinnerSelectedListener(
                currentPositionProvider = { presenter.getSelectedDate() },
                onChanged = { position -> presenter.selectDate(position) },
            )
    }

    private fun setupDateChangeTimeListener() {
        movieTime.onItemSelectedListener =
            SpinnerSelectedListener(
                currentPositionProvider = { presenter.getSelectedTime() },
                onChanged = { position -> presenter.selectTime(position) },
            )
    }

    private fun confirmButtonHandler() {
        val confirmButton: Button = findViewById(R.id.confirm_button)

        confirmButton.setOnClickListener {
            presenter.confirmBooking()
        }
    }

    private fun countButtonHandler() {
        val plusButton: Button = findViewById(R.id.plus_button)
        val minusButton: Button = findViewById(R.id.minus_button)

        plusButton.setOnClickListener {
            presenter.increaseTicketCount()
        }

        minusButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }
    }

    override fun setupPage(movieUiModel: MovieUiModel) {
        findViewById<ImageView>(R.id.movie_poster).setImageResource(movieUiModel.poster)
        findViewById<TextView>(R.id.title).text = movieUiModel.title
        findViewById<TextView>(R.id.start_date).text = movieUiModel.startDate
        findViewById<TextView>(R.id.end_date).text = movieUiModel.endDate
        findViewById<TextView>(R.id.running_time).text = movieUiModel.runningTime
    }

    override fun moveToSeatSelection(ticket: Ticket) {
        val intent =
            Intent(this, SeatSelectionActivity::class.java).apply {
                putExtra(KEY_TICKET, ticket)
            }
        startActivity(intent)
    }

    override fun showTicketCount(count: Int) {
        ticketCount.text = "$count"
    }

    override fun updateDateSpinner(
        dates: List<String>,
        selectedPosition: Int,
    ) {
        SpinnerAdapter.bind(this, movieDate, dates)
        movieDate.setSelection(selectedPosition)
    }

    override fun updateTimeSpinner(
        times: List<String>,
        selectedPosition: Int,
    ) {
        SpinnerAdapter.bind(this, movieTime, times)
        movieTime.setSelection(selectedPosition)
    }

    companion object {
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        const val KEY_TICKET = "TICKET"
    }
}
