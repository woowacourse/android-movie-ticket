package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.formatTimestamp

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_complete)

        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("ticket", Ticket::class.java)
            } else {
                intent.getSerializableExtra("ticket") as Ticket
            }

        ticket?.let { ticket ->
            findViewById<TextView>(R.id.completeTitle).text = ticket.title
            findViewById<TextView>(R.id.completeDate).text = formatTimestamp(ticket.date)
            findViewById<TextView>(R.id.completeReservationCount).text = "${ticket.count}명"
            findViewById<TextView>(R.id.completeReservationPrice).text =
                "${formatCurrency(ticket.price)}원"
        }
    }
}
