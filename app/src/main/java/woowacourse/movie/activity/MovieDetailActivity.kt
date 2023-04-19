package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.price.Price
import woowacourse.movie.R
import woowacourse.movie.mapper.toPlayingTimes
import woowacourse.movie.model.MovieModel
import woowacourse.movie.model.PlayingTimesModel
import woowacourse.movie.util.customGetSerializable
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val savedCount = getSavedCount(savedInstanceState)
        val savedDateSpinnerIndex = getSavedDateSpinnerIndex(savedInstanceState)
        val savedTimeSpinnerIndex = getSavedTimeSpinnerIndex(savedInstanceState)

        val movie: MovieModel = getIntentMovieDate()

        initMovieDataView(movie)
        initCountView(savedCount)
        initSpinner(savedDateSpinnerIndex, savedTimeSpinnerIndex, movie.playingTimes)
        initTicketingButton(movie.title)
        setActionBar()
    }

    private fun getIntentMovieDate(): MovieModel = intent.customGetSerializable(MOVIE_KEY)

    private fun initMovieDataView(movie: MovieModel) {
        initImageView(movie.image)
        initTitle(movie.title)
        initPlayingDate(movie.playingTimes)
        initRunningTime(movie.runningTime)
        initDescription(movie.description)
    }

    private fun initCountView(savedCount: Int) {
        initCount(savedCount)
        initMinusButton()
        initPlusButton()
    }

    private fun initSpinner(
        savedDateSpinnerIndex: Int,
        savedTimeSpinnerIndex: Int,
        playingTimes: PlayingTimesModel
    ) {
        initDateSpinner(savedDateSpinnerIndex, playingTimes)
        initTimeSpinner(savedTimeSpinnerIndex, playingTimes)
    }

    private fun initTicketingButton(movieTitle: String) {
        val ticketingButton = findViewById<Button>(R.id.btn_ticketing)
        ticketingButton.setOnClickListener {
            val intent = Intent(this, TicketResultActivity::class.java)
            val ticketingInfo = getTicketingInfo(movieTitle)
            intent.putExtra(INFO_KEY, ticketingInfo)
            startActivity(intent)
        }
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initImageView(imageResource: Int) {
        val imageView = findViewById<ImageView>(R.id.img_movie)
        imageView.setImageResource(imageResource)
    }

    private fun initTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.text_title)
        titleView.text = title
    }

    private fun initPlayingDate(times: PlayingTimesModel) {
        val playingDateView = findViewById<TextView>(R.id.text_playing_date)
        playingDateView.text = getString(
            R.string.playing_time, times.startDate,
            times.endDate
        )
    }

    private fun initRunningTime(runningTime: Int) {
        val runningTimeView = findViewById<TextView>(R.id.text_running_time)
        runningTimeView.text =
            getString(R.string.running_time, runningTime)
    }

    private fun initDescription(description: String) {
        val descriptionView = findViewById<TextView>(R.id.text_description)
        descriptionView.text = description
    }

    private fun initCount(savedCount: Int) {
        val countText = findViewById<TextView>(R.id.text_count)
        countText.text = savedCount.toString()
    }

    private fun initMinusButton() {
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val countView = findViewById<TextView>(R.id.text_count)
        minusButton.setOnClickListener {
            val count = getCount()
            if (count > 1) countView.text = (count.minus(1)).toString()
        }
    }

    private fun initPlusButton() {
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val countView = findViewById<TextView>(R.id.text_count)
        plusButton.setOnClickListener {
            val count = getCount()
            countView.text = (count.plus(1)).toString()
        }
    }

    private fun getTicketingInfo(movieTitle: String): com.example.domain.model.TicketingInfo {
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        return com.example.domain.model.TicketingInfo.of(
            movieTitle,
            spinnerDate.selectedItem as LocalDate,
            spinnerTime.selectedItem as LocalTime,
            getCount(),
            Price(),
            com.example.domain.model.Payment.ON_SITE
        )
    }

    private fun getSavedCount(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(COUNT_KEY) ?: DEFAULT_COUNT

    private fun getSavedDateSpinnerIndex(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(SPINNER_DATE_KEY) ?: DEFAULT_POSITION

    private fun getSavedTimeSpinnerIndex(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt(SPINNER_TIME_KEY) ?: DEFAULT_POSITION

    private fun initTimeSpinner(savedTimeSpinnerIndex: Int, playingTimes: PlayingTimesModel) {
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val selectedDate = playingTimes.toPlayingTimes().playingDates[spinnerDate.selectedItemPosition]
        val times = playingTimes.toPlayingTimes().getTimes(selectedDate)
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerTime.setSelection(savedTimeSpinnerIndex)
    }

    private fun initDateSpinner(savedDateSpinnerIndex: Int, playingTimes: PlayingTimesModel) {
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val dates = playingTimes.toPlayingTimes().playingDates
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerDate.setSelection(savedDateSpinnerIndex, false)
        spinnerDate.onItemSelectedListener = SpinnerListener(playingTimes, dates, spinnerTime)
    }

    private fun getCount(): Int = findViewById<TextView>(R.id.text_count).text.toString().toInt()

    class SpinnerListener(
        private val playingTimes: PlayingTimesModel,
        private val dates: List<LocalDate>,
        private val spinnerTime: Spinner
    ) : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            adapterView: AdapterView<*>?,
            view: View?,
            index: Int,
            p3: Long
        ) {
            val times = playingTimes.toPlayingTimes().getTimes(dates[index])
            spinnerTime.adapter =
                ArrayAdapter(spinnerTime.context, android.R.layout.simple_spinner_item, times)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) = Unit
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

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
        private const val INFO_KEY = "ticketingInfo"
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
    }
}
