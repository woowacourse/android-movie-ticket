package woowacourse.movie.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.R
import woowacourse.movie.TicketActivity
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.domain.movieinfo.RunningDate
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private var numberOfBooker = 1
    private var dateSpinnerPosition = 0
    private var timeSpinnerPosition = 0

    private val selectDateSpinner by lazy { findViewById<Spinner>(R.id.select_date) }
    private val selectTimeSpinner by lazy { findViewById<Spinner>(R.id.select_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setToolBar()

        if (savedInstanceState != null) {
            numberOfBooker = savedInstanceState.getInt(NUMBER_OF_PEOPLE)
            dateSpinnerPosition = savedInstanceState.getInt(DATE_SPINNER_POSITION)
            timeSpinnerPosition = savedInstanceState.getInt(TIME_SPINNER_POSITION)
        }

        val movie = intent.getSerializableExtra(MOVIE_KEY) as Movie

        setDateSpinner(movie.runningDate)
        setUpMovieData(movie)
        setNumberOfPeople()
        clickBookBtn(movie)
    }

    private fun setToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.movie_detail_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpMovieData(movie: Movie) {
        val moviePoster = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val screeningStartDate = findViewById<TextView>(R.id.screening_start_date)
        val screeningEndDate = findViewById<TextView>(R.id.screening_end_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val description = findViewById<TextView>(R.id.movie_description)

        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title

        screeningStartDate.text =
            movie.runningDate.startDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

        screeningEndDate.text =
            movie.runningDate.endDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

        runningTime.text = movie.runningTime.toString()
        description.text = movie.description
    }

    private fun setNumberOfPeople() {
        val booker = findViewById<TextView>(R.id.number_of_people)
        booker.text = numberOfBooker.toString()
        clickDecreaseBtn(booker)
        clickIncreaseBtn(booker)
    }

    private fun clickDecreaseBtn(booker: TextView) {
        val minusBtn = findViewById<Button>(R.id.minus_people)

        minusBtn.setOnClickListener {
            numberOfBooker -= 1
            if (numberOfBooker <= MIN_BOOKER_NUMBER) {
                numberOfBooker = MIN_BOOKER_NUMBER
            }
            booker.text = numberOfBooker.toString()
        }
    }

    private fun clickIncreaseBtn(booker: TextView) {
        val plusBtn = findViewById<Button>(R.id.plus_people)

        plusBtn.setOnClickListener {
            numberOfBooker += 1
            if (numberOfBooker >= MAX_BOOKER_NUMBER) {
                numberOfBooker = MAX_BOOKER_NUMBER
            }
            booker.text = numberOfBooker.toString()
        }
    }

    private fun clickBookBtn(movie: Movie) {
        val bookBtn = findViewById<Button>(R.id.book_button)
        bookBtn.setOnClickListener {
            val selectedDate = LocalDate.parse(selectDateSpinner.selectedItem.toString())
            val selectedTime = LocalTime.parse(selectTimeSpinner.selectedItem.toString())
            val ticket =
                Ticket(
                    TICKET_PRICE,
                    LocalDateTime.of(selectedDate, selectedTime),
                    numberOfBooker,
                )
            val intent = Intent(this, TicketActivity::class.java)
            intent.putExtra(TICKET_KEY, ticket)
            intent.putExtra(MOVIE_KEY, movie)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setDateSpinner(date: RunningDate) {
        val spinnerAdapter = SpinnerAdapter(this)
        selectDateSpinner.adapter = spinnerAdapter.getDateSpinnerAdapter(date)
        selectDateSpinner.setSelection(dateSpinnerPosition)

        selectDateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                setTimeSpinner(
                    spinnerAdapter,
                    LocalDate.parse(selectDateSpinner.getItemAtPosition(position) as String),
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setTimeSpinner(spinnerAdapter: SpinnerAdapter, selectedDay: LocalDate) {
        selectTimeSpinner.adapter = spinnerAdapter.getTimeSpinnerAdapter(selectedDay)
        selectTimeSpinner.setSelection(timeSpinnerPosition)

        selectTimeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                timeSpinnerPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NUMBER_OF_PEOPLE, numberOfBooker)
        outState.putInt(DATE_SPINNER_POSITION, dateSpinnerPosition)
        outState.putInt(TIME_SPINNER_POSITION, timeSpinnerPosition)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
        private const val NUMBER_OF_PEOPLE = "booker_number"
        private const val DATE_SPINNER_POSITION = "date_spinner_position"
        private const val TIME_SPINNER_POSITION = "time_spinner_position"
        private const val MIN_BOOKER_NUMBER = 1
        private const val MAX_BOOKER_NUMBER = 10
        private const val TICKET_PRICE = 13000
    }
}
