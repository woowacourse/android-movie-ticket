package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.Failure
import domain.Movie
import domain.Result
import domain.Success
import domain.Ticket
import java.io.Serializable

class ReservationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ticket = Ticket()
        val movie = intent.intentSerializable("movie", Movie::class.java)!!

        showMovieInformation(movie)
        initializePlusButton(ticket)
        initializeMinusButton(ticket)
        initializeReservationButton(ticket, movie)
    }

    private fun showMovieInformation(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.image_view_reservation_detail_poster)
        val title = findViewById<TextView>(R.id.text_view_reservation_detail_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_screening_date)
        val runningTime = findViewById<TextView>(R.id.text_view_reservation_running_time)
        val summary = findViewById<TextView>(R.id.text_view_reservation_summary)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text = movie.screeningDate
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    private fun initializePlusButton(ticket: Ticket) {
        val plusButton = findViewById<Button>(R.id.button_reservation_detail_plus)
        val numberOfTickets =
            findViewById<TextView>(R.id.text_view_reservation_detail_number_of_tickets)

        plusButton.setOnClickListener {
            val result = ticket.increaseCount()
            handleNumberOfTicketsBounds(result, numberOfTickets, ticket)
        }
    }

    private fun initializeMinusButton(ticket: Ticket) {
        val minusButton = findViewById<Button>(R.id.button_reservation_detail_minus)
        val numberOfTickets =
            findViewById<TextView>(R.id.text_view_reservation_detail_number_of_tickets)

        minusButton.setOnClickListener {
            val result = ticket.decreaseCount()
            handleNumberOfTicketsBounds(result, numberOfTickets, ticket)
        }
    }

    private fun initializeReservationButton(
        ticket: Ticket,
        movie: Movie,
    ) {
        val reservationButton = findViewById<Button>(R.id.button_reservation_detail_finished)

        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationFinishedActivity::class.java)
            intent.putExtra("movie", movie)
            intent.putExtra("ticket", ticket)
            startActivity(intent)
        }
    }

    private fun handleNumberOfTicketsBounds(
        result: Result,
        numberOfTickets: TextView,
        ticket: Ticket,
    ) {
        when (result) {
            is Success -> numberOfTickets.text = ticket.count.toString()
            is Failure -> Toast.makeText(this, getString(R.string.invalid_number_of_tickets), Toast.LENGTH_SHORT).show()
        }
    }

    private fun <T : Serializable> Intent.intentSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }
}
