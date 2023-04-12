package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Ticket
import java.time.format.DateTimeFormatter

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val ticket = intent.getSerializableExtra("ticket") as Ticket

        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        val numberOfPeople = findViewById<TextView>(R.id.ticket_numberOfPeople)
        val price = findViewById<TextView>(R.id.ticket_price)

        movieTitle.text = ticket.movieTitle
        movieDate.text = ticket.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        numberOfPeople.text = ticket.calculateTicketPrice().toString()
        price.text = (ticket.numberOfPeople * ticket.price).toString()
    }
}
