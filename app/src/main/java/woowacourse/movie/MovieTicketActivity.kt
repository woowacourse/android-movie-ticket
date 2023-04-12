package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    private val movieInfo by lazy { intent.getSerializableExtra("movie") as MovieInfo }
    private val ticketCount by lazy { intent.getIntExtra("ticketCount", 1) }
    private val ticketTotalPrice by lazy { intent.getIntExtra("ticketTotalPrice", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        updateMovieView()
    }

    fun updateMovieView() {
        val ticketTitleView = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieReleaseDateView = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPriceView = findViewById<TextView>(R.id.ticket_total_price)

        with(movieInfo) {
            ticketTitleView.text = title
            ticketMovieReleaseDateView.text = startDate.format(dateTimeFormatter)
            ticketCountView.text = getString(R.string.movie_ticket_count).format(ticketCount)
            ticketTotalPriceView.text = getString(R.string.movie_ticket_total_price).format(decimalFormat.format(ticketTotalPrice))
        }
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val decimalFormat = DecimalFormat("#,###")
    }
}
