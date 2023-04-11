package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        val movie = intent.getSerializableExtra("movie") as Movie
        val ticketCount = intent.getIntExtra("ticketCount", 1)

        val ticketTitle = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieReleaseDate = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPrice = findViewById<TextView>(R.id.ticket_total_price)

        ticketTitle.text = movie.title
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val decimalFormat = DecimalFormat("#,###")
        ticketMovieReleaseDate.text = "${movie.releaseDate.format(dateTimeFormatter)}"
        ticketCountView.text = "일반 ${ticketCount}명"
        ticketTotalPrice.text = "${decimalFormat.format(ticketCount * 13000)}원 (현장 결제)"
    }
}
