package woowacourse.movie.feature.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.feature.complete.MovieReservationCompleteActivity
import woowacourse.movie.feature.reservation.ui.toReservationUiModel
import woowacourse.movie.model.ScreeningDate
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.ScreeningTime
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.format.DateTimeFormatter

class MovieReservationActivity :
    BaseActivity<MovieReservationContract.Presenter>(),
    MovieReservationContract.View {
    private val posterImage by lazy { findViewById<ImageView>(R.id.poster_image) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val runningTimeText by lazy { findViewById<TextView>(R.id.running_time_text) }
    private val synopsisText by lazy { findViewById<TextView>(R.id.synopsis_text) }
    private val screeningDateSpinner by lazy { findViewById<Spinner>(R.id.screening_date_spinner) }
    private val screeningTimeSpinner by lazy { findViewById<Spinner>(R.id.screening_time_spinner) }
    private val minusButton by lazy { findViewById<Button>(R.id.minus_button) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val plusButton by lazy { findViewById<Button>(R.id.plus_button) }
    private val reservationButton by lazy { findViewById<Button>(R.id.seat_select_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movieId = movieId()
        if (isError(movieId)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        initializeView(movieId)
        setOnClickButtonListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val reservationCountValue =
            savedInstanceState.getInt(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
        presenter.updateReservationCount(reservationCountValue)
    }

    override fun initializePresenter() = MovieReservationPresenter(this, MovieRepositoryImpl)

    private fun movieId(): Long {
        return intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)
    }

    private fun isError(movieId: Long): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeView(movieId: Long) {
        presenter.loadMovieData(movieId)
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

    override fun setUpReservationView(movie: Movie) {
        val reservation = movie.toReservationUiModel(this)
        with(reservation) {
            posterImage.setImageDrawable(posterImageDrawable)
            titleText.text = titleMessage
            screeningDateText.text = screeningDateMessage
            runningTimeText.text = runningTimeMessage
            synopsisText.text = synopsisMessage
        }
    }

    override fun initializeSpinner(
        screeningDates: List<ScreeningDate>,
        screeningTimes: List<ScreeningTime>,
    ) {
        screeningDateSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningDates.map { it.message() })
        screeningDateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position :Int, id: Long) {
                presenter.selectScreeningDate(screeningDates[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }
        screeningTimeSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTimes)
    }

    private fun ScreeningDate.message() = date.format(DateTimeFormatter.ofPattern("yyyy-M-d"))

    override fun updateReservationCount(reservationCountValue: Int) {
        reservationCountText.text = reservationCountValue.toString()
    }

    override fun moveReservationCompleteView(reservationCountValue: Int) {
        MovieReservationCompleteActivity.startActivity(this, movieId(), reservationCountValue)
    }

    override fun updateScreeningTimeSpinner(screeningTimes: List<ScreeningTime>) {
        val screeningTimeMessage = screeningTimes.map { it.time.format(DateTimeFormatter.ofPattern("HH:mm"))}
        screeningTimeSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTimeMessage)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MOVIE_RESERVATION_COUNT_KEY, reservationCountText.text.toString().toInt())
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val MOVIE_RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = 1

        fun startActivity(
            context: Context,
            movieId: Long,
        ) {
            Intent(context, MovieReservationActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                context.startActivity(this)
            }
        }
    }
}
