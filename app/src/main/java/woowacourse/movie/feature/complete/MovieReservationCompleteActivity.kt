package woowacourse.movie.feature.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.base.BaseActivity
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
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

    private fun movieContentId(): Long {
        return intent.getLongExtra(MOVIE_CONTENT_ID_KEY, MOVIE_CONTENT_ID_DEFAULT_VALUE)
    }

    private fun reservationCount(): Int {
        return intent.getIntExtra(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
    }

    override fun handleError() {
        Log.e(TAG, "Invalid Key")
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(
        movieContentId: Long,
        reservationCount: Int,
    ) {
        presenter.setUpMovieContent(movieContentId)
        presenter.setUpTicket(reservationCount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            titleText.text = title
            screeningDateText.text = screeningDate.message()
        }
    }

    override fun setUpTicketUi(ticket: Ticket) {
        ticket.run {
            reservationCountText.text =
                resources.getString(R.string.reservation_count).format(reservationCount.count)
            reservationAmountText.text =
                resources.getString(R.string.reservation_amount).format(amount())
        }
    }

    private fun LocalDate.message() = format(DateTimeFormatter.ofPattern("yyyy.M.d"))

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_KEY = "movie_content_id"
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val MOVIE_RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1

        fun startActivity(
            context: Context,
            movieContentId: Long,
            reservationCount: Int,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                putExtra(MOVIE_CONTENT_ID_KEY, movieContentId)
                putExtra(MOVIE_RESERVATION_COUNT_KEY, reservationCount)
                context.startActivity(this)
            }
        }
    }
}
