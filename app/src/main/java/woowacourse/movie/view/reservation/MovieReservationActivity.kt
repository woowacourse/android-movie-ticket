package woowacourse.movie.view.reservation

import android.content.Context
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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.IntentKeys
import woowacourse.movie.common.parcelable
import woowacourse.movie.common.parcelableExtra
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Scheduler
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var ticketCount = MINIMUM_TICKET_COUNT
    private val scheduler = Scheduler()

    private val dateSpinner: Spinner by lazy { findViewById(R.id.date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.time_spinner) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.ticket_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        movie = intent.parcelableExtra(IntentKeys.EXTRA_MOVIE, Movie::class.java)
            ?: run {
                finish()
                return
            }

        initMovieInfo()
        val savedTicket =
            savedInstanceState?.parcelable(IntentKeys.EXTRA_TICKET, Ticket::class.java)

        initDateSpinner(savedTicket?.showtime)
        initTimeSpinner(savedTicket?.showtime?.toLocalTime())
        savedTicket?.let { ticket ->
            ticketCount = ticket.count
            ticketCountTextView.text = ticketCount.toString()
        }
        initTicketCountButton()
        initSelectButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (selectedDate != null && selectedTime != null) {
            val ticket =
                Ticket(
                    movie = movie,
                    showtime = LocalDateTime.of(selectedDate, selectedTime),
                    count = ticketCount,
                )
            outState.putParcelable(IntentKeys.EXTRA_TICKET, ticket)
        }
    }

    private fun initUi() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initMovieInfo() {
        val poster: ImageView = findViewById(R.id.poster)
        val title: TextView = findViewById(R.id.movie_title)
        val screeningDate: TextView = findViewById(R.id.screening_date)
        val runningTime: TextView = findViewById(R.id.running_time)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        val startDate = movie.startDate.format(DATE_FORMAT)
        val endDate = movie.endDate.format(DATE_FORMAT)
        screeningDate.text = getString(R.string.screening_date, startDate, endDate)
        runningTime.text = getString(R.string.running_time, movie.runningTime)
    }

    private fun initDateSpinner(savedDateTime: LocalDateTime?) {
        val screeningDates =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDate.now())

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningDates)
        dateSpinner.adapter = adapter

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedDate = screeningDates[position]
                    initTimeSpinner(savedDateTime?.toLocalTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        savedDateTime?.toLocalDate()?.let { selectedDate = it }
        selectedDate.let { dateSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initTimeSpinner(savedTime: LocalTime?) {
        val showtimes =
            selectedDate?.let { scheduler.getShowtimes(it, LocalDateTime.now()) } ?: emptyList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, showtimes)
        timeSpinner.adapter = adapter

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedTime = showtimes[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        savedTime?.let { selectedTime = it }
        selectedTime?.let { timeSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initTicketCountButton() {
        val decrementButton: Button = findViewById(R.id.decrement_button)
        if (ticketCount == MINIMUM_TICKET_COUNT) decrementButton.isEnabled = false
        val incrementButton: Button = findViewById(R.id.increment_button)

        ticketCountTextView.text = ticketCount.toString()
        decrementButton.setOnClickListener {
            ticketCount--
            ticketCountTextView.text = ticketCount.toString()
            if (ticketCount == MINIMUM_TICKET_COUNT) decrementButton.isEnabled = false
        }
        incrementButton.setOnClickListener {
            decrementButton.isEnabled = true
            ticketCount++
            ticketCountTextView.text = ticketCount.toString()
        }
    }

    private fun initSelectButton() {
        val selectButton: Button = findViewById(R.id.select_button)
        val alertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(R.string.confirm_reservation)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.complete_reservation) { _, _ ->
                    onConfirmReservation()
                }.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
        alertDialog.setCancelable(false)
    }

    private fun onConfirmReservation() {
        if (selectedDate != null && selectedTime != null) {
            val ticket =
                Ticket(
                    movie = movie,
                    showtime = LocalDateTime.of(selectedDate, selectedTime),
                    count = ticketCount,
                )
            val intent = MovieReservationCompleteActivity.newIntent(this, ticket)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.select_date_and_time, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(IntentKeys.EXTRA_MOVIE, movie)
            }

        private const val MINIMUM_TICKET_COUNT = 1
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
