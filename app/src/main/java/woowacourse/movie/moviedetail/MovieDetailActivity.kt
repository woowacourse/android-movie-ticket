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
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.domain.movieinfo.MovieDate
import woowacourse.movie.domain.movieinfo.MovieTime
import woowacourse.movie.domain.movieinfo.RunningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private var movieTikcet = Ticket()
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
        val movieDate = findViewById<TextView>(R.id.movie_date)
        val runningTime = findViewById<TextView>(R.id.movie_time)
        val description = findViewById<TextView>(R.id.movie_description)

        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title

        movieDate.text = formatMovieRunningDate(movie)

        runningTime.text = getString(R.string.movie_running_time).format(movie.runningTime)
        description.text = movie.description
    }

    private fun formatMovieRunningDate(item: Movie): String {
        val startDate =
            item.runningDate.startDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))
        val endDate =
            item.runningDate.endDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))
        return getString(R.string.movie_running_date).format(startDate, endDate)
    }

    private fun setNumberOfPeople() {
        val booker = findViewById<TextView>(R.id.booker_num)
        booker.text = movieTikcet.numberOfPeople.toString()
        clickDecreaseBtn(booker)
        clickIncreaseBtn(booker)
    }

    private fun clickDecreaseBtn(booker: TextView) {
        val minusBtn = findViewById<Button>(R.id.minus_button)

        minusBtn.setOnClickListener {
            movieTikcet.decrease()
            booker.text = movieTikcet.numberOfPeople.toString()
        }
    }

    private fun clickIncreaseBtn(booker: TextView) {
        val plusBtn = findViewById<Button>(R.id.plus_button)

        plusBtn.setOnClickListener {
            movieTikcet.increase()
            booker.text = movieTikcet.numberOfPeople.toString()
        }
    }

    private fun clickBookBtn(movie: Movie) {
        val bookBtn = findViewById<Button>(R.id.book_button)
        bookBtn.setOnClickListener {
            val selectedDate = MovieDate.of(selectDateSpinner.selectedItem.toString())
            val selectedTime = MovieTime.of(selectTimeSpinner.selectedItem.toString())
            val intent = Intent(this, TicketActivity::class.java)
            intent.putExtra(TICKET_KEY, movieTikcet)
            intent.putExtra(MOVIE_KEY, movie)
            intent.putExtra(DATE_KEY, selectedDate)
            intent.putExtra(TIME_KEY, selectedTime)
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
        outState.putInt(DATE_SPINNER_POSITION, dateSpinnerPosition)
        outState.putInt(TIME_SPINNER_POSITION, timeSpinnerPosition)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "movie_date"
        private const val TIME_KEY = "movie_time"
        private const val NUMBER_OF_PEOPLE = "booker_number"
        private const val DATE_SPINNER_POSITION = "date_spinner_position"
        private const val TIME_SPINNER_POSITION = "time_spinner_position"
    }
}
