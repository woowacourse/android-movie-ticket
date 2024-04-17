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

        val title = findViewById<TextView>(R.id.text_view_reservation_finished_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_finished_screening_date)
        val numberOfTickets = findViewById<TextView>(R.id.text_view_reservation_finished_number_of_tickets)
        val ticketPrice = findViewById<TextView>(R.id.text_view_reservation_finished_ticket_price)

        val movie = intent.intentSerializable("movie", Movie::class.java)!!
        val numberOfTicketDetail = intent.getIntExtra("numberOfTickets", 0)

        title.text = movie.title
        screeningDate.text = movie.screeningDate
        numberOfTickets.text = numberOfTicketDetail.toString()

        val ticket = Ticket()
        val decimalFormat = DecimalFormat("#,###")
        ticketPrice.text = decimalFormat.format(ticket.calculatePrice(numberOfTicketDetail))
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
