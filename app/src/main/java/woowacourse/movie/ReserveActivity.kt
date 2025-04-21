package woowacourse.movie

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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.ReservationResultActivity.Companion.KEY_RESERVATION
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieDateScheduler
import woowacourse.movie.domain.MovieTimeScheduler
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ReservationScheduler
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.domain.TicketType
import woowacourse.movie.domain.Tickets
import woowacourse.movie.extensions.serializableData
import woowacourse.movie.factory.CustomDialogFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_ticket_count) }
    private val reservationScheduler =
        ReservationScheduler(MovieDateScheduler(), MovieTimeScheduler())
    private val customDialogFactory = CustomDialogFactory()
    private lateinit var reservation: Reservation
    private var isDateInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        initSystemUI()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initWithMovie(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_RESERVATION, reservation)
        outState.putInt(KEY_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_TIME_POSITION, timeSpinner.selectedItemPosition)
        outState.putSerializable(KEY_SELECTED_DATE, dateSpinner.selectedItem as LocalDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val datePosition = savedInstanceState.getInt(KEY_DATE_POSITION)
        val timePosition = savedInstanceState.getInt(KEY_TIME_POSITION)
        val selectedDate = savedInstanceState.getSerializable(KEY_SELECTED_DATE) as LocalDate
        dateSpinner.setSelection(datePosition)
        updateTimeSpinner(selectedDate)
        timeSpinner.setSelection(timePosition)
        isDateInit = true
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initWithMovie(savedInstanceState: Bundle?) {
        val movie = movie()
        if (movie == null) {
            showMissingMovieDialog()
        } else {
            initMovieContent(movie)
            initReservation(savedInstanceState, movie)
        }
    }

    private fun movie(): Movie? = intent.serializableData(KEY_MOVIE, Movie::class.java)

    private fun showMissingMovieDialog() {
        customDialogFactory.emptyValueDialog(
            this,
            getString(R.string.empty_movie_title),
            getString(R.string.empty_movie_message),
            ::finish,
        ).show()
    }

    private fun initMovieContent(movie: Movie) {
        with(movie) {
            findViewById<ImageView>(R.id.iv_poster).setImageResource(imageUrl)
            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_screening_date).text = formatScreeningDate(screeningDate)
            findViewById<TextView>(R.id.tv_running_time).text =
                getString(R.string.formatted_minute, runningTime.time)
        }
    }

    private fun initReservation(
        savedInstanceState: Bundle?,
        movie: Movie,
    ) {
        val timePosition = savedInstanceState?.getInt(KEY_TIME_POSITION) ?: 0
        initDateSpinner(movie.screeningDate)
        initTimeSpinner(movie.screeningDate.startDate)
        this.reservation = savedInstanceState?.getSerializable(KEY_RESERVATION) as? Reservation
            ?: Reservation(
                movie.title, getSelectedDateTime(),
                Tickets(listOf(TicketType.DEFAULT)),
            )
        updateTicketCount()
        initButtonListeners()
        timeSpinner.setSelection(timePosition)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = reservationScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateSpinner.onItemSelectedListener = createDateSelectionListener(dates)
    }

    private fun initTimeSpinner(startDate: LocalDate) {
        updateTimeSpinner(reservationScheduler.startDate(startDate, LocalDate.now()))
        timeSpinner.onItemSelectedListener = createTimeSelectionListener()
    }

    private fun updateTimeSpinner(selectedDate: LocalDate) {
        val times =
            reservationScheduler.reservableTimes(
                selectedDate,
                LocalDateTime.now(),
            )

        timeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
    }

    private fun getSelectedDateTime(): LocalDateTime =
        LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate,
            timeSpinner.selectedItem as LocalTime,
        )

    private fun initButtonListeners() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener {
            if (reservation.canMinus()) {
                reservation = reservation.minusCount()
                updateTicketCount()
            } else {
                Toast.makeText(this, R.string.validate_min_movie_ticket, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_plus).setOnClickListener {
            reservation = reservation.plusCount()
            updateTicketCount()
        }
        val dialog =
            customDialogFactory.selectDialog(
                this,
                getString(R.string.reserve_dialog_title),
                getString(R.string.reserve_dialog_message),
            ) {
                if (::reservation.isInitialized) {
                    startActivity(
                        Intent(this, ReservationResultActivity::class.java).apply {
                            putExtra(KEY_RESERVATION, reservation)
                        },
                    )
                } else {
                    customDialogFactory.emptyValueDialog(
                        this,
                        getString(R.string.error_reservation_title),
                        getString(R.string.error_reservation_message),
                        ::finish,
                    ).show()
                }
            }

        findViewById<Button>(R.id.btn_select).setOnClickListener {
            dialog.show()
        }
    }

    private fun updateTicketCount() {
        ticketCountTextView.text = reservation.ticketCount.toString()
    }

    private fun formatScreeningDate(screeningDate: ScreeningDate): String {
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_format))
        return getString(
            R.string.formatted_screening_date,
            screeningDate.startDate.format(formatter),
            screeningDate.endDate.format(formatter),
        )
    }

    private fun createDateSelectionListener(dates: List<LocalDate>) =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (isDateInit) {
                    isDateInit = false
                    return
                }

                val selectedDate = dates[position]
                updateTimeSpinner(selectedDate)
                reservation = reservation.updateReservedTime(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    private fun createTimeSelectionListener() =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                reservation = reservation.updateReservedTime(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    companion object {
        private const val KEY_DATE_POSITION = "datePosition"
        private const val KEY_TIME_POSITION = "timePosition"
        private const val KEY_SELECTED_DATE = "selectedDate"
        const val KEY_MOVIE = "movie"
    }
}
