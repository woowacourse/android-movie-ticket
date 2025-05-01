package woowacourse.movie.view.reservation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movietime.MovieSchedule
import woowacourse.movie.domain.movietime.ScreeningTime
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.view.dialog.DialogFactory
import woowacourse.movie.view.reservation.seat.ReservationSeatActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val present: ReservationContract.Presenter by lazy {
        ReservationPresent(this)
    }

    private lateinit var spinnerDate: Spinner
    private lateinit var spinnerTime: Spinner
    private lateinit var counterTextView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout_reservation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerDate = findViewById(R.id.spinner_date)
        spinnerTime = findViewById(R.id.spinner_time)
        counterTextView = findViewById(R.id.tv_personnel)
        plusButton = findViewById(R.id.btn_plus_button)
        minusButton = findViewById(R.id.btn_minus_button)

        val movie: Movie? = intent.getSerializableExtraCompat<Movie>(KEY_MOVIE)

        fetchMovieOrShowError(movie)
    }

    private fun fetchMovieOrShowError(movie: Movie?) {
        if (movie == null) {
            showErrorInvalidMovie()
        } else {
            present.fetchData(movie)
        }
    }

    override fun showErrorInvalidMovie() {
        DialogFactory().showError(this) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        present.onSaveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        present.onRestoreState(savedInstanceState)
    }

    override fun showSpinnerData(
        movie: Movie,
        selectedDatePosition: Int,
    ) {
        setDateSpinner(movie, LocalDate.now(), spinnerTime)

        spinnerDate.setSelection(selectedDatePosition)
    }

    override fun showMovieReservationScreen(movie: Movie) {
        val movieTitleTextView = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.tv_movie_date)
        val movieTimeTextView = findViewById<TextView>(R.id.tv_movie_time)
        val moviePosterImageView = findViewById<ImageView>(R.id.iv_movie_image)

        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
        val start = movie.date.startDate.format(formatter)
        val end = movie.date.endDate.format(formatter)

        movieTitleTextView.text = movie.title
        movieDateTextView.text = getString(R.string.movieDate, start, end)
        movieTimeTextView.text = getString(R.string.movieTime, movie.time.toString())
        moviePosterImageView.setImageResource(movie.image)
    }

    override fun showCount(count: Int) {
        counterTextView.text = count.toString()
    }

    override fun setCountButtons() {
        plusButton.setOnClickListener {
            present.increasedCount()
        }

        minusButton.setOnClickListener {
            present.decreasedCount()
        }
    }

    override fun setReservationButton() {
        val reservationButton = findViewById<Button>(R.id.btn_reservation)

        reservationButton.setOnClickListener {
            val selectedDate: LocalDate = spinnerDate.selectedItem as LocalDate
            val selectedTime: LocalTime? = spinnerTime.selectedItem as? LocalTime?
            if (selectedTime == null) {
                Toast.makeText(this, getString(R.string.message_not_allowed_time), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            present.createTicket(LocalDateTime.of(selectedDate, selectedTime))
        }
    }

    override fun navigateToReservationComplete(ticket: Ticket) {
        val intent = ReservationSeatActivity.newIntent(this@ReservationActivity, ticket)
        startActivity(intent)
    }

    private fun setDateSpinner(
        movie: Movie,
        localDate: LocalDate,
        spinnerTime: Spinner,
    ) {
        val movieSchedule = MovieSchedule(movie.date)
        val currentDateSpinner = movieSchedule.selectableDates(localDate)

        spinnerDate.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currentDateSpinner,
            )

        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = currentDateSpinner[position]
                    present.resetSelectedTimePosition(position)
                    setTimeSpinner(spinnerTime, selectedDate)
                    present.selectedDate(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setTimeSpinner(
        spinner: Spinner,
        localDate: LocalDate,
    ) {
        val currentTimeTable = ScreeningTime(localDate, LocalDateTime.now()).selectableTimes()
        spinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currentTimeTable,
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    present.selectedTime(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun setTimeSelection(position: Int) {
        spinnerTime.setSelection(position)
    }

    companion object {
        private const val KEY_MOVIE = "movie"
        private const val DATE_PATTERN = "yyyy.M.d"

        fun newIntent(
            context: Context,
            movie: Movie?,
        ): Intent =
            Intent(context, ReservationActivity::class.java).putExtra(
                KEY_MOVIE,
                movie,
            )
    }
}
