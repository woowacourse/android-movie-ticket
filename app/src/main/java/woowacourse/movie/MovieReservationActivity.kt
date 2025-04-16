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
    private var ticketCount = MINIMUM_TICKET_COUNT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movie", Movie::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("movie")
            }
        if (movie == null) {
            finish()
            return
        }

        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
        val scheduler = Scheduler()
        initDateSpinner(dateSpinner, timeSpinner, movie, scheduler)
        initTimeSpinner(timeSpinner, dateSpinner.selectedItem as LocalDate, scheduler)
        initTicketCountButton()
        initSelectButton(
            movie,
            dateSpinner,
            timeSpinner,
        )
    }

    private fun initDateSpinner(
        dateSpinner: Spinner,
        timeSpinner: Spinner,
        movie: Movie,
        scheduler: Scheduler,
    ) {
        val screeningDates =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDate.now())
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningDates,
            )

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    initTimeSpinner(timeSpinner, screeningDates[position], scheduler)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTimeSpinner(
        timeSpinner: Spinner,
        selectedDate: LocalDate,
        scheduler: Scheduler,
    ) {
        timeSpinner.adapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                scheduler.getShowTimes(selectedDate, LocalDateTime.now()),
            )
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

    private fun initSelectButton(
        movie: Movie,
        dateSpinner: Spinner,
        timeSpinner: Spinner,
    ) {
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
                            showtime =
                                LocalDateTime.of(
                                    dateSpinner.selectedItem as LocalDate,
                                    timeSpinner.selectedItem as LocalTime,
                                ),
                            count = ticketCount,
                        )
                    intent.putExtra("ticket", ticket)
                    startActivity(intent)
                }.setNegativeButton(R.string.cancel_text) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
    }

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
    }
}
