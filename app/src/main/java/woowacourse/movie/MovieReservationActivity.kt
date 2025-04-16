package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
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

class MovieReservationActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private lateinit var selectedDate: LocalDate
    private lateinit var selectedTime: LocalTime
    private var ticketCount = MINIMUM_TICKET_COUNT
    private val scheduler = Scheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUi()

        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MovieAdapter.EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MovieAdapter.EXTRA_MOVIE)
        } ?: run {
            finish()
            return
        }

        val savedTicket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState?.getParcelable(EXTRA_TICKET, Ticket::class.java)
            } else {
                @Suppress("DEPRECATION")
                savedInstanceState?.getParcelable(EXTRA_TICKET)
            }

        initializeSpinners(savedTicket)
        savedTicket?.let { ticket ->
            ticketCount = ticket.count
            findViewById<TextView>(R.id.ticket_count).text = ticketCount.toString()
        }
        initTicketCountButton()
        initSelectButton(movie)
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

    private fun initializeUi() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeSpinners(savedTicket: Ticket?) {
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

        selectedDate = savedDateTime?.toLocalDate() ?: dateSpinner.selectedItem as LocalDate
        dateSpinner.setSelection(adapter.getPosition(selectedDate))
    }

    private fun initTimeSpinner(
        timeSpinner: Spinner,
        savedTime: LocalTime?,
    ) {
        val showtimes = scheduler.getShowTimes(selectedDate, LocalDateTime.now())
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

        selectedTime = savedTime ?: timeSpinner.selectedItem as LocalTime
        timeSpinner.setSelection(adapter.getPosition(selectedTime))
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

    private fun initSelectButton(movie: Movie) {
        val selectButton = findViewById<Button>(R.id.select_button)
        val alertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(R.string.confirm_reservation_title)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.confirm_reservation_text) { _, _ ->
                    val intent = Intent(this, MovieReservationCompletionActivity::class.java)
                    val ticket =
                        Ticket(
                            movie = movie,
                            showtime = LocalDateTime.of(selectedDate, selectedTime),
                            count = ticketCount,
                        )
                    intent.putExtra(EXTRA_TICKET, ticket)
                    startActivity(intent)
                }.setNegativeButton(R.string.cancel_text) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
    }

    companion object {
        const val EXTRA_TICKET = "ticket"
        private const val MINIMUM_TICKET_COUNT = 1
    }
}
