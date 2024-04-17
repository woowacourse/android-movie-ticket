package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.formatTimestamp

class MovieReservationCompleteActivity : AppCompatActivity() {
    private lateinit var completeTitleTextView: TextView
    private lateinit var completeDateTextView: TextView
    private lateinit var completeReservationCountTextView: TextView
    private lateinit var completeReservationPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        completeTitleTextView = findViewById(R.id.completeTitle)
        completeDateTextView = findViewById(R.id.completeDate)
        completeReservationCountTextView = findViewById(R.id.completeReservationCount)
        completeReservationPriceTextView = findViewById(R.id.completeReservationPrice)

        getTicketData()?.let { ticket ->
            completeTitleTextView.text = ticket.title
            completeDateTextView.text = formatTimestamp(ticket.date)
            completeReservationCountTextView.text = "${ticket.count}명"
            completeReservationPriceTextView.text = "${formatCurrency(ticket.price)}원"
        }
    }

    private fun getTicketData(): MovieTicket? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("ticket", MovieTicket::class.java)
        } else {
            intent.getSerializableExtra("ticket") as MovieTicket
        }
    }
}
