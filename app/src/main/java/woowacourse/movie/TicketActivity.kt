package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        setupView()
        setupActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupView() {
        val ticket: Ticket = intent
            ?.parcelable("ticket") ?: throw IllegalArgumentException()

        val title = findViewById<TextView>(R.id.ticket_title)
        val personCount = findViewById<TextView>(R.id.ticket_person_count)
        val price = findViewById<TextView>(R.id.ticket_price)
        val date = findViewById<TextView>(R.id.ticket_date)

        title.text = ticket.movie.title
        personCount.text = ticket.personCount.toString()
        price.text = ticket.calculateTicketPrice().toString()
        date.text = ticket.movie.date
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
