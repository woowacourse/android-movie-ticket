package woowacourse.movie.bookingResult

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.dto.Ticket

class BookingResultActivity : AppCompatActivity() {
    lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        ticket = intent.fetchExtraOrNull<Ticket>("TICKET") ?: return

        findViewById<TextView>(R.id.title).text = ticket.title
        findViewById<TextView>(R.id.date).text = ticket.date
        findViewById<TextView>(R.id.time).text = ticket.time
        findViewById<TextView>(R.id.count).text =
            String.format(
                this.getString(R.string.people_count),
                ticket.count.toString(),
            )
        findViewById<TextView>(R.id.money).text =
            String.format(
                this.getString(R.string.payment),
                ticket.money,
            )

        setSeatTextView()
    }

    fun setSeatTextView() {
        val seatView = findViewById<TextView>(R.id.seats)
        seatView.text = ticket.seats.joinToString(", ")
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)
}
