package woowacourse.movie

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
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationActivity : AppCompatActivity() {
    private var ticketCount = 0

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

        val scheduler = Scheduler()
        initDateSpinner(movie, scheduler)
        initTicketCountButton()
        initSelectButton()
    }

    private fun initDateSpinner(
        movie: Movie,
        scheduler: Scheduler,
    ) {
        val screeningDates =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDate.now())
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
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
                    initTimeSpinner(screeningDates[position], scheduler)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initTimeSpinner(
        selectedDate: LocalDate,
        scheduler: Scheduler,
    ) {
        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)
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
            if (ticketCount == 0) return@setOnClickListener
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
                .setPositiveButton(R.string.confirm_reservation_text) { _, _ -> }
                .setNegativeButton(R.string.cancel_text) { dialog, _ -> dialog.dismiss() }
        selectButton.setOnClickListener {
            alertDialog.show()
        }
    }
}
