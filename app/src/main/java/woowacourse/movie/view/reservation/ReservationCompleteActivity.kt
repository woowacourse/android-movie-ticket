package woowacourse.movie.view.reservation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.view.Extras
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.getParcelableExtraCompat

class ReservationCompleteActivity : AppCompatActivity() {
    private val movieTicket by lazy { getMovieTicketData() }
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMovieTicketInfo()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getMovieTicketData(): MovieTicket? = intent.getParcelableExtraCompat(Extras.TicketData.TICKET_KEY)

    private fun setupMovieTicketInfo() {
        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_complete_title)
        movieTitleTextView.text = movieTicket?.title

        val screeningDateTextView =
            findViewById<TextView>(R.id.tv_reservation_complete_timestamp)
        screeningDateTextView.text = movieTicket?.timeStamp

        val ticketCountTextView = findViewById<TextView>(R.id.tv_reservation_complete_ticket_count)
        ticketCountTextView.text =
            resources.getString(R.string.reservation_complete_ticket_count, movieTicket?.count)

        val ticketPriceTextView = findViewById<TextView>(R.id.tv_reservation_complete_ticket_price)
        ticketPriceTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_price,
                reservationUiFormatter.priceToUI(movieTicket?.price() ?: 13000),
            )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
