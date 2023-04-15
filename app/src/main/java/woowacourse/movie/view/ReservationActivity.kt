package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationBinding

    private var peopleCountSaved = 1
    private lateinit var selectedScreeningDate: LocalDate
    private lateinit var selectedScreeningTime: LocalTime
    private val movie: Movie by lazy { initMovieFromIntent() }
    private var timeSpinnerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        initViewData()
        initSpinner()
        initPeopleCountAdjustButtonClickListener()
        initReserveButtonClickListener()
    }

    override fun onResume() {
        super.onResume()
        binding.peopleCount.text = peopleCountSaved.toString()
    }

    private fun initMovieFromIntent(): Movie {
        val movie = intent.getParcelableMovie()
        requireNotNull(movie) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        return movie
    }

    private fun Intent.getParcelableMovie(): Movie? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return getParcelableExtra(MovieListActivity.MOVIE_ITEM, Movie::class.java)
        }
        return getParcelableExtra(MovieListActivity.MOVIE_ITEM) as? Movie
    }

    private fun initViewData() {
        binding.apply {
            moviePoster.setImageResource(movie.posterResourceId)
            movieTitle.text = movie.title
            movieScreeningDate.text = getString(R.string.screening_date_format).format(
                movie.screeningStartDate.format(DATE_FORMATTER),
                movie.screeningEndDate.format(DATE_FORMATTER)
            )
            movieRunningTime.text =
                getString(R.string.running_time_format).format(movie.runningTime.value)
            movieSummary.text = movie.summary
        }
    }

    private fun initSpinner() {
        selectedScreeningDate = movie.screeningStartDate
        selectedScreeningTime = movie.getFirstScreeningTime(selectedScreeningDate)

        val screeningDates = movie.getAllScreeningDates()

        val dateSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningDates
        )

        binding.dateSpinner.apply {
            adapter = dateSpinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    }

    private fun initTimeSpinner(selectedPosition: Int?) {
        val screeningTimes = movie.getAllScreeningTimes(selectedScreeningDate)

        val timeSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            screeningTimes
        )
        binding.timeSpinner.apply {
            adapter = timeSpinnerAdapter

            if (selectedPosition != null) {
                this.setSelection(selectedPosition, false)
            }
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    }

    private fun initPeopleCountAdjustButtonClickListener() {
        binding.apply {
            minusButton.setOnClickListener {
                decreasePeopleCount()
            }
            plusButton.setOnClickListener {
                increasePeopleCount()
            }
        }
    }

    private fun ActivityReservationBinding.decreasePeopleCount() {
        if (peopleCountSaved > Reservation.MIN_PEOPLE_COUNT) {
            peopleCountSaved--
            peopleCount.text = peopleCountSaved.toString()
        }
    }

    private fun ActivityReservationBinding.increasePeopleCount() {
        if (peopleCountSaved < Reservation.MAX_PEOPLE_COUNT) {
            peopleCountSaved++
            peopleCount.text = peopleCountSaved.toString()
        }
    }


    private fun initReserveButtonClickListener() {
        binding.reservationButton.setOnClickListener {

            val reservation = Reservation(
                movie,
                peopleCountSaved,
                LocalDateTime.of(selectedScreeningDate, selectedScreeningTime)
            )

            val intent = Intent(this, ReservationCompletedActivity::class.java)
            intent.putExtra(RESERVATION, reservation.toUiModel())
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putInt(PEOPLE_COUNT, peopleCountSaved)
            putSerializable(SELECTED_DATE, selectedScreeningDate)
            putSerializable(SELECTED_TIME, selectedScreeningTime)
            putInt(SELECTED_TIME_POSITION, binding.timeSpinner.selectedItemPosition)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        peopleCountSaved = savedInstanceState.getInt(PEOPLE_COUNT)
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
        const val RESERVATION = "RESERVATION"
        private const val PEOPLE_COUNT = "PEOPLE_COUNT"
        private const val SELECTED_DATE = "SELECTED_DATE"
        private const val SELECTED_TIME = "SELECTED_TIME"
        private const val SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
    }
}
