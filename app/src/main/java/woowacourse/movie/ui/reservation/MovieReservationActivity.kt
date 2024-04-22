package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.MovieDate
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.complete.MovieReservationCompleteActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieReservationActivity :
    BaseActivity<MovieReservationContract.Presenter>(),
    MovieReservationContract.View {
    private val presenter: MovieReservationContract.Presenter by lazy { initializePresenter() }
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
        if (movieContentId == DEFAULT_VALUE) {
            handleError()
            return
        }

        initializeUi(movieContentId)
        setOnClickButtonListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(RESERVATION_COUNT_STATE_KEY, reservationCountText.text.toString().toInt())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            val count = it.getInt(RESERVATION_COUNT_STATE_KEY)
            presenter.updateReservationCount(count)
        }
    }

    override fun initializePresenter() = MovieReservationPresenter(this, MovieContentsImpl)

    private fun movieContentId() = intent.getLongExtra(MovieReservationKey.ID, DEFAULT_VALUE)

    override fun handleError() {
        Log.e(TAG, "Invalid MovieContentKey")
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeUi(movieContentId: Long) {
        presenter.updateMovieContent(movieContentId)
        presenter.updateReservationCount()
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
            presenter.decreaseCount()
        }

        plusButton.setOnClickListener {
            presenter.increaseCount()
        }

        reservationButton.setOnClickListener {
            presenter.reserveMovie()
        }
    }

    override fun updateMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text =
                resources.getString(R.string.screening_date)
                    .format(dateFormatter(screeningMovieDate))
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }
    }

    override fun updateReservationCountUi(reservationCount: Int) {
        reservationCountText.text = reservationCount.toString()
    }

    override fun moveMovieReservationCompleteView(reservationCount: Int) {
        Intent(this, MovieReservationCompleteActivity::class.java).run {
            putExtra(MovieReservationKey.ID, movieContentId())
            putExtra(MovieReservationKey.COUNT, reservationCount)
            startActivity(this)
        }
    }

    private fun dateFormatter(movieDate: MovieDate): String {
        val screeningDate = LocalDate.of(movieDate.year, movieDate.month, movieDate.day)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return screeningDate.format(formatter)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_STATE_KEY = "reservationCount"
    }
}
