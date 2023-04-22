package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.reservation.TicketCount
import woowacourse.movie.R
import woowacourse.movie.getIntentData
import woowacourse.movie.model.DisplayItem
import woowacourse.movie.model.SeatSelectionInfo
import woowacourse.movie.movies.MoviesActivity.Companion.MOVIE_KEY
import woowacourse.movie.seatselection.ScreeningSeatSelectionActivity
import woowacourse.movie.util.getSerializableExtraByKey

class ReservationActivity : AppCompatActivity() {

    private val navigationView: NavigationView by lazy {
        NavigationView(movieInfo, findViewById(R.id.reservation_navigation_bar))
    }
    private lateinit var movieInfo: DisplayItem.MovieInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        movieInfo = this.getIntentData(MOVIE_KEY) ?: DisplayItem.MovieInfo.ofError()
        setMovieInfoView()
        setNavigationBar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(NAVIGATION_VIEW_STATE_KEY, navigationView.state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getSerializableExtraByKey<NavigationViewState>(NAVIGATION_VIEW_STATE_KEY)
            ?.apply {
                navigationView.setDateSpinner(
                    dateSpinnerPosition,
                    timeSpinnerPosition
                )
            }
    }

    private fun setMovieInfoView() {
        val movieInfoView = MovieInfoView(findViewById(R.id.movie_information_view))

        movieInfoView.bind(movieInfo)
    }

    private fun setNavigationBar() {
        with(navigationView) {
            setDateSpinner()
            setMinusButtonClickedListener { alertTicketCountError() }
            setPlusButtonClickedListener()
            setOnCompleteButtonClickedListener(::onCompleted)
            setTicketCountTextView()
        }
    }

    private fun alertTicketCountError(
        errorMessage: String = getString(R.string.ticket_count_condition_message_form).format(
            TicketCount.MINIMUM
        )
    ) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun onCompleted(seatSelectionInfo: SeatSelectionInfo) {
        val intent = Intent(this, ScreeningSeatSelectionActivity::class.java)

        intent.putExtra(SEAT_SELECTION_KEY, seatSelectionInfo)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val NAVIGATION_VIEW_STATE_KEY = "navigation_view_state_key"
        const val SEAT_SELECTION_KEY = "reservation_key"
    }
}
