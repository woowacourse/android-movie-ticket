package woowacourse.movie.view.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.CountNumberOfPeople
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.view.BaseActivity
import woowacourse.movie.view.TicketActivity
import woowacourse.movie.view.viewmodel.MovieUIModel
import woowacourse.movie.view.viewmodel.toMovie
import woowacourse.movie.view.viewmodel.toUIModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : BaseActivity() {
    private var numberOfBooker = 1
    private var dateSpinnerPosition = 0
    private var timeSpinnerPosition = 0

    private val selectDateSpinner by lazy { findViewById<Spinner>(R.id.select_date) }
    private val selectTimeSpinner by lazy { findViewById<Spinner>(R.id.select_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setBackToBefore(R.id.movie_detail_toolbar)

        if (savedInstanceState != null) {
            numberOfBooker = savedInstanceState.getInt(NUMBER_OF_PEOPLE)
            dateSpinnerPosition = savedInstanceState.getInt(DATE_SPINNER_POSITION)
            timeSpinnerPosition = savedInstanceState.getInt(TIME_SPINNER_POSITION)
        }

        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        setDateSpinner(movieUI.startDate, movieUI.endDate)
        setUpMovieData(movieUI.toMovie())
        setNumberOfPeople()
        clickBookBtn(movieUI.toMovie())
    }

    private fun setUpMovieData(movie: Movie) {
        val moviePoster = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val description = findViewById<TextView>(R.id.movie_description)

        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title

        val startDate =
            movie.startDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

        val endDate =
            movie.endDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

        screeningDate.text = this.getString(R.string.screen_date, startDate, endDate)

        runningTime.text = this.getString(R.string.running_time, movie.runningTime)
        description.text = movie.description
    }

    private fun setNumberOfPeople() {
        val booker = findViewById<TextView>(R.id.number_of_people)
        booker.text = numberOfBooker.toString()
        val countNumberOfPeople = CountNumberOfPeople()
        clickDecreaseBtn(countNumberOfPeople, booker)
        clickIncreaseBtn(countNumberOfPeople, booker)
    }

    private fun clickDecreaseBtn(countNumberOfPeople: CountNumberOfPeople, booker: TextView) {
        val minusBtn = findViewById<Button>(R.id.minus_people)

        minusBtn.setOnClickListener {
            numberOfBooker = countNumberOfPeople.minusNumberOfPeople(numberOfBooker)
            booker.text = numberOfBooker.toString()
        }
    }

    private fun clickIncreaseBtn(countNumberOfPeople: CountNumberOfPeople, booker: TextView) {
        val plusBtn = findViewById<Button>(R.id.plus_people)

        plusBtn.setOnClickListener {
            numberOfBooker = countNumberOfPeople.plusNumberOfPeople(numberOfBooker)
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
            intent.putExtra(TICKET_KEY, ticket.toUIModel())
            intent.putExtra(MOVIE_KEY, movie.toUIModel())
            startActivity(intent)
        }
    }

    private fun setDateSpinner(startDate: LocalDate, endDate: LocalDate) {
        val spinnerAdapter = SpinnerAdapter(this)
        selectDateSpinner.adapter = spinnerAdapter.getDateSpinnerAdapter(startDate, endDate)
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

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
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

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
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
        private const val TICKET_PRICE = 13000
    }
}
