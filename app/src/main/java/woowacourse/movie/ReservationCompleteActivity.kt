package woowacourse.movie

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.MovieTicket

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieTicket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(TICKET_DATA_KEY, MovieTicket::class.java)
            } else {
                intent.getSerializableExtra(TICKET_DATA_KEY) as MovieTicket
            }

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
                THOUSAND_COMMA.format(movieTicket?.price()),
            )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val TICKET_DATA_KEY = "movieTicket"
        private val THOUSAND_COMMA = DecimalFormat("#,###")
    }
}
