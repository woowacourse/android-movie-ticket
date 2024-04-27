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
import woowacourse.movie.feature.reservation.ui.ReservationUiModel
import woowacourse.movie.feature.seat.SeatSelectActivity
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

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
    private val seatSelectButton by lazy { findViewById<Button>(R.id.seat_select_button) }
    private lateinit var screeningTimeSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        if (validateError()) return
        initializeView(movieId())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreReservationCount(savedInstanceState)
        restoreScreeningDateSpinner(savedInstanceState)
        restoreScreeningTimeSpinner(savedInstanceState)
    }

    private fun restoreReservationCount(savedInstanceState: Bundle) {
        val reservationCountValue =
            savedInstanceState.getInt(MOVIE_RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)
        presenter.updateReservationCount(reservationCountValue)
    }

    private fun restoreScreeningDateSpinner(savedInstanceState: Bundle) {
        val screeningDateSpinnerPosition =
            savedInstanceState.getInt(
                SCREENING_DATE_SPINNER_POSITION_KEY,
                SCREENING_DATE_SPINNER_POSITION_DEFAULT_VALUE,
            )
        screeningDateSpinner.setSelection(screeningDateSpinnerPosition)
    }

    private fun restoreScreeningTimeSpinner(savedInstanceState: Bundle) {
        val screeningTimeSpinnerPosition =
            savedInstanceState.getInt(
                SCREENING_TIME_SPINNER_POSITION_KEY,
                SCREENING_TIME_SPINNER_POSITION_DEFAULT_VALUE,
            )
        screeningTimeSpinner.setSelection(screeningTimeSpinnerPosition)
    }

    override fun initializePresenter() = MovieReservationPresenter(this, MovieRepositoryImpl)

    private fun validateError(): Boolean {
        if (isError(movieId())) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return true
        }
        return false
    }

    private fun movieId() = intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)

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
        presenter.initializeReservationCount()
        setOnClickButtonListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setOnClickButtonListener() {
        minusButton.setOnClickListener {
            presenter.decreaseReservationCount()
        }

        plusButton.setOnClickListener {
            presenter.increaseReservationCount()
        }

        seatSelectButton.setOnClickListener {
            presenter.selectSeat(
                screeningDateSpinner.selectedItem.toString(),
                screeningTimeSpinner.selectedItem.toString(),
            )
        }
    }

    override fun initializeReservationView(movie: Movie) {
        val reservation = ReservationUiModel.of(this, movie)
        with(reservation) {
            posterImage.setImageResource(posterImageId)
            titleText.text = titleMessage
            screeningDateText.text = screeningDateMessage
            runningTimeText.text = runningTimeMessage
            synopsisText.text = synopsisMessage
        }
    }

    override fun initializeSpinner(
        screeningDatesMessage: List<String>,
        screeningTimesMessage: List<String>,
    ) {
        screeningDateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningDatesMessage)
        screeningDateSpinner.onItemSelectedListener =
            spinnerItemSelectedListener { _, _, position, _ ->
                presenter.selectScreeningDate(screeningDatesMessage[position])
            }

        screeningTimeSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTimesMessage)
        screeningTimeSpinner.adapter = screeningTimeSpinnerAdapter
    }

    private fun spinnerItemSelectedListener(block: (AdapterView<*>?, View?, Int, Long) -> Unit): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                block(parent, view, position, id)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    override fun updateReservationCount(reservationCountValue: Int) {
        reservationCountText.text = reservationCountValue.toString()
    }

    override fun moveSeatSelectView(
        screeningDateTime: LocalDateTime,
        reservationCountValue: Int,
    ) {
        SeatSelectActivity.startActivity(this, movieId(), screeningDateTime, reservationCountValue)
    }

    override fun updateScreeningTimeSpinner(screeningTimes: List<String>) {
        screeningTimeSpinnerAdapter.clear()
        screeningTimeSpinnerAdapter.addAll(screeningTimes)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MOVIE_RESERVATION_COUNT_KEY, reservationCountText.text.toString().toInt())
        outState.putInt(
            SCREENING_DATE_SPINNER_POSITION_KEY,
            screeningDateSpinner.selectedItemPosition,
        )
        outState.putInt(
            SCREENING_TIME_SPINNER_POSITION_KEY,
            screeningTimeSpinner.selectedItemPosition,
        )
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val MOVIE_RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = 1
        private const val SCREENING_DATE_SPINNER_POSITION_KEY = "date_spinner_position_key"
        private const val SCREENING_DATE_SPINNER_POSITION_DEFAULT_VALUE = 0
        private const val SCREENING_TIME_SPINNER_POSITION_KEY = "time_spinner_position_key"
        private const val SCREENING_TIME_SPINNER_POSITION_DEFAULT_VALUE = 0

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
