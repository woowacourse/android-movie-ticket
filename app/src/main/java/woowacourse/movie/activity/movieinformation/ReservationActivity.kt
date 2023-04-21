package woowacourse.movie.activity.movieinformation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.seats.SeatSelectionActivity
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_INSTANCE_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_TIME_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_TIME_INSTANCE_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INSTANCE_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INTENT_KEY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel? = intent.getSerializableExtra(MOVIE_INTENT_KEY) as? MovieModel
        if (movieModel == null) {
            showToast(getString(R.string.invalid_access_message))
            finish()
        }

        MovieInformationView(binding, movieModel!!).set()
        TicketCountSelectorView(binding).set()
        initReservationViews(movieModel, savedInstanceState)
        loadSavedInstanceState(movieModel, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val selectedDate: LocalDate = binding.screeningDateSpinner.selectedItem as LocalDate
        val selectedTime: LocalTime = binding.screeningTimeSpinner.selectedItem as LocalTime

        outState.putInt(
            TICKET_COUNT_INSTANCE_KEY,
            binding.ticketCountTextView.text.toString().toInt()
        )
        outState.putLong(SCREENING_DATE_INSTANCE_KEY, selectedDate.toEpochDay())
        outState.putString(SCREENING_TIME_INSTANCE_KEY, selectedTime.toString())
    }

    private fun initReservationViews(movieModel: MovieModel, savedInstanceState: Bundle?) {
        binding.seatSelectionButton.setOnClickListener { seatSelectionButtonClickEvent(movieModel) }
        initSpinner(movieModel, savedInstanceState)
    }

    private fun initSpinner(movieModel: MovieModel, savedInstanceState: Bundle?) {
        val dates = movieModel.screeningPeriod.getScreeningDates()

        binding.screeningDateSpinner.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, dates
        )

        binding.screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    initTimeSpinner(movieModel, dates[position])
                    if (savedInstanceState != null) loadSavedInstanceState(
                        movieModel,
                        savedInstanceState
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    initTimeSpinner(movieModel, null)
                }
            }
    }

    private fun initTimeSpinner(movieModel: MovieModel, date: LocalDate?, position: Int = 0) {
        val times = movieModel.screeningPeriod.getScreeningTimes(date)

        binding.screeningTimeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )
        binding.screeningTimeSpinner.setSelection(position)
    }

    private fun loadSavedInstanceState(movieModel: MovieModel, instance: Bundle?) {
        if (instance == null) return
        loadSpinner(movieModel, instance)
        binding.ticketCountTextView.text = instance.getInt(TICKET_COUNT_INSTANCE_KEY).toString()
    }

    private fun loadSpinner(movieModel: MovieModel, savedInstanceState: Bundle) {
        val screeningDate: LocalDate =
            LocalDate.ofEpochDay(savedInstanceState.getLong(SCREENING_DATE_INSTANCE_KEY))
        val screeningTime: LocalTime =
            LocalTime.parse(savedInstanceState.getString(SCREENING_TIME_INSTANCE_KEY))
        val selectedDatePosition: Int =
            movieModel.screeningPeriod.getScreeningDates().indexOf(screeningDate)
        val selectedTimePosition: Int =
            movieModel.screeningPeriod.getScreeningTimes(screeningDate).indexOf(screeningTime)

        binding.screeningDateSpinner.setSelection(selectedDatePosition)
        initTimeSpinner(movieModel, screeningDate, selectedTimePosition)
    }

    private fun seatSelectionButtonClickEvent(movieModel: MovieModel) {
        val selectedDate: LocalDate = binding.screeningDateSpinner.selectedItem as LocalDate
        val selectedTime: LocalTime = binding.screeningTimeSpinner.selectedItem as LocalTime

        val intent = Intent(this, SeatSelectionActivity::class.java)
        intent.putExtra(MOVIE_INTENT_KEY, movieModel)
        intent.putExtra(TICKET_COUNT_INTENT_KEY, binding.ticketCountTextView.text.toString().toInt())
        intent.putExtra(SCREENING_DATE_TIME_INTENT_KEY, LocalDateTime.of(selectedDate, selectedTime))

        startActivity(intent)
    }

    private fun showToast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}
