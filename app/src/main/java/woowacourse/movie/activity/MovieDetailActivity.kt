package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.domain.model.model.Movie
import com.example.domain.model.model.Payment
import com.example.domain.model.model.PlayingTimes
import com.example.domain.model.model.ReservationInfo
import woowacourse.movie.R
import woowacourse.movie.listener.DateSpinnerListener
import woowacourse.movie.mapper.toMovie
import woowacourse.movie.mapper.toReservationInfoModel
import woowacourse.movie.model.MovieListItem
import woowacourse.movie.model.ReservationInfoModel
import woowacourse.movie.util.customGetSerializable
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val savedCount = getSavedCount(savedInstanceState)
        val savedDateSpinnerIndex = getSavedDateSpinnerIndex(savedInstanceState)
        val savedTimeSpinnerIndex = getSavedTimeSpinnerIndex(savedInstanceState)

        val movieModel: MovieListItem.MovieModel = getReceivedIntentData()
        initMovieDataView(movieModel)
        val movie: Movie = movieModel.toMovie()
        initSpinner(
            savedDateSpinnerIndex,
            savedTimeSpinnerIndex,
            movie.startDate,
            movie.endDate
        )
        initCountView(savedCount)
        initTicketingButton(movieModel.title)
        setActionBar()
    }

    private fun getReceivedIntentData(): MovieListItem.MovieModel =
        intent.customGetSerializable(MOVIE_KEY)

    private fun initMovieDataView(movie: MovieListItem.MovieModel) {
        InitView.initImageView(findViewById(R.id.img_movie), movie.image)
        InitView.initTextView(findViewById(R.id.text_title), movie.title)
        InitView.initTextView(
            findViewById(R.id.text_playing_date),
            getString(
                R.string.playing_time, movie.startDate,
                movie.endDate
            )
        )
        InitView.initTextView(
            findViewById(R.id.text_running_time),
            getString(R.string.running_time, movie.runningTime)
        )
        InitView.initTextView(findViewById(R.id.text_description), movie.description)
    }

    private fun initCountView(savedCount: Int) {
        InitView.initTextView(findViewById(R.id.text_count), savedCount.toString())
        initMinusButton()
        initPlusButton()
    }

    private fun initSpinner(
        savedDateSpinnerIndex: Int,
        savedTimeSpinnerIndex: Int,
        startDate: LocalDate,
        endDate: LocalDate
    ) {
        initDateSpinner(savedDateSpinnerIndex, startDate, endDate)
        initTimeSpinner(savedTimeSpinnerIndex, startDate, endDate)
    }

    private fun initTicketingButton(movieTitle: String) {
        InitView.initButton(findViewById(R.id.btn_ticketing)) {
            startActivity(
                getIntentToSend(
                    movieTitle
                )
            )
        }
    }

    private fun getIntentToSend(movieTitle: String): Intent {
        val intent = Intent(this, ReserveSeatActivity::class.java)
        val reservationInfoModel = getReservationInfoModel(movieTitle)
        intent.putExtra(RESERVATION_INFO_KEY, reservationInfoModel)
        return intent
    }

    private fun initMinusButton() {
        InitView.initButton(findViewById(R.id.btn_minus)) {
            val countView = findViewById<TextView>(R.id.text_count)
            val count = getCount()
            if (count > 1) countView.text = (count.minus(1)).toString()
        }
    }

    private fun initPlusButton() {
        InitView.initButton(findViewById(R.id.btn_plus)) {
            val countView = findViewById<TextView>(R.id.text_count)
            val count = getCount()
            countView.text = (count.plus(1)).toString()
        }
    }

    private fun getReservationInfoModel(movieTitle: String): ReservationInfoModel {
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val playingDate = spinnerDate.selectedItem as LocalDate
        val playingTime = spinnerTime.selectedItem as LocalTime
        return ReservationInfo(
            movieTitle,
            playingDate,
            playingTime,
            getCount(),
            Payment.ON_SITE
        ).toReservationInfoModel()
    }

    private fun getSavedCount(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(COUNT_KEY) ?: DEFAULT_COUNT

    private fun getSavedDateSpinnerIndex(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(SPINNER_DATE_KEY) ?: DEFAULT_POSITION

    private fun getSavedTimeSpinnerIndex(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(SPINNER_TIME_KEY) ?: DEFAULT_POSITION

    private fun initTimeSpinner(
        savedTimeSpinnerIndex: Int,
        startDate: LocalDate,
        endDate: LocalDate
    ) {
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val playingTimes = PlayingTimes(startDate, endDate)
        val selectedDate = playingTimes.playingDates[spinnerDate.selectedItemPosition]
        val times = playingTimes.getTimes(selectedDate)
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerTime.setSelection(savedTimeSpinnerIndex)
    }

    private fun initDateSpinner(
        savedDateSpinnerIndex: Int,
        startDate: LocalDate,
        endDate: LocalDate
    ) {
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val playingTimes = PlayingTimes(startDate, endDate)
        val dates = playingTimes.playingDates
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerDate.setSelection(savedDateSpinnerIndex, false)
        spinnerDate.onItemSelectedListener = DateSpinnerListener(playingTimes, dates, spinnerTime)
    }

    private fun getCount(): Int = findViewById<TextView>(R.id.text_count).text.toString().toInt()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val countText = findViewById<TextView>(R.id.text_count)
        outState.putInt(COUNT_KEY, countText.text.toString().toInt())
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        outState.putInt(SPINNER_DATE_KEY, spinnerDate.selectedItemPosition)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        outState.putInt(SPINNER_TIME_KEY, spinnerTime.selectedItemPosition)
    }

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val COUNT_KEY = "COUNT"
        private const val SPINNER_DATE_KEY = "SPINNER_DATE"
        private const val SPINNER_TIME_KEY = "SPINNER_TIME"
        private const val RESERVATION_INFO_KEY = "reservationInfo"
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
    }
}
