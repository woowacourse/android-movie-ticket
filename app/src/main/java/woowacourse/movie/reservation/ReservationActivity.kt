package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.reservation.TicketCount
import woowacourse.movie.R
import woowacourse.movie.getIntentData
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.SeatSelectionInfo
import woowacourse.movie.movies.MoviesActivity.Companion.MOVIE_KEY
import woowacourse.movie.seatselection.ScreeningSeatSelectionActivity

class ReservationActivity : AppCompatActivity() {

    private val navigationViewSetter: NavigationViewSetter by lazy {
        NavigationViewSetter(movieInfo, findViewById(R.id.reservation_navigation_bar))
    }
    private lateinit var movieInfo: MovieInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        movieInfo = this.getIntentData(MOVIE_KEY) ?: MovieInfo.ofError()
        setMovieInfoView()
        setNavigationBar(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        navigationViewSetter.saveState(outState)
    }

    private fun setMovieInfoView() {
        val movieInfoViewSetter = MovieInfoViewSetter(findViewById(R.id.movie_information_view))

        movieInfoViewSetter.setView(movieInfo)
    }

    private fun setNavigationBar(savedInstanceState: Bundle?) {
        with(navigationViewSetter) {
            setDateSpinner(savedInstanceState)
            setMinusButtonClickedListener { alertTicketCountError() }
            setPlusButtonClickedListener()
            setOnCompleteButtonClickedListener(::onFinished)
            setTicketCountTextView(savedInstanceState)
        }
    }

    private fun alertTicketCountError(
        errorMessage: String = getString(R.string.ticket_count_condition_message_form).format(
            TicketCount.MINIMUM
        )
    ) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun onFinished(seatSelectionInfo: SeatSelectionInfo) {
        val intent = Intent(this, ScreeningSeatSelectionActivity::class.java)

        intent.putExtra(SEAT_SELECTION_KEY, seatSelectionInfo)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TICKET_COUNT_KEY = "ticket_key"
        const val SCREENING_DATE_POSITION_KEY = "screening_date_key"
        const val SCREENING_TIME_POSITION_KEY = "screening_time_key"
        const val SEAT_SELECTION_KEY = "reservation_key"
    }
}
