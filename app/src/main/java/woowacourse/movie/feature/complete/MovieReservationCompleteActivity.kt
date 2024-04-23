package woowacourse.movie.feature.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
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
        val reservationCountValue = reservationCountValue()
        if (isError(movieContentId, reservationCountValue)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        setUpUi(movieContentId, reservationCountValue)
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieContentsImpl)

    private fun movieContentId(): Long {
        return intent.getLongExtra(MOVIE_CONTENT_ID_KEY, MOVIE_CONTENT_ID_DEFAULT_VALUE)
    }

    private fun reservationCountValue(): Int {
        return intent.getIntExtra(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
    }

    private fun isError(
        movieContentId: Long,
        reservationCountValue: Int,
    ): Boolean {
        return movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE || reservationCountValue == RESERVATION_COUNT_DEFAULT_VALUE
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(
        movieContentId: Long,
        reservationCountValue: Int,
    ) {
        presenter.setUpMovieContent(movieContentId)
        presenter.setUpTicket(reservationCountValue)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        titleText.text = movieContent.title
        screeningDateText.text = movieContent.screeningDate.message()
    }

    override fun setUpTicketUi(ticket: Ticket) {
        reservationCountText.text =
            resources.getString(R.string.reservation_count).format(ticket.reservationCount.count)
        reservationAmountText.text =
            resources.getString(R.string.reservation_amount).format(ticket.amount())
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
            reservationCountValue: Int,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                putExtra(MOVIE_CONTENT_ID_KEY, movieContentId)
                putExtra(MOVIE_RESERVATION_COUNT_KEY, reservationCountValue)
                context.startActivity(this)
            }
        }
    }
}
