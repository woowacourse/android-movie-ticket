package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.domain.TimeScheduler
import woowacourse.movie.ext.getSerializableCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_ticket_count) }
    private val movie: Movie by lazy { setUpMovie() }
    private lateinit var reservation: Reservation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserve)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reservation = getReservation(savedInstanceState)

        initMovieInfo()
        initDateSpinner()
        initTimeSpinner()

        updateTicketCount()
        initButtonClickListeners()
    }

    private fun setUpMovie(): Movie {
        return intent.getSerializableCompat<Movie>(KeyIdentifiers.KEY_MOVIE)
    }

    private fun getReservation(savedInstanceState: Bundle?): Reservation {
        return savedInstanceState?.getSerializable(KeyIdentifiers.KEY_RESERVATION) as? Reservation
            ?: Reservation(
                title = movie.title,
                _count = TicketCount(DEFAULT_TICKET_COUNT_SIZE),
                reservedTime = getInitSchedule(movie.screeningDate),
            )
    }

    private fun getInitSchedule(screeningDate: ScreeningDate): LocalDateTime {
        val firstDate =
            screeningDate.reservableDates(LocalDate.now()).first()

        val firstTime =
            TimeScheduler.reservableTimes(
                firstDate,
                getCurrentTime(),
            ).first()

        return LocalDateTime.of(firstDate, firstTime)
    }

    private fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now()
            .plusHours(1)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
    }

    private fun initMovieInfo() {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formatting(movie.screeningDate)
        runningTime.text = getString(R.string.text_running_time).format(movie.runningTime.time)
    }

    private fun initDateSpinner() {
        val dates = movie.screeningDate.reservableDates(LocalDate.now())

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
        dateSpinner.setSelection(dates.indexOf(reservation.reservedTime.toLocalDate()))

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectDate = dates[position]
                    updateTimeSpinner(selectDate)
                    reservation = reservation.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun initTimeSpinner() {
        val selectedDate = dateSpinner.selectedItem as LocalDate

        updateTimeSpinner(selectedDate)

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    reservation = reservation.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun updateTimeSpinner(selectedDate: LocalDate) {
        val times =
            TimeScheduler.reservableTimes(
                selectedDate,
                getCurrentTime(),
            )

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )
        timeSpinner.adapter = adapter

        timeSpinner.setSelection(times.indexOf(reservation.reservedTime.toLocalTime()))
    }

    private fun getSelectedDateTime(): LocalDateTime {
        return LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate?,
            timeSpinner.selectedItem as LocalTime,
        )
    }

    private fun initButtonClickListeners() {
        val minusBtn = findViewById<Button>(R.id.btn_minus)
        val plusBtn = findViewById<Button>(R.id.btn_plus)
        val selectBtn = findViewById<Button>(R.id.btn_select)

        minusBtn.setOnClickListener {
            if (reservation.canMinus()) {
                reservation = reservation.minusCount()
                updateTicketCount()
            }
        }

        plusBtn.setOnClickListener {
            reservation = reservation.plusCount()
            updateTicketCount()
        }

        selectBtn.setOnClickListener {
            showSelectDialog()
        }
    }

    private fun showSelectDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reserve_dialog_title))
            .setMessage(getString(R.string.reserve_dialog_message))
            .setPositiveButton(getString(R.string.reserve_dialog_positive_button)) { _, _ ->
                val intent = ReservationResultActivity.newIntent(this, reservation)
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.reserve_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun updateTicketCount() {
        ticketCount.text = reservation.count.toString()
    }

    private fun formatting(screeningDate: ScreeningDate): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return getString(R.string.text_screening_date).format(start, end)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KeyIdentifiers.KEY_RESERVATION, reservation)
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val DEFAULT_TICKET_COUNT_SIZE = 1

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, ReserveActivity::class.java).apply {
                putExtra(KeyIdentifiers.KEY_MOVIE, movie)
            }
    }
}
