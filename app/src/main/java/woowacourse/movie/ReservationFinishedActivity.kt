package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import domain.Ticket
import java.io.Serializable
import java.text.DecimalFormat

class ReservationFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        val movie = intent.intentSerializable("movie", Movie::class.java)!!
        val ticket = intent.intentSerializable("ticket", Ticket::class.java)!!

        showReservationHistory(movie, ticket)
    }

    private fun showReservationHistory(
        movie: Movie,
        ticket: Ticket,
    ) {
        val title = findViewById<TextView>(R.id.text_view_reservation_finished_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_finished_screening_date)
        val numberOfTickets = findViewById<TextView>(R.id.text_view_reservation_finished_number_of_tickets)
        val ticketPrice = findViewById<TextView>(R.id.text_view_reservation_finished_ticket_price)

        title.text = movie.title
        screeningDate.text = movie.screeningDate
        numberOfTickets.text = ticket.count.toString()

        val decimalFormat = DecimalFormat("#,###")
        ticketPrice.text = decimalFormat.format(ticket.calculatePrice())
    }

    fun <T : Serializable> Intent.intentSerializable(
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
