package woowacourse.movie.feature.complete

import android.content.Context
import android.content.Intent
import android.os.Build
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val ticket = ticket()
        if (isError(ticket)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        presenter.loadMovieData(ticket!!.movieId)
        addBackPressedCallback()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieRepositoryImpl)

    private fun ticket(): Ticket? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(TICKET_KEY, Ticket::class.java)
        } else {
            intent.getSerializableExtra(TICKET_KEY) as? Ticket
        }
    }

    private fun isError(ticket: Ticket?) = ticket == null

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> MovieHomeActivity.startActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpReservationCompleteView(movie: Movie) {
        val reservationComplete = MovieReservationCompleteUiModel.of(this, movie, ticket()!!)
        with(reservationComplete) {
            titleText.text = titleMessage
            screeningDateText.text = screeningDateTimeMessage
            seatsInfoText.text = seatsInfoMessage
            reservationAmountText.text = reservationAmountMessage
        }
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val TICKET_KEY = "ticket_key"

        fun startActivity(
            context: Context,
            ticket: Ticket,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                Log.e(TAG, "${ticket.selectedSeats.seats.size}")
                putExtra(TICKET_KEY, ticket)
                context.startActivity(this)
            }
        }
    }
}
