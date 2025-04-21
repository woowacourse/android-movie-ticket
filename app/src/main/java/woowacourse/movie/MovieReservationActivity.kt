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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Scheduler
import woowacourse.movie.domain.Ticket
import woowacourse.movie.util.getParcelableCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private val dateSpinner by lazy { findViewById<Spinner>(R.id.date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.time_spinner) }
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var ticketCount = MINIMUM_TICKET_COUNT
    private val scheduler = Scheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUi()

        movie = intent.extras?.getParcelableCompat<Movie>(MovieAdapter.KEY_MOVIE) ?: run {
            finish()
            return
        }
        initializeMovieInfo()

        val savedTicket: Ticket? = savedInstanceState?.getParcelableCompat<Ticket>(KEY_TICKET)
        initializeSpinners(savedTicket)
        savedTicket?.let { ticket ->
            ticketCount = ticket.count
            findViewById<TextView>(R.id.ticket_count).text = ticketCount.toString()
        }

        initializeTicketCountButton()
        initializeSelectButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val ticket =
            Ticket(
                movie = movie,
                showtime = LocalDateTime.of(selectedDate, selectedTime),
                count = ticketCount,
            )
        outState.putParcelable(KEY_TICKET, ticket)
    }

    private fun initializeUi() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeMovieInfo() {
        val poster = findViewById<ImageView>(R.id.poster)
        val title = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)
        val runningTime = findViewById<TextView>(R.id.running_time)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        val startDate = movie.startDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format_pattern)))
        val endDate = movie.endDate.format(DateTimeFormatter.ofPattern(getString(R.string.date_format_pattern)))
        screeningDate.text = getString(R.string.screening_date_range_template).format(startDate, endDate)
        runningTime.text = getString(R.string.running_time_template).format(movie.runningTime)
    }

    private fun initializeSpinners(savedTicket: Ticket?) {
        initializeDateSpinner(savedTicket?.showtime)
        initializeTimeSpinner(savedTicket?.showtime?.toLocalTime())
    }

    private fun initializeDateSpinner(savedDateTime: LocalDateTime?) {
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
                    initializeTimeSpinner(savedDateTime?.toLocalTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        savedDateTime?.toLocalDate()?.let { selectedDate = it }
        selectedDate.let { dateSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initializeTimeSpinner(savedTime: LocalTime?) {
        val showtimes =
            selectedDate?.let {
                scheduler.getShowtimes(it, LocalDateTime.now())
            } ?: emptyList()
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

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        savedTime?.let { selectedTime = it }
        selectedTime?.let { timeSpinner.setSelection(adapter.getPosition(it)) }
    }

    private fun initializeTicketCountButton() {
        val decrementButton = findViewById<Button>(R.id.decrement_button)
        if (ticketCount == MINIMUM_TICKET_COUNT) decrementButton.isEnabled = false
        val incrementButton = findViewById<Button>(R.id.increment_button)
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)

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

    private fun initializeSelectButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        val alertDialog =
            AlertDialog.Builder(this).setTitle(R.string.confirm_reservation_title)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.confirm_reservation_yes) { _, _ ->
                    onConfirmReservation()
                }.setNegativeButton(R.string.confirm_reservation_no) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
        alertDialog.setCancelable(false)
    }

    private fun onConfirmReservation() {
        if (selectedDate != null && selectedTime != null) {
            val intent = Intent(this, MovieReservationResultActivity::class.java)
            val ticket =
                Ticket(
                    movie = movie,
                    showtime = LocalDateTime.of(selectedDate, selectedTime),
                    count = ticketCount,
                )
            intent.putExtra(KEY_TICKET, ticket)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.incorrect_date_and_time, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val KEY_TICKET = "ticket"
        private const val MINIMUM_TICKET_COUNT = 1
    }
}
