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
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SelectedSeats
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

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
        val selectedSeatsArrayList = selectedSeats()!!
        val selectedSeats =
            SelectedSeats(ReservationCount(selectedSeatsArrayList.size)).apply {
                selectedSeatsArrayList.forEach { select(it) }
            }
        ticket = Ticket(movieId(), screeningDateTime()!!, selectedSeats)
        initializeView()
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieRepositoryImpl)

    private fun validateError(): Boolean {
        if (isError(movieId(), screeningDateTime(), selectedSeats())) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return true
        }
        return false
    }

    private fun movieId() = intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)

    private fun screeningDateTime(): LocalDateTime? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(SCREENING_DATE_TIME_KEY, LocalDateTime::class.java)
        } else {
            intent.getSerializableExtra(SCREENING_DATE_TIME_KEY) as? LocalDateTime
        }
    }

    private fun selectedSeats(): ArrayList<Seat>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(SELECTED_SEATS_KEY, Seat::class.java)
        } else {
            intent.getParcelableArrayListExtra(SELECTED_SEATS_KEY)
        }
    }

    private fun isError(
        movieId: Long,
        screeningDateTime: LocalDateTime?,
        selectedSeats: List<Seat>?,
    ): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE || screeningDateTime == null || selectedSeats == null
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeView() {
        presenter.loadMovieData(movieId())
        addBackPressedCallback()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        private const val MOVIE_ID_KEY = "movie_id_key"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val SCREENING_DATE_TIME_KEY = "screening_date_time_key"
        private const val SELECTED_SEATS_KEY = "selected_seats_key"

        fun startActivity(
            context: Context,
            movieId: Long,
            screeningDateTime: LocalDateTime,
            selectedSeats: List<Seat>,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                putExtra(SCREENING_DATE_TIME_KEY, screeningDateTime)
                putParcelableArrayListExtra(SELECTED_SEATS_KEY, ArrayList(selectedSeats))
                context.startActivity(this)
            }
        }
    }
}
