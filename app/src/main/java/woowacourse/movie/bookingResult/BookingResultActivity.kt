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

        val ticketDTO = DataUtils.getExtraOrFinish<Ticket>(intent, this, "TICKET") ?: return

        findViewById<TextView>(R.id.title).text = ticketDTO.title
        findViewById<TextView>(R.id.date).text = ticketDTO.date
        findViewById<TextView>(R.id.time).text = ticketDTO.time
        findViewById<TextView>(R.id.count).text =
            String.format(
                this.getString(R.string.people_count),
                ticketDTO.count.toString(),
            )
        findViewById<TextView>(R.id.money).text =
            String.format(
                this.getString(R.string.payment),
                ticketDTO.money,
            )
    }
}
