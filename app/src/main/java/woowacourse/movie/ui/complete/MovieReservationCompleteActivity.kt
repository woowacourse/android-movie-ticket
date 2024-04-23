package woowacourse.movie.ui.complete

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.MovieDate
import woowacourse.movie.model.movie.Ticket
import woowacourse.movie.ui.base.BaseActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieReservationCompleteActivity :
    BaseActivity<MovieReservationCompleteContract.Presenter>(),
    MovieReservationCompleteContract.View {
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val movieContentId = movieContentId()
        val reservationCount = reservationCount()
        if (movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE || reservationCount == RESERVATION_COUNT_DEFAULT_VALUE) {
            handleError()
            return
        }

        setUpUi(movieContentId, reservationCount)
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieContentsImpl)

    private fun movieContentId() = intent.getLongExtra(MovieReservationCompleteKey.ID, MOVIE_CONTENT_ID_DEFAULT_VALUE)

    private fun reservationCount() = intent.getIntExtra(MovieReservationCompleteKey.COUNT, RESERVATION_COUNT_DEFAULT_VALUE)

    override fun handleError() {
        Log.e(TAG, "Invalid Key")
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(
        movieContentId: Long,
        reservationCount: Int,
    ) {
        presenter.loadMovieContent(movieContentId)
        presenter.updateTicket(reservationCount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            titleText.text = title
            screeningDateText.text =
                resources.getString(R.string.date)
                    .format(dateFormatter(screeningMovieDate))
        }
    }

    override fun updateTicketUi(ticket: Ticket) {
        ticket.run {
            reservationCountText.text =
                resources.getString(R.string.reservation_count).format(reservationCount.count)
            reservationAmountText.text =
                resources.getString(R.string.reservation_amount).format(amount())
        }
    }

    private fun dateFormatter(movieDate: MovieDate): String {
        val screeningDate = LocalDate.of(movieDate.year, movieDate.month, movieDate.day)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return screeningDate.format(formatter)
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
    }
}
