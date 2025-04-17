package woowacourse.movie.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.ReservationDialog
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSchedule
import woowacourse.movie.domain.ScreeningTime
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {
    private var count = 1
    private var selectedDatePosition = 0
    private var selectedTimePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movie = intent.getSerializableExtra("movie") as? Movie ?: Movie()

        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)

        setMovieInfo(movie)
        setCountButtons()
        setReservationButton(movie, spinnerDate, spinnerTime)

        setDateSpinner(movie, LocalDate.now(), spinnerDate, spinnerTime)

        count = savedInstanceState?.getInt(KEY_PERSONNEL_COUNT) ?: return
        selectedDatePosition = savedInstanceState.getInt(KEY_DATE_POSITION)
        selectedTimePosition = savedInstanceState.getInt(KEY_TIME_POSITION)

        spinnerDate.setSelection(selectedDatePosition)
        updateCounterText()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_PERSONNEL_COUNT, count)
        outState.putInt(KEY_DATE_POSITION, selectedDatePosition)
        outState.putInt(KEY_TIME_POSITION, selectedTimePosition)
    }

    private fun updateCounterText() {
        val counterTextView = findViewById<TextView>(R.id.personnel)
        counterTextView.text = count.toString()
    }

    private fun setMovieInfo(movie: Movie) {
        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val movieTimeTextView = findViewById<TextView>(R.id.movie_time)
        val moviePosterImageView = findViewById<ImageView>(R.id.movie_image)

        val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
        val start = movie.date.startDate.format(formatter)
        val end = movie.date.endDate.format(formatter)

        movieTitleTextView.text = movie.title
        movieDateTextView.text = getString(R.string.movieDate, start, end)
        movieTimeTextView.text = getString(R.string.movieTime, movie.time.toString())
        moviePosterImageView.setImageResource(movie.image)
    }

    private fun setCountButtons() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            if (count > DEFAULT_PERSONNEL) count--
            updateCounterText()
        }

        plusButton.setOnClickListener {
            count++
            updateCounterText()
        }

        updateCounterText()
    }

    private fun setReservationButton(
        movie: Movie,
        spinnerDate: Spinner,
        spinnerTime: Spinner,
    ) {
        val reservationButton = findViewById<Button>(R.id.reservation_button)

        reservationButton.setOnClickListener {
            val ticket =
                createTicket(
                    movie.title,
                    spinnerDate.selectedItem as LocalDate,
                    spinnerTime.selectedItem as LocalTime,
                )
            ReservationDialog(this, ticket).popUp()
        }
    }

    private fun setDateSpinner(
        movie: Movie,
        localDate: LocalDate,
        spinner: Spinner,
        spinnerTime: Spinner,
    ) {
        val movieSchedule = MovieSchedule(movie.date)
        val currentDateSpinner = movieSchedule.selectableDates(localDate)

        spinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currentDateSpinner,
            )
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedDatePosition = position
                    val selectedDate = currentDateSpinner[selectedDatePosition]
                    setTimeSpinner(spinnerTime, selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setTimeSpinner(
        spinner: Spinner,
        localDate: LocalDate,
    ) {
        val currentTimeTable = ScreeningTime(localDate.atStartOfDay()).selectableTimes()
        spinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currentTimeTable,
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        spinner.setSelection(selectedTimePosition)

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedTimePosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun createTicket(
        movieTitle: String,
        localDate: LocalDate,
        localTime: LocalTime,
    ): Ticket {
        return Ticket(
            movieTitle,
            LocalDateTime.of(localDate.year, localDate.month, localDate.dayOfMonth, localTime.hour, localTime.minute),
            count,
        )
    }

    companion object {
        private const val KEY_PERSONNEL_COUNT = "personnel_count"
        private const val KEY_DATE_POSITION = "movieDate_position"
        private const val KEY_TIME_POSITION = "timeTable_position"
        private const val DEFAULT_PERSONNEL = 1
    }
}
