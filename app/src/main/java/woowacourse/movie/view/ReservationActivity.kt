package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ScreeningTime
import woowacourse.movie.util.DATE_FORMATTER
import woowacourse.movie.util.getParcelableCompat
import woowacourse.movie.util.getSerializableCompat
import woowacourse.movie.view.model.MovieListModel.MovieUiModel
import woowacourse.movie.view.model.ReservationOptions
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationBinding

    private var peopleCountSaved = 1
    private lateinit var selectedScreeningDate: LocalDate
    private lateinit var selectedScreeningTime: LocalTime
    private val movie: MovieUiModel by lazy { initMovieFromIntent() }
    private var timeSpinnerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewData()
        initSpinner()
        initPeopleCountAdjustButtonClickListener()
        initReserveButtonClickListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        binding.peopleCount.text = peopleCountSaved.toString()
    }

    private fun initMovieFromIntent(): MovieUiModel {
        val movie = intent.getParcelableCompat<MovieUiModel>(MOVIE)
        requireNotNull(movie) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        return movie
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
                getString(R.string.running_time_format).format(movie.runningTime)
            movieSummary.text = movie.summary
        }
    }

    private fun initSpinner() {
        selectedScreeningDate = movie.screeningStartDate
        selectedScreeningTime = ScreeningTime(selectedScreeningDate).getFirstScreeningTime()

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
        val screeningTimes = ScreeningTime(selectedScreeningDate).getAllScreeningTimes()

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
            val reservationOptions = ReservationOptions(
                movie.title,
                LocalDateTime.of(selectedScreeningDate, selectedScreeningTime),
                peopleCountSaved
            )
            startActivity(SeatSelectionActivity.newIntent(this, reservationOptions, movie))
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

        savedInstanceState.getSerializableCompat<LocalDate>(SELECTED_DATE)?.run {
            selectedScreeningDate = this
        }
        savedInstanceState.getSerializableCompat<LocalTime>(SELECTED_TIME)?.run {
            selectedScreeningTime = this
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PEOPLE_COUNT = "PEOPLE_COUNT"
        private const val SELECTED_DATE = "SELECTED_DATE"
        private const val SELECTED_TIME = "SELECTED_TIME"
        private const val SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val MOVIE = "MOVIE"
        fun newIntent(context: Context, movie: MovieUiModel): Intent {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }
}
