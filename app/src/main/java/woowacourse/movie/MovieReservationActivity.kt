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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MovieAdapter.EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MovieAdapter.EXTRA_MOVIE)
        } ?: run {
            finish()
            return
        }

        initMovieInfo()
        val savedTicket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState?.getParcelable(EXTRA_TICKET, Ticket::class.java)
            } else {
                @Suppress("DEPRECATION")
                savedInstanceState?.getParcelable(EXTRA_TICKET)
            }

        initSpinners(savedTicket)
        savedTicket?.let { ticket ->
            ticketCount = ticket.count
            findViewById<TextView>(R.id.ticket_count).text = ticketCount.toString()
        }
        initTicketCountButton()
        initSelectButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val ticket =
            Ticket(
                movie = movie,
                showtime = LocalDateTime.of(selectedDate, selectedTime),
                count = ticketCount,
            )
        outState.putParcelable(EXTRA_TICKET, ticket)
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
        val poster = findViewById<ImageView>(R.id.poster)
        val title = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)
        val runningTime = findViewById<TextView>(R.id.running_time)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        val startDate = movie.startDate.format(DATE_FORMAT)
        val endDate = movie.endDate.format(DATE_FORMAT)
        screeningDate.text = SCREENING_DATE_RANGE.format(startDate, endDate)
        runningTime.text = RUNNING_TIME.format(movie.runningTime)
    }

    private fun initSpinners(savedTicket: Ticket?) {
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
        initDateSpinner(dateSpinner, timeSpinner, savedTicket?.showtime)
        initTimeSpinner(timeSpinner, savedTicket?.showtime?.toLocalTime())
    }

    private fun initDateSpinner(
        dateSpinner: Spinner,
        timeSpinner: Spinner,
        savedDateTime: LocalDateTime?,
    ) {
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
                    initTimeSpinner(timeSpinner, savedDateTime?.toLocalTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        savedDateTime?.toLocalDate()?.let { selectedDate = it }
        selectedDate.let { dateSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initTimeSpinner(
        timeSpinner: Spinner,
        savedTime: LocalTime?,
    ) {
        val showtimes =
            selectedDate?.let { scheduler.getShowTimes(it, LocalDateTime.now()) } ?: emptyList()
        val adapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                showtimes,
            )
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

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        savedTime?.let { selectedTime = it }
        selectedTime?.let { timeSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initTicketCountButton() {
        val decrementButton = findViewById<Button>(R.id.decrement_button)
        val incrementButton = findViewById<Button>(R.id.increment_button)
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)

        decrementButton.setOnClickListener {
            if (ticketCount == MINIMUM_TICKET_COUNT) return@setOnClickListener
            ticketCount--
            ticketCountTextView.text = ticketCount.toString()
        }
        incrementButton.setOnClickListener {
            if (ticketCount == Int.MAX_VALUE) return@setOnClickListener
            ticketCount++
            ticketCountTextView.text = ticketCount.toString()
        }
    }

    private fun initSelectButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        val alertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(R.string.confirm_reservation_title)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.confirm_reservation_text) { _, _ ->
                    onConfirmReservation()
                }.setNegativeButton(R.string.cancel_text) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
        alertDialog.setCancelable(false)
    }

    private fun onConfirmReservation() {
        if (selectedDate != null && selectedTime != null) {
            val intent = Intent(this, MovieReservationCompletionActivity::class.java)
            val ticket =
                Ticket(
                    movie = movie,
                    showtime = LocalDateTime.of(selectedDate, selectedTime),
                    count = ticketCount,
                )
            intent.putExtra(EXTRA_TICKET, ticket)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.incorrect_date_and_time, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_TICKET = "ticket"
        private const val MINIMUM_TICKET_COUNT = 1
        private const val SCREENING_DATE_RANGE = "%s ~ %S"
        private const val RUNNING_TIME = "%d분"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
