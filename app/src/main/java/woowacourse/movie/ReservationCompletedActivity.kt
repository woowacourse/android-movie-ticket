package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Ticket
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        presenter.onViewCreated()
    }

    override fun readTicketData() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("ticket", Ticket::class.java)
        } else {
            intent.getSerializableExtra("ticket") as? Ticket
        }

    override fun initializeTicketDetails(ticket: Ticket) {
        findViewById<TextView>(R.id.movie_title).text = ticket.getTitle()
        findViewById<TextView>(R.id.opening_day).text = ticket.getOpeningDay()
        findViewById<TextView>(R.id.quantity).text = "일반 ${ticket.quantity}명"
        findViewById<TextView>(R.id.price).text =
            "${DecimalFormat("#,###").format(ticket.getPrice() * ticket.quantity)}원 (현장 결제)"
    }
}
