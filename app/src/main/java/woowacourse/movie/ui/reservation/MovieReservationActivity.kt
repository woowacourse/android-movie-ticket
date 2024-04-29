package woowacourse.movie.ui.reservation

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
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.selection.MovieSeatSelectionActivity
import woowacourse.movie.ui.utils.getImageFromId
import java.time.LocalDate
import java.time.LocalTime
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
    private val movieDateSpinner by lazy { findViewById<Spinner>(R.id.screeningDate_spinner) }
    private val movieTimeSpinner by lazy { findViewById<Spinner>(R.id.screeningTime_spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movieContentId = movieContentId()
        if (movieContentId == DEFAULT_VALUE) {
            presenter.handleError(NoSuchElementException())
            return
        }

        presenter.loadMovieContent(movieContentId)
        presenter.updateReservationCount()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setOnClickMovieDateSpinnerListener()
        setOnClickMovieTimeSpinnerListener()
        setOnClickButtonListener()
    }

    private fun setOnClickMovieTimeSpinnerListener() {
        movieTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val value = movieTimeSpinner.getItemAtPosition(position)
                    presenter.selectTime(value as LocalTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun setOnClickMovieDateSpinnerListener() {
        movieDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val movieDate = movieDateSpinner.getItemAtPosition(position)
                    presenter.selectDate(movieDate as LocalDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
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

    override fun initializePresenter() = MovieReservationPresenter(this, MovieContentsImpl, UserTicketsImpl)

    private fun movieContentId() = intent.getLongExtra(MovieReservationKey.ID, DEFAULT_VALUE)

    override fun showError(throwable: Throwable) {
        Log.e(TAG, throwable.message.toString())
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
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
            presenter.reserveSeat()
        }
    }

    override fun showMovieContent(movieContent: MovieContent) {
        movieContent.run {
            val image = imageId.getImageFromId(this@MovieReservationActivity)
            posterImage.setImageResource(image)
            titleText.text = title
            screeningDateText.text =
                resources.getString(R.string.screening_date)
                    .format(dateFormatter(openingMovieDate), dateFormatter(endingMoviesDate))
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }
    }

    override fun updateReservationCount(reservationCount: Int) {
        reservationCountText.text = reservationCount.toString()
    }

    override fun showMovieDateSelection(dateRange: List<LocalDate>) {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, dateRange).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            movieDateSpinner.adapter = this
        }
    }

    override fun showMovieTimeSelection(timeRange: List<LocalTime>) {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, timeRange).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            movieTimeSpinner.adapter = this
        }
    }

    override fun moveMovieSeatSelectionPage(userTicketId: Long) {
        Intent(this, MovieSeatSelectionActivity::class.java).run {
            putExtra(MovieReservationKey.TICKET_ID, userTicketId)
            startActivity(this)
        }
    }

    private fun dateFormatter(movieDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return movieDate.format(formatter)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_STATE_KEY = "reservationCount"
    }
}
