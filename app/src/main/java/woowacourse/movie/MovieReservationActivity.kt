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
    private lateinit var ticket: Ticket
    private lateinit var dateAdapter: ArrayAdapter<LocalDate>
    private lateinit var timeAdapter: ArrayAdapter<LocalTime>
    private val dateSpinner by lazy { findViewById<Spinner>(R.id.date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.time_spinner) }
    private val scheduler = Scheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        movie = intent.extras?.getParcelableCompat<Movie>(MovieAdapter.KEY_MOVIE) ?: run { return }
        initializeMovieInfo()
        initializeDateSpinner()
        if (!::dateAdapter.isInitialized) {
            showToast()
            finish()
            return
        }
        initializeTimeSpinner()
        initializeTicket()
        initializeTicketCountButton()
        initializeSelectButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_TICKET, ticket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        ticket = savedInstanceState.getParcelableCompat<Ticket>(KEY_TICKET) ?: run { return }
        initializeTicketCountButton()
    }

    private fun initializeView() {
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
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_format))
        val startDate = movie.startDate.format(formatter)
        val endDate = movie.endDate.format(formatter)
        screeningDate.text = getString(R.string.screening_dates_format).format(startDate, endDate)
        runningTime.text = getString(R.string.running_type_format).format(movie.runningTime)
    }

    private fun initializeDateSpinner() {
        val screeningDates =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDateTime.now())
        if (screeningDates.isEmpty()) return
        dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningDates)
        dateSpinner.adapter = dateAdapter

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedShowtime =
                        LocalDateTime.of(
                            dateAdapter.getItem(position),
                            ticket.showtime.toLocalTime(),
                        )
                    ticket = ticket.copy(showtime = selectedShowtime)
                    initializeTimeSpinner()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun initializeTimeSpinner() {
        val showtimes =
            (dateSpinner.selectedItem as LocalDate).let { selectedDate ->
                scheduler.getShowtimes(selectedDate, LocalDateTime.now())
            }
        if (showtimes.isEmpty()) return
        timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, showtimes)
        timeSpinner.adapter = timeAdapter

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedShowtime =
                        LocalDateTime.of(
                            ticket.showtime.toLocalDate(),
                            timeAdapter.getItem(position),
                        )
                    ticket = ticket.copy(showtime = selectedShowtime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }

        if (::ticket.isInitialized) {
            timeSpinner.setSelection(timeAdapter.getPosition(ticket.showtime.toLocalTime()))
        }
    }

    private fun initializeTicket() {
        ticket =
            Ticket(
                movie,
                LocalDateTime.of(
                    dateAdapter.getItem(0),
                    timeAdapter.getItem(0),
                ),
                Ticket.MINIMUM_TICKET_COUNT,
            )
    }

    private fun initializeTicketCountButton() {
        val incrementButton = findViewById<Button>(R.id.increment_button)
        val decrementButton = findViewById<Button>(R.id.decrement_button)
        decrementButton.isEnabled = ticket.count > Ticket.MINIMUM_TICKET_COUNT
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)
        ticketCountTextView.text = ticket.count.toString()

        incrementButton.setOnClickListener {
            decrementButton.isEnabled = true
            ticket = ticket.increment()
            ticketCountTextView.text = ticket.count.toString()
        }

        decrementButton.setOnClickListener {
            ticket = ticket.decrement()
            ticketCountTextView.text = ticket.count.toString()
            decrementButton.isEnabled = ticket.count > Ticket.MINIMUM_TICKET_COUNT
        }
    }

    private fun initializeSelectButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        val alertDialog =
            AlertDialog.Builder(this).setTitle(R.string.confirm_reservation_alert_title)
                .setMessage(R.string.confirm_reservation_alert_message)
                .setPositiveButton(R.string.confirm_reservation_alert_yes) { _, _ ->
                    onConfirmReservation()
                }
                .setNegativeButton(R.string.confirm_reservation_alert_no) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
        alertDialog.setCancelable(false)
    }

    private fun onConfirmReservation() {
        val intent = Intent(this, MovieReservationResultActivity::class.java)
        intent.putExtra(KEY_TICKET, ticket)
        startActivity(intent)
    }

    private fun showToast() {
        Toast.makeText(
            this,
            getString(R.string.no_screening_date_available_toast_message),
            Toast.LENGTH_SHORT,
        ).show()
    }

    companion object {
        const val KEY_TICKET = "ticket"
    }
}
