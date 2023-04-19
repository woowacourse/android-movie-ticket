package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel
import woowacourse.movie.uimodel.ReservationModel.Companion.RESERVATION_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_INSTANCE_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_TIME_INSTANCE_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.TICKET_COUNT_INSTANCE_KEY
import woowacourse.movie.uimodel.toReservationModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel? = intent.getSerializableExtra(MOVIE_INTENT_KEY) as? MovieModel
        if (movieModel == null) {
            Toast.makeText(this, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        initMovieInformationViews(movieModel!!)
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

    private fun initMovieInformationViews(movieModel: MovieModel?) {
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        movieModel!!.posterImage?.let { id -> binding.moviePosterImageView.setImageResource(id) }
        binding.movieNameTextView.text = movieModel.name.value
        binding.movieScreeningPeriodTextView.text =
            getString(R.string.screening_period_form).format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
        binding.movieRunningTimeTextView.text =
            getString(R.string.running_time_form).format(movieModel.runningTime)
        binding.movieDescriptionTextView.text = movieModel.description
    }

    private fun initReservationViews(movieModel: MovieModel, savedInstanceState: Bundle?) {
        binding.ticketCountTextView.text = reservation.TicketCount.MINIMUM.toString()

        binding.ticketCountMinusButton.setOnClickListener { ticketCountMinusButtonClickEvent() }
        binding.ticketCountPlusButton.setOnClickListener { ticketCountPlusButtonClickEvent() }
        binding.completeButton.setOnClickListener { completeButtonClickEvent(movieModel) }

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

    private fun ticketCountMinusButtonClickEvent() {
        runCatching {
            val ticketCount =
                reservation.TicketCount(binding.ticketCountTextView.text.toString().toInt() - 1)
            binding.ticketCountTextView.text = ticketCount.value.toString()
        }.onFailure {
            val ticketCountConditionMessage =
                getString(R.string.ticket_count_condition_message_form).format(reservation.TicketCount.MINIMUM)
            Toast.makeText(this, ticketCountConditionMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun ticketCountPlusButtonClickEvent() {
        val ticketCount =
            reservation.TicketCount(binding.ticketCountTextView.text.toString().toInt() + 1)
        binding.ticketCountTextView.text = ticketCount.value.toString()
    }

    private fun completeButtonClickEvent(movieModel: MovieModel) {
        val ticketCount = binding.ticketCountTextView.text.toString().toInt()
        val screeningDate = binding.screeningDateSpinner.selectedItem as LocalDate
        val screeningTime = binding.screeningTimeSpinner.selectedItem as LocalTime
        val reservationModel: ReservationModel =
            payment.Reservation.from(
                movieModel.toDomainModel(),
                ticketCount,
                LocalDateTime.of(screeningDate, screeningTime)
            ).toReservationModel()
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(RESERVATION_INTENT_KEY, reservationModel)
        startActivity(intent)
        finish()
    }
}
