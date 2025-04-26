package woowacourse.movie.activity.bookingresult

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.booking.BookingActivity
import woowacourse.movie.domain.Ticket

class BookingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        val ticket = intent.getParcelableExtra<Ticket>(BookingActivity.Companion.KEY_TICKET)

        if (ticket != null) {
            findViewById<TextView>(R.id.title).text = ticket.title
            findViewById<TextView>(R.id.date).text = ticket.date.toString()
            findViewById<TextView>(R.id.time).text = ticket.time.toString()
            findViewById<TextView>(R.id.count).text =
                String.format(
                    resources.getString(R.string.people_count),
                    ticket.count.toString(),
                )
            findViewById<TextView>(R.id.money).text =
                String.format(
                    resources.getString(R.string.payment),
                    ticket.money,
                )
            findViewById<TextView>(R.id.seat).text = ticket.seat.joinToString(", ")
        }
    }
}
