package woowacourse.movie.feature.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.feature.complete.ui.MovieReservationCompleteUiModel
import woowacourse.movie.feature.home.MovieHomeActivity
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException

class MovieReservationCompleteActivity :
    BaseActivity<MovieReservationCompleteContract.Presenter>(),
    MovieReservationCompleteContract.View {
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_time_text) }
    private val seatsInfoText by lazy { findViewById<TextView>(R.id.seats_info_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        if (validateError()) return
        initializeView()
    }

    override fun initializePresenter(): MovieReservationCompletePresenter {
        return MovieReservationCompletePresenter(this, MovieRepositoryImpl, TicketRepositoryImpl)
    }

    private fun validateError(): Boolean {
        if (isError(ticketId())) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return true
        }
        return false
    }

    private fun ticketId() = intent.getLongExtra(TICKET_ID_KEY, TICKET_ID_DEFAULT_VALUE)

    private fun isError(ticketId: Long) = ticketId == TICKET_ID_DEFAULT_VALUE

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeView() {
        presenter.loadTicketData(ticketId())
        presenter.loadMovieData(ticket.movieId)
        addBackPressedCallback()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initializeTicket(ticket: Ticket) {
        this.ticket = ticket
    }

    override fun initializeReservationCompleteView(movie: Movie) {
        val reservationComplete = MovieReservationCompleteUiModel.of(this, movie, ticket)
        with(reservationComplete) {
            titleText.text = titleMessage
            screeningDateText.text = screeningDateTimeMessage
            seatsInfoText.text = seatsInfoMessage
            reservationAmountText.text = reservationAmountMessage
        }
    }

    private fun addBackPressedCallback() {
        val onBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    MovieHomeActivity.startActivity(this@MovieReservationCompleteActivity)
                }
            }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> MovieHomeActivity.startActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val TICKET_ID_KEY = "ticket_id_key"
        private const val TICKET_ID_DEFAULT_VALUE = -1L

        fun startActivity(
            context: Context,
            ticketId: Long,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                putExtra(TICKET_ID_KEY, ticketId)
                context.startActivity(this)
            }
        }
    }
}
