package woowacourse.movie.bookingResult

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.dto.Ticket
import woowacourse.movie.util.DataUtils

class BookingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        val ticket = DataUtils.getExtraOrFinish<Ticket>(intent, this, "TICKET") ?: return

        findViewById<TextView>(R.id.title).text = ticket.title
        findViewById<TextView>(R.id.date).text = ticket.date
        findViewById<TextView>(R.id.time).text = ticket.time
        findViewById<TextView>(R.id.count).text =
            String.format(
                resources.getString(R.string.people_count),
                ticket.count,
            )
        findViewById<TextView>(R.id.money).text =
            String.format(
                resources.getString(R.string.payment),
                ticket.money.toInt(),
            )
    }
}
