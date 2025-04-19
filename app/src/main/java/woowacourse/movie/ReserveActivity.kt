package woowacourse.movie

import android.content.Intent
import android.os.Build
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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieDateScheduler
import woowacourse.movie.domain.MovieTimeScheduler
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ReservationScheduler
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.domain.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val reservationScheduler =
        ReservationScheduler(MovieDateScheduler(), MovieTimeScheduler())
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_ticket_count) }
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

        val movie = movie()
        if (movie == null) {
            showEmptyDialog()
        } else {
            initMovieInfo(movie)
            initDateSpinner(movie.screeningDate)
            initTimeSpinner(movie.screeningDate.startDate)

            reservation = savedInstanceState?.getSerializable(KEY_RESERVATION) as? Reservation
                ?: Reservation(
                    title = movie.title,
                    _count = TicketCount(DEFAULT_TICKET_COUNT_SIZE),
                    reservedTime = getSelectedDateTime(),
                )

            updateTicketCount()
            initButtonClickListeners()
        }
    }

    private fun movie(): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_MOVIE, Movie::class.java)
        } else {
            intent.getParcelableExtra(KEY_MOVIE) as? Movie
        }
    }

    private fun initMovieInfo(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        val formattedScreeningDate = formatting(movie.screeningDate)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formattedScreeningDate
        runningTime.text = getString(R.string.formatted_minute, movie.runningTime.time)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = reservationScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )

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

    private fun initTimeSpinner(startDate: LocalDate) {
        val selectedDate =
            reservationScheduler.startDate(
                startDate,
                LocalDate.now(),
            )

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
        val currentDateTime =
            LocalDateTime.now()
                .withMinute(0)
                .withSecond(0)
                .withNano(0)

        val times =
            reservationScheduler.reservableTimes(
                selectedDate,
                currentDateTime,
            )

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )
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
        val minMovieTicketMessage =
            Toast.makeText(
                this,
                getString(R.string.validate_min_movie_ticket),
                Toast.LENGTH_SHORT,
            )
        minusBtn.setOnClickListener {
            if (reservation.canMinus()) {
                reservation = reservation.minusCount()
                updateTicketCount()
            } else {
                minMovieTicketMessage.show()
            }
        }

        plusBtn.setOnClickListener {
            reservation = reservation.plusCount()
            updateTicketCount()
        }

        val alertDialog =
            initSelectDialog(
                getString(R.string.reserve_dialog_title),
                getString(R.string.reserve_dialog_message),
            ) {
                val intent =
                    Intent(this, ReservationResultActivity::class.java).apply {
                        putExtra(KEY_RESERVATION, reservation)
                    }
                startActivity(intent)
            }

        selectBtn.setOnClickListener {
            alertDialog.show()
        }
    }

    private fun showEmptyDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.empty_movie_title))
            .setMessage(getString(R.string.empty_movie_message))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun initSelectDialog(
        title: String,
        message: String,
        onConfirm: () -> Unit,
    ): AlertDialog.Builder {
        return AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.reserve_dialog_positive_button)) { _, _ -> onConfirm() }
            .setNegativeButton(getString(R.string.reserve_dialog_negative_button)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
    }

    private fun updateTicketCount() {
        ticketCount.text = reservation.count.toString()
    }

    private fun formatting(screeningDate: ScreeningDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(getString(R.string.date_format))
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return getString(R.string.formatted_screening_date, start, end)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_RESERVATION, reservation)
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT_SIZE = 1
        private const val KEY_MOVIE = "movie"
        private const val KEY_RESERVATION = "reservation"
    }
}
