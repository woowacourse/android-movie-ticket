package woowacourse.movie.view.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.detail.ReservationDetailContract
import woowacourse.movie.presenter.detail.ReservationDetailPresenter
import woowacourse.movie.view.finished.ReservationFinishedActivity
import woowacourse.movie.view.home.ReservationHomeActivity.Companion.MOVIE_ID
import java.io.Serializable

class ReservationDetailActivity : AppCompatActivity(), ReservationDetailContract.View {
    private val presenter: ReservationDetailPresenter = ReservationDetailPresenter(this)

    private val poster: ImageView by lazy { findViewById(R.id.image_view_reservation_detail_poster) }
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_detail_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_screening_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.text_view_reservation_running_time) }
    private val summary: TextView by lazy { findViewById(R.id.text_view_reservation_summary) }
    private val minusButton: Button by lazy { findViewById(R.id.button_reservation_detail_minus) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_detail_number_of_tickets) }
    private val plusButton: Button by lazy { findViewById(R.id.button_reservation_detail_plus) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_reservation_detail_finished) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)

        presenter.loadMovie(movieId)
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
        screeningDate.text = movie.screeningDate
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    override fun changeNumberOfTickets(ticket: Ticket) {
        numberOfTickets.text = ticket.count.toString()
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
    }

    private fun initializeMinusButton() {
        minusButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }
    }

    private fun initializePlusButton() {
        plusButton.setOnClickListener {
            presenter.increaseTicketCount()
        }
    }

    private fun initializeReservationButton(movieId: Int) {
        reservationButton.setOnClickListener {
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
