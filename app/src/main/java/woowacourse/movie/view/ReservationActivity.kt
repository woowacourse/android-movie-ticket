package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private var peopleCount = 1
    private lateinit var selectedScreeningDate: LocalDate
    private lateinit var selectedScreeningTime: LocalTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MovieListAdapter.MOVIE, Movie::class.java)
        } else {
            intent.getSerializableExtra(MovieListAdapter.MOVIE) as? Movie
        }
        requireNotNull(movie) { "인텐트로 받아온 데이터가 널일 수 없습니다." }

        initViewData(movie)
        initSpinner(movie)
        initPeopleCountAdjustButtonClickListener()
        initReserveButtonClickListener(movie)
    }

    private fun initViewData(movie: Movie) {
        val posterView = findViewById<ImageView>(R.id.movie_poster)
        posterView.setImageResource(movie.poster.resourceId)
        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = movie.title
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text =
            getString(R.string.screening_date_format).format(movie.screeningStartDate.toString())
        val runningTimeView = findViewById<TextView>(R.id.movie_running_time)
        runningTimeView.text =
            getString(R.string.running_time_format).format(movie.runningTime.value)
        val summaryView = findViewById<TextView>(R.id.movie_summary)
        summaryView.text = movie.movieDetail.summary
    }

    private fun initSpinner(movie: Movie) {
        selectedScreeningDate = movie.screeningStartDate
        selectedScreeningTime = movie.getFirstScreeningTime(selectedScreeningDate)

        val screeningDates = movie.getAllScreeningDates()
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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val screeningTimes = movie.getAllScreeningTimes(selectedScreeningDate)
        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningTimes
        )
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedScreeningTime = screeningTimes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initPeopleCountAdjustButtonClickListener() {
        val peopleCountView = findViewById<TextView>(R.id.people_count)
        findViewById<Button>(R.id.minus_button).setOnClickListener {
            if (peopleCount > Reservation.MIN_PEOPLE_COUNT) {
                peopleCount--
                peopleCountView.text = peopleCount.toString()
            }
        }
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            if (peopleCount < Reservation.MAX_PEOPLE_COUNT) {
                peopleCount++
                peopleCountView.text = peopleCount.toString()
            }
        }
    }

    private fun initReserveButtonClickListener(movie: Movie) {
        findViewById<Button>(R.id.reservation_button).setOnClickListener {

            val reservation = Reservation(
                movie,
                peopleCount,
                LocalDateTime.of(selectedScreeningDate, selectedScreeningTime)
            )

            val intent = Intent(this, ReservationCompletedActivity::class.java)
            intent.putExtra(RESERVATION, reservation)
            startActivity(intent)
        }
    }

    companion object {
        const val RESERVATION = "RESERVATION"
    }
}
