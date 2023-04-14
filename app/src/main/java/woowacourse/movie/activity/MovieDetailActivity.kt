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
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.PlayingTimes
import woowacourse.movie.model.Price
import woowacourse.movie.model.TicketingInfo
import woowacourse.movie.util.Formatter
import woowacourse.movie.util.Keys
import woowacourse.movie.util.customGetSerializable
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val savedCount = savedInstanceState?.getInt(Keys.COUNT_KEY) ?: DEFAULT_COUNT
        val savedDate = savedInstanceState?.getInt(Keys.SPINNER_DATE_KEY) ?: DEFAULT_POSITION
        val savedTime = savedInstanceState?.getInt(Keys.SPINNER_TIME_KEY) ?: DEFAULT_POSITION

        val movie: Movie = intent.customGetSerializable(Keys.MOVIE_KEY)

        initImageView(movie.image)
        initTitle(movie.title)
        initPlayingDate(movie.playingTimes)
        initRunningTime(movie.runningTime)
        initDescription(movie.description)
        initCount(savedCount)
        initMinusButton()
        initPlusButton()
        initButton(movie)
        initDateSpinner(savedDate, movie.playingTimes)
        initTimeSpinner(savedTime, movie.playingTimes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initButton(movie: Movie) {
        val ticketingButton = findViewById<Button>(R.id.btn_ticketing)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)

        ticketingButton.setOnClickListener {
            val intent = Intent(this, TicketResultActivity::class.java)
            val ticketingInfo = TicketingInfo.of(
                movie.title,
                spinnerDate.selectedItem as LocalDate,
                spinnerTime.selectedItem as LocalTime,
                getCount(),
                Price(),
                "현장"
            )
            intent.putExtra(Keys.INFO_KEY, ticketingInfo)
            startActivity(intent)
        }
    }

    private fun initTimeSpinner(savedTimePosition: Int, playingTimes: PlayingTimes) {
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val times = playingTimes.times[spinnerDate.selectedItem] ?: emptyList()
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerTime.setSelection(savedTimePosition)
    }

    private fun initDateSpinner(savedDatePosition: Int, playingTimes: PlayingTimes) {
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        val dates = playingTimes.times.keys.sorted()
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        spinnerDate.setSelection(savedDatePosition, false)
        spinnerDate.onItemSelectedListener = DateSpinnerListener(playingTimes, dates, spinnerTime)
    }

    private fun getCount(): Int = findViewById<TextView>(R.id.text_count).text.toString().toInt()

    private fun initMinusButton() {
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val countView = findViewById<TextView>(R.id.text_count)
        minusButton.setOnClickListener {
            val count = getCount()
            if (count > 1) countView.text = (count - 1).toString()
        }
    }

    private fun initPlusButton() {
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val countView = findViewById<TextView>(R.id.text_count)
        plusButton.setOnClickListener {
            val count = getCount()
            countView.text = (count + 1).toString()
        }
    }

    private fun initCount(count: Int) {
        val countText = findViewById<TextView>(R.id.text_count)
        countText.text = count.toString()
    }

    private fun initDescription(description: String) {
        val descriptionView = findViewById<TextView>(R.id.text_description)
        descriptionView.text = description
    }

    private fun initRunningTime(runningTime: Int) {
        val runningTimeView = findViewById<TextView>(R.id.text_running_time)
        runningTimeView.text = getText(R.string.running_time).toString().format(runningTime.toString())
    }

    private fun initPlayingDate(times: PlayingTimes) {
        val playingDateView = findViewById<TextView>(R.id.text_playing_date)
        playingDateView.text = getString(R.string.playing_time).format(
            Formatter.dateFormat(times.startDate),
            Formatter.dateFormat(times.endDate)
        )
    }

    private fun initTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.text_title)
        titleView.text = title
    }

    private fun initImageView(imageResource: Int) {
        val imageView = findViewById<ImageView>(R.id.img_movie)
        imageView.setImageResource(imageResource)
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
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        outState.putInt(Keys.COUNT_KEY, countText.text.toString().toInt())
        outState.putInt(Keys.SPINNER_DATE_KEY, spinnerDate.selectedItemPosition)
        outState.putInt(Keys.SPINNER_TIME_KEY, spinnerTime.selectedItemPosition)
    }

    companion object {
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
    }
}
