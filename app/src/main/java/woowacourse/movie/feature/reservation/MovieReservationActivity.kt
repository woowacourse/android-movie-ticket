package woowacourse.movie.feature.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.feature.complete.MovieReservationCompleteActivity
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieReservationActivity :
    BaseActivity<MovieReservationContract.Presenter>(),
    MovieReservationContract.View {
    private val posterImage by lazy { findViewById<ImageView>(R.id.poster_image) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val runningTimeText by lazy { findViewById<TextView>(R.id.running_time_text) }
    private val synopsisText by lazy { findViewById<TextView>(R.id.synopsis_text) }
    private val minusButton by lazy { findViewById<Button>(R.id.minus_button) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val plusButton by lazy { findViewById<Button>(R.id.plus_button) }
    private val reservationButton by lazy { findViewById<Button>(R.id.reservation_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movieContentId = movieContentId()
        if (isError(movieContentId)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        setUpUi(movieContentId)
        setOnClickButtonListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val reservationCountValue =
            savedInstanceState.getInt(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
        presenter.updateReservationCount(reservationCountValue)
    }

    override fun initializePresenter() = MovieReservationPresenter(this, MovieContentsImpl)

    private fun movieContentId(): Long {
        return intent.getLongExtra(MOVIE_CONTENT_ID_KEY, MOVIE_CONTENT_ID_DEFAULT_VALUE)
    }

    private fun isError(movieContentId: Long): Boolean {
        return movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(movieContentId: Long) {
        presenter.setUpMovieContent(movieContentId)
        presenter.setUpReservationCount()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnClickButtonListener() {
        minusButton.setOnClickListener {
            presenter.decreaseReservationCount()
        }

        plusButton.setOnClickListener {
            presenter.increaseReservationCount()
        }

        reservationButton.setOnClickListener {
            presenter.reserveMovie()
        }
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            posterImage.setImageResource(posterImageId)
            titleText.text = title
            screeningDateText.text = screeningDate.message()
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }
    }

    private fun LocalDate.message(): String {
        return this@MovieReservationActivity.resources.getString(R.string.screening_date)
            .format(format(DateTimeFormatter.ofPattern("yyyy.M.d")))
    }

    override fun updateReservationCountUi(reservationCountValue: Int) {
        reservationCountText.text = reservationCountValue.toString()
    }

    override fun moveMovieReservationCompleteView(reservationCountValue: Int) {
        MovieReservationCompleteActivity.startActivity(this, movieContentId(), reservationCountValue)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MOVIE_RESERVATION_COUNT_KEY, reservationCountText.text.toString().toInt())
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_KEY = "movie_content_id"
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val MOVIE_RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = 1

        fun startActivity(
            context: Context,
            movieContentId: Long,
        ) {
            Intent(context, MovieReservationActivity::class.java).run {
                putExtra(MOVIE_CONTENT_ID_KEY, movieContentId)
                context.startActivity(this)
            }
        }
    }
}
