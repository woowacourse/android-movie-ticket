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
import woowacourse.movie.domain.payment.Reservation
import woowacourse.movie.domain.reservation.TicketCount
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

        val movieModel: MovieModel? by lazy { intent.getSerializableExtra(MOVIE_INTENT_KEY) as? MovieModel }

        initTicketCount()
        movieModel?.let {
            initReservationView(it)
            initClickListener(it)
            initSpinner(it)
            loadSavedInstanceState(it, savedInstanceState)
        }
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

    private fun initReservationView(movieModel: MovieModel?) {
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

    private fun initTicketCount() {
        binding.ticketCountTextView.text = TicketCount.MINIMUM.toString()
    }

    private fun initSpinner(movieModel: MovieModel) {
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    initTimeSpinner(movieModel, null)
                }
            }
    }

    private fun initTimeSpinner(movieModel: MovieModel, date: LocalDate?, defaultPoint: Int = 0) {
        val times = movieModel.screeningPeriod.getScreeningTimes(date)

        binding.screeningTimeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )
        binding.screeningTimeSpinner.setSelection(defaultPoint)
    }

    private fun initClickListener(movieModel: MovieModel) {
        initMinusClickListener()
        initPlusClickListener()
        initCompleteButton(movieModel)
    }

    private fun initMinusClickListener() {
        binding.ticketCountMinusButton.setOnClickListener {
            runCatching {
                val ticketCount =
                    TicketCount(binding.ticketCountTextView.text.toString().toInt() - 1)
                binding.ticketCountTextView.text = ticketCount.value.toString()
            }.onFailure {
                val ticketCountConditionMessage =
                    getString(R.string.ticket_count_condition_message_form).format(TicketCount.MINIMUM)
                Toast.makeText(this, ticketCountConditionMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initPlusClickListener() {
        binding.ticketCountPlusButton.setOnClickListener {
            val ticketCount = TicketCount(binding.ticketCountTextView.text.toString().toInt() + 1)
            binding.ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun initCompleteButton(movieModel: MovieModel) {
        binding.reservationCompleteButton.setOnClickListener {
            val ticketCount = binding.ticketCountTextView.text.toString().toInt()
            val screeningDate = binding.screeningDateSpinner.selectedItem as LocalDate
            val screeningTime = binding.screeningTimeSpinner.selectedItem as LocalTime
            val reservationModel: ReservationModel =
                Reservation.from(
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
}
