package woowacourse.movie.bookingResult

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.uiModel.TicketUIModel

class BookingResultActivity : AppCompatActivity() {
    lateinit var ticketUIModel: TicketUIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        ticketUIModel = intent.fetchExtraOrNull<TicketUIModel>("TICKET") ?: return

        findViewById<TextView>(R.id.title).text = ticketUIModel.title
        findViewById<TextView>(R.id.date).text = ticketUIModel.date
        findViewById<TextView>(R.id.time).text = ticketUIModel.time
        findViewById<TextView>(R.id.count).text =
            String.format(
                this.getString(R.string.people_count),
                ticketUIModel.count.toString(),
            )
        findViewById<TextView>(R.id.money).text =
            String.format(
                this.getString(R.string.payment),
                ticketUIModel.money,
            )

        setSeatTextView()
    }

    fun setSeatTextView() {
        val seatView = findViewById<TextView>(R.id.seats)
        seatView.text = ticketUIModel.seats.joinToString(", ")
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)
}
