package woowacourse.movie.feature.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.feature.complete.ui.ReservationCompleteEntity
import woowacourse.movie.feature.complete.ui.toReservationCompleteUiModel
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

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

        val movieId = movieId()
        val reservationCountValue = reservationCountValue()
        if (isError(movieId, reservationCountValue)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        initializeView(movieId, reservationCountValue)
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieRepositoryImpl)

    private fun movieId(): Long {
        return intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)
    }

    private fun reservationCountValue(): Int {
        return intent.getIntExtra(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
    }

    private fun isError(
        movieId: Long,
        reservationCountValue: Int,
    ): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE || reservationCountValue == RESERVATION_COUNT_DEFAULT_VALUE
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeView(
        movieId: Long,
        reservationCountValue: Int,
    ) {
        // TODO("다른 액티비티에서 상영시간 받아오기")
        presenter.loadMovieData(
            movieId,
            LocalDateTime.of(2024, 3, 1, 9, 0),
            reservationCountValue
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpReservationCompleteView(
        movie: Movie,
        ticket: Ticket,
    ) {
        val reservationComplete =
            ReservationCompleteEntity(movie, ticket).toReservationCompleteUiModel(this)
        with(reservationComplete) {
            titleText.text = titleMessage
            screeningDateText.text = screeningDateMessage
            reservationCountText.text = reservationCountMessage
            reservationAmountText.text = reservationAmountMessage
        }
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val MOVIE_RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1

        fun startActivity(
            context: Context,
            movieId: Long,
            reservationCountValue: Int,
        ) {
            Intent(context, MovieReservationCompleteActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                putExtra(MOVIE_RESERVATION_COUNT_KEY, reservationCountValue)
                context.startActivity(this)
            }
        }
    }
}
