package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.Ticket
import java.text.DecimalFormat

class ReservationFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        val title = findViewById<TextView>(R.id.text_view_reservation_finished_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_finished_screening_date)
        val numberOfTickets = findViewById<TextView>(R.id.text_view_reservation_finished_number_of_tickets)
        val ticketPrice = findViewById<TextView>(R.id.text_view_reservation_finished_ticket_price)

        val numberOfTicketDetail = intent.getIntExtra("numberOfTickets", 0)

        title.text = intent.getStringExtra("title")
        screeningDate.text = intent.getStringExtra("screeningDate")
        numberOfTickets.text = numberOfTicketDetail.toString()

        val ticket = Ticket()
        val decimalFormat = DecimalFormat("#,###")
        ticketPrice.text = decimalFormat.format(ticket.calculatePrice(numberOfTicketDetail))
    }
}
