package woowacourse.movie.view

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket

class ReservationCompleteActivity : AppCompatActivity() {
    private val movieTicket by lazy { getMovieTicketData() }
    private val formatter: Formatter by lazy { Formatter() }

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

    private fun getMovieTicketData(): MovieTicket? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(TICKET_DATA_KEY, MovieTicket::class.java)
        } else {
            intent.getSerializableExtra(TICKET_DATA_KEY) as MovieTicket
        }

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
                formatter.priceToUI(movieTicket?.price() ?: 13000),
            )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val TICKET_DATA_KEY = "movieTicket"
    }
}
