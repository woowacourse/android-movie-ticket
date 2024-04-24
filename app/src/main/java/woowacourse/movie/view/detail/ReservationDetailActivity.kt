package woowacourse.movie.view.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieUtils.convertPeriodFormat
import woowacourse.movie.R
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.detail.ReservationDetailContract
import woowacourse.movie.presenter.detail.ReservationDetailPresenter
import woowacourse.movie.view.finished.ReservationFinishedActivity
import woowacourse.movie.view.home.ReservationHomeActivity.Companion.MOVIE_ID
import java.io.Serializable

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)

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
        outState.putSerializable(TICKET, presenter.ticket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val ticket = it.bundleSerializable(TICKET, Ticket::class.java) ?: Ticket()
            presenter.ticket.restoreTicket(ticket.count)
            numberOfTickets.text = presenter.ticket.count.toString()
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

    override fun showResultToast() {
        Toast.makeText(this, getString(R.string.invalid_number_of_tickets), Toast.LENGTH_SHORT)
            .show()
    }

    override fun navigateToFinished(
        movieId: Int,
        ticket: Ticket,
    ) {
        val intent = Intent(this, ReservationFinishedActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        intent.putExtra(TICKET, ticket)
        startActivity(intent)
        finish()
    }

    private fun updateScreeningTimes(movieId: Int) {
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("selectDate", "nothingSelected")
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
            Log.d("screeningDate", screeningPeriodSpinner.selectedItem.toString())
            Log.d("screeningDate", screeningTimeSpinner.selectedItem.toString())
            presenter.initializeReservationButton(movieId)
        }
    }

    private fun <T : Serializable> Bundle.bundleSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializable(key, clazz)
        } else {
            this.getSerializable(key) as T?
        }
    }

    companion object {
        const val DEFAULT_MOVIE_ID = 0
        const val TICKET = "ticket"
    }
}
