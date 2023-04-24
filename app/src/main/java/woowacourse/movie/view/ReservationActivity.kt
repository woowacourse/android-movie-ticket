package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.service.MovieService
import woowacourse.movie.view.MovieListAdapter.Companion.MOVIE_ID
import woowacourse.movie.view.SeatSelectionActivity.Companion.RESERVATION_INFO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private lateinit var selectedScreeningDate: LocalDate
    private lateinit var selectedScreeningTime: LocalTime
    private val movie: MovieDto by lazy { initMovieFromIntent() }
    private var timeSpinnerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initViewData()
        initSpinner()
        initSeatSelectionButtonClickListener()
    }

    private fun initMovieFromIntent(): MovieDto {
        val movieId = intent.getLongExtra(MOVIE_ID, 0)
        return MovieService.findMovieById(movieId)
    }

    private fun initViewData() {
        val posterView = findViewById<ImageView>(R.id.movie_poster)
        posterView.setImageResource(PosterRepository.getPosterResourceId(movie.id))
        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = movie.title
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text =
            if (movie.screenings.isEmpty()) getString(R.string.screening_date_is_empty) else
                getString(R.string.screening_date_format).format(
                    movie.screenings.first().screeningDateTime.format(DATE_FORMATTER),
                    movie.screenings.last().screeningDateTime.format(DATE_FORMATTER)
                )
        val runningTimeView = findViewById<TextView>(R.id.movie_running_time)
        runningTimeView.text =
            getString(R.string.running_time_format).format(movie.runningTime)
        val summaryView = findViewById<TextView>(R.id.movie_summary)
        summaryView.text = movie.summary
    }

    private fun initSpinner() {
        selectedScreeningDate = movie.screenings.first().screeningDateTime.toLocalDate()
        selectedScreeningTime = movie.screenings.first().screeningDateTime.toLocalTime()

        val screeningDates = movie.screenings.map { it.screeningDateTime.toLocalDate() }.distinct()
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningDates
        )

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedScreeningDate = screeningDates[position]
                initTimeSpinner(timeSpinnerPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun initTimeSpinner(selectedPosition: Int?) {
        val screeningTimes =
            movie.screenings.filter { it.screeningDateTime.toLocalDate() == selectedScreeningDate }
                .map { it.screeningDateTime.toLocalTime() }
        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningTimes
        )
        if (selectedPosition != null) {
            timeSpinner.setSelection(selectedPosition, false)
        }
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedScreeningTime = screeningTimes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun initSeatSelectionButtonClickListener() {
        findViewById<Button>(R.id.seat_selection_button).setOnClickListener {
            startSeatSelectionActivity()
        }
    }

    private fun startSeatSelectionActivity() {
        val intent = Intent(this, SeatSelectionActivity::class.java)
        val selectedScreeningDateTime =
            LocalDateTime.of(selectedScreeningDate, selectedScreeningTime)
        intent.putExtra(RESERVATION_INFO, ReservationInfo(movie.id, selectedScreeningDateTime))
        startActivity(intent)
    }

//    private fun initReserveButtonClickListener() {
//        findViewById<Button>(R.id.reservation_button).setOnClickListener {
//            reserveBySelectedReservationOptions()
//            startReservationResultActivity()
//        }
//    }

//    private fun reserveBySelectedReservationOptions() {
//        val selectedScreeningDateTime =
//            LocalDateTime.of(selectedScreeningDate, selectedScreeningTime)
//        MovieService.reserve(movie.id, selectedScreeningDateTime, selectedAudienceCount)
//    }
//
//    private fun startReservationResultActivity() {
//        val intent = Intent(this, ReservationResultActivity::class.java)
//        val selectedScreeningDateTime =
//            LocalDateTime.of(selectedScreeningDate, selectedScreeningTime)
//        intent.putExtra(RESERVATION_INFO, ReservationInfo(movie.id, selectedScreeningDateTime))
//        startActivity(intent)
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)

        outState.apply {
            putSerializable(SELECTED_DATE, selectedScreeningDate)
            putSerializable(SELECTED_TIME, selectedScreeningTime)
            putInt(SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        timeSpinnerPosition = savedInstanceState.getInt(SELECTED_TIME_POSITION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(SELECTED_DATE, LocalDate::class.java)?.run {
                selectedScreeningDate = this
            }
            savedInstanceState.getSerializable(SELECTED_TIME, LocalTime::class.java)?.run {
                selectedScreeningTime = this
            }
        } else {
            (savedInstanceState.getSerializable(SELECTED_DATE) as LocalDate?)?.run {
                selectedScreeningDate = this
            }
            (savedInstanceState.getSerializable(SELECTED_TIME) as LocalTime?)?.run {
                selectedScreeningTime = this
            }
        }
    }

    companion object {
        private const val SELECTED_DATE = "SELECTED_DATE"
        private const val SELECTED_TIME = "SELECTED_TIME"
        private const val SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
    }
}
