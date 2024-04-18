package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieReservationCompleteActivity : AppCompatActivity() {
    private lateinit var ticketTitle: TextView
    private lateinit var ticketScreeningDate: TextView
    private lateinit var ticketPrice: TextView
    private lateinit var ticketCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(Ticket.KEY_NAME_TICKET, Ticket::class.java)
            } else {
                intent.getSerializableExtra(Ticket.KEY_NAME_TICKET) as? Ticket
            }
        initView(ticket)
    }

    private fun initView(ticket: Ticket?) {
        ticketTitle = findViewById(R.id.ticket_title)
        ticketScreeningDate = findViewById(R.id.ticket_screening_date)
        ticketPrice = findViewById(R.id.ticket_price)
        ticketCount = findViewById(R.id.ticket_number_of_people)

        ticket?.let {
            ticketTitle.text = it.title
            ticketScreeningDate.text = it.screeningDate
            ticketPrice.text = String.format(TICKET_PRICE, it.price)
            ticketCount.text = TICKET_COUNT.format(it.count)
        }
    }

    companion object {
        private const val TICKET_PRICE = "%,d원 (현장결제)"
        private const val TICKET_COUNT = "일반 %d명"
    }
}
