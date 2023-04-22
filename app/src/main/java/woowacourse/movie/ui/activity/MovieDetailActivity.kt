package woowacourse.movie.ui.activity

import android.content.Context
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
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.TimesGenerator
import woowacourse.movie.ui.getParcelable
import woowacourse.movie.ui.model.MovieModel
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PriceModel
import woowacourse.movie.ui.model.TicketTimeModel
import woowacourse.movie.ui.model.mapToMovie
import woowacourse.movie.ui.model.mapToPeopleCountModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private var peopleCount = PeopleCount()
    private val dateSpinner: Spinner by lazy { findViewById(R.id.detail_date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.detail_time_spinner) }
    private lateinit var timeSpinnerAdapter: ArrayAdapter<LocalTime>
    private val times = mutableListOf<LocalTime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getParcelable<MovieModel>(MOVIE_EXTRA_KEY)?.let {
            setMovieInfo(it)
            setDateSpinner(it)
            setBookingButton(it)
        }
        setTimeSpinner()
        setPeopleCountController()

        loadSavedData(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(DATE_SPINNER_POSITION_INSTANCE_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(TIME_SPINNER_POSITION_INSTANCE_KEY, timeSpinner.selectedItemPosition)
        outState.putInt(PEOPLE_COUNT_VALUE_INSTANCE_KEY, peopleCount.value)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMovieInfo(movie: MovieModel) {
        val posterView = findViewById<ImageView>(R.id.detail_poster)
        val titleView = findViewById<TextView>(R.id.detail_title)
        val dateView = findViewById<TextView>(R.id.detail_date)
        val runningTimeView = findViewById<TextView>(R.id.detail_running_time)
        val descriptionView = findViewById<TextView>(R.id.detail_description)

        posterView.setImageResource(movie.poster)
        titleView.text = movie.title
        dateView.text = movie.getScreenDate()
        runningTimeView.text = movie.getRunningTime()
        descriptionView.text = movie.description
    }

    private fun MovieModel.getScreenDate(): String = getString(R.string.screen_date, startDate.format(), endDate.format())

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

    private fun MovieModel.getRunningTime(): String = getString(R.string.running_time, runningTime)

    private fun setDateSpinner(movie: MovieModel) {
        val dateSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            movie.mapToMovie().getDatesBetweenTwoDates()
        )
        dateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateSpinnerAdapter
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                timeSpinner.setSelection(0)
                times.clear()
                times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
                timeSpinnerAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setTimeSpinner() {
        times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
        timeSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )
        timeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeSpinnerAdapter
    }

    private fun setPeopleCountController() {
        val peopleCountView = findViewById<TextView>(R.id.detail_people_count)
        setPeopleCountView(peopleCountView)
        setMinusButton(peopleCountView)
        setPlusButton(peopleCountView)
    }

    private fun setPeopleCountView(peopleCountView: TextView) {
        peopleCountView.text = "${peopleCount.value}"
    }

    private fun setMinusButton(peopleCountView: TextView) {
        val minusButton = findViewById<Button>(R.id.detail_minus_button)
        minusButton.setOnClickListener {
            peopleCount = peopleCount.minusCount()
            setPeopleCountView(peopleCountView)
        }
    }

    private fun setPlusButton(peopleCountView: TextView) {
        val plusButton = findViewById<Button>(R.id.detail_plus_button)
        plusButton.setOnClickListener {
            peopleCount = peopleCount.plusCount()
            setPeopleCountView(peopleCountView)
        }
    }

    private fun setBookingButton(movie: MovieModel) {
        val bookingButton = findViewById<Button>(R.id.detail_booking_button)

        bookingButton.setOnClickListener {
            moveToSeatPickerActivity(movie)
        }
    }

    private fun moveToSeatPickerActivity(movie: MovieModel) {
        val ticket = MovieTicketModel(
            movie.title,
            TicketTimeModel(
                LocalDateTime.of(
                    dateSpinner.selectedItem as LocalDate,
                    timeSpinner.selectedItem as LocalTime
                )
            ),
            peopleCount.mapToPeopleCountModel(),
            seats = emptySet(),
            PriceModel(0)
        )

        val intent = SeatPickerActivity.createIntent(this, ticket)
        startActivity(intent)
    }

    private fun loadSavedData(savedInstanceState: Bundle?) {
        val datePosition = savedInstanceState?.getInt(DATE_SPINNER_POSITION_INSTANCE_KEY) ?: 0
        val timePosition = savedInstanceState?.getInt(TIME_SPINNER_POSITION_INSTANCE_KEY) ?: 0
        val count = savedInstanceState?.getInt(PEOPLE_COUNT_VALUE_INSTANCE_KEY) ?: 1
        dateSpinner.setSelection(datePosition)
        timeSpinner.setSelection(timePosition)
        peopleCount = PeopleCount(count)
    }

    companion object {
        private const val DATE_SPINNER_POSITION_INSTANCE_KEY = "date_position"
        private const val MOVIE_EXTRA_KEY = "movie"
        private const val PEOPLE_COUNT_VALUE_INSTANCE_KEY = "count"
        private const val TIME_SPINNER_POSITION_INSTANCE_KEY = "time_position"

        fun createIntent(context: Context, movie: MovieModel): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA_KEY, movie)
            return intent
        }
    }
}
